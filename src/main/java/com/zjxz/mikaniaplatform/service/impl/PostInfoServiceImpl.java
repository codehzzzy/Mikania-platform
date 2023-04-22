package com.zjxz.mikaniaplatform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxz.mikaniaplatform.constants.Status;
import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.model.dto.PostInfoAddRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUploadStatusRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUploadStatusResponse;
import com.zjxz.mikaniaplatform.model.entity.PageResult;
import com.zjxz.mikaniaplatform.model.entity.PostInfo;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.model.vo.PostInfoVO;
import com.zjxz.mikaniaplatform.service.PostInfoService;
import com.zjxz.mikaniaplatform.mapper.PostinfoMapper;
import com.zjxz.mikaniaplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.zjxz.mikaniaplatform.util.EntityList2VOList.Eneity2VoOrDTO;
import static com.zjxz.mikaniaplatform.util.MyBeanUtil.copyProperties;
import static com.zjxz.mikaniaplatform.util.PageUtil.getPage;
import static com.zjxz.mikaniaplatform.util.User2ThreadLocalUtils.getUser;

/**
* @author hzzzzzy
* @description 针对表【postinfo(发布信息表)】的数据库操作Service实现
* @createDate 2023-04-21 16:54:07
*/
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostinfoMapper, PostInfo>
    implements PostInfoService {
    @Autowired
    private UserService userService;

    @Override
    public void addPost(PostInfoAddRequest postAddRequest, String url) {
        var postInfo = copyProperties(postAddRequest, PostInfo.class);
        postInfo.setStatus(Status.WAIT);
        postInfo.setUrl(url);
        postInfo.setUserId(getUser().getId());
        var flag = this.save(postInfo);
        if (flag){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("帖子添加失败"));
        }
    }

    @Override
    public void uploadStatus(PostInfoUploadStatusRequest postInfoUploadStatusRequest) {
        var postInfo = copyProperties(postInfoUploadStatusRequest, PostInfo.class);
        boolean flag = this.updateById(postInfo);
        if (flag){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("帖子更新失败"));
        }
    }

    @Override
    public List<PostInfoUploadStatusResponse> getUrl() {
        LambdaQueryWrapper<PostInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostInfo::getStatus,0);
        List<PostInfo> postInfoList = this.list(queryWrapper);
        if (BeanUtil.isEmpty(postInfoList)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("帖子url获取失败"));
        }
        List<PostInfoUploadStatusResponse> list = Eneity2VoOrDTO(postInfoList, PostInfoUploadStatusResponse.class);
        return list;
    }

    @Override
    public PageResult<PostInfoVO> get(int current, int size) {
        List<PostInfo> postInfoList = this.list();
        List<PostInfoVO> tempList = Eneity2VoOrDTO(postInfoList, PostInfoVO.class);
        List<PostInfoVO> list = tempList.stream().peek(item -> {
            String username = userService.getById(item.getUserId()).getUsername();
            item.setUsername(username);
        }).collect(Collectors.toList());
        PageResult<PostInfoVO> result = getPage(list, current, size);
        if (BeanUtil.isEmpty(result)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("帖子列表获取失败"));
        }
        return result;
    }
}