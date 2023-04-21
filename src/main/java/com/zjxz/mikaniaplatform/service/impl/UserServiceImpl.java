package com.zjxz.mikaniaplatform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxz.mikaniaplatform.mapper.UserMapper;
import com.zjxz.mikaniaplatform.model.entity.User;
import com.zjxz.mikaniaplatform.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author hzzzzzy
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-04-21 16:54:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




