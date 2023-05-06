package com.zjxz.mikaniaplatform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.zjxz.mikaniaplatform.constants.RedisConstant;
import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.mapper.UserMapper;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.model.entity.User;
import com.zjxz.mikaniaplatform.service.UserService;
import com.zjxz.mikaniaplatform.util.JwtUtil;
import lombok.val;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import static com.zjxz.mikaniaplatform.constants.Common.HEADER_TOKEN;
import static com.zjxz.mikaniaplatform.constants.RedisConstant.USER_LOGIN_TOKEN_EXPIRE;

/**
* @author hzzzzzy
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-04-21 16:54:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private static String SALT="mikania";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String doLogin(String username, String password) {
        String token = "";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 根据username和password查对应用户
        val queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUsername,username);
        queryWrapper.eq(User::getPassword,encryptPassword);
        val user = this.getOne(queryWrapper);
        if(BeanUtil.isEmpty(user)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("找不到对应用户"));
        }else {
            // 生成token,存进redis  User2ThreadLocalUtils
            try {
                token = JwtUtil.createToken(String.valueOf(user.getId()),user.getUsername());
                stringRedisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_TOKEN + token, JSONUtil.toJsonStr(user),USER_LOGIN_TOKEN_EXPIRE, TimeUnit.SECONDS);
            } catch (UnsupportedEncodingException e) {
                throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("存入token失败"));
            }
            return token;
        }
    }


    @Override
    public void doLogout(HttpServletRequest request) {
        String token = request.getHeader(HEADER_TOKEN);
        // FIXME 退出登录时，删除redis中的token失败
        Boolean flag = stringRedisTemplate.delete(RedisConstant.USER_LOGIN_TOKEN + token);
        if(!flag){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("退出登录失败"));
        }
    }


    @Override
    public void doRegister(String username, String password, String verifyPassword) {
        // 如果密码与确认密码不一样，则注册失败
        if(!password.equals(verifyPassword)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.REGISTER_PASSWORD_ERROR));
        }
        // 如果用户名在数据库已经存在，则注册失败
        val userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
        userLambdaQueryWrapper.eq(User::getUsername,username);
        val user = this.getOne(userLambdaQueryWrapper);
        if(!BeanUtil.isEmpty(user)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.REGISTER_USERNAME_EXIST));
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        val saveUser = User.builder()
                .username(username)
                .password(encryptPassword)
                .build();
        this.save(saveUser);
    }


    @Override
    public void doAlter(HttpServletRequest request,String oldPassword, String newPassword, String verifyPassword) {
        String token = request.getHeader(HEADER_TOKEN);
        String jsonUser = stringRedisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_TOKEN + token);
        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);
        if(!user.getPassword().equals(oldPassword)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.OLD_PASSWORD_ERROR));
        }
        if(!newPassword.equals(verifyPassword)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.REGISTER_PASSWORD_ERROR));
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + newPassword).getBytes());
        user.setPassword(encryptPassword);
        val userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
        userLambdaQueryWrapper.eq(User::getId,user.getId());
        this.update(user,userLambdaQueryWrapper);
        stringRedisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_TOKEN+token, JSONUtil.toJsonStr(user),USER_LOGIN_TOKEN_EXPIRE, TimeUnit.SECONDS);
    }
}




