package com.zjxz.mikaniaplatform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxz.mikaniaplatform.constants.Status;
import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.mapper.PostInfoMapper;
import com.zjxz.mikaniaplatform.model.dto.PostInfoAddRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUpdateRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUploadStatusRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUploadStatusResponse;
import com.zjxz.mikaniaplatform.model.entity.PageResult;
import com.zjxz.mikaniaplatform.model.entity.PostInfo;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.model.vo.PostInfoVO;
import com.zjxz.mikaniaplatform.model.vo.UserPostInfoVO;
import com.zjxz.mikaniaplatform.service.PostInfoService;
import com.zjxz.mikaniaplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zjxz.mikaniaplatform.util.Eneity2VoOrDTOList.Eneity2VoOrDTO;
import static com.zjxz.mikaniaplatform.util.MyBeanUtil.copyProperties;
import static com.zjxz.mikaniaplatform.util.PageUtil.getPage;
import static com.zjxz.mikaniaplatform.util.User2ThreadLocalUtils.getUser;

/**
* @author hzzzzzy
* @description 针对表【postinfo(发布信息表)】的数据库操作Service实现
* @createDate 2023-04-21 16:54:07
*/
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo>
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
    public void uploadStatus(List<PostInfoUploadStatusRequest> postInfoUploadStatusRequestList) {
        var postInfoList = new ArrayList<PostInfo>();
        postInfoUploadStatusRequestList.stream().peek(request->{
            PostInfo postInfo = copyProperties(request, PostInfo.class);
            postInfoList.add(postInfo);
        });
        var flag = this.updateBatchById(postInfoList);
        if (flag){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("帖子添加失败"));
        }
    }


    @Override
    public List<PostInfoUploadStatusResponse> getUrl() {
        var queryWrapper = new LambdaQueryWrapper<PostInfo>();
        queryWrapper.eq(PostInfo::getStatus,0);
        var postInfoList = this.list(queryWrapper);
        if (BeanUtil.isEmpty(postInfoList)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("帖子url获取失败"));
        }
        var list = Eneity2VoOrDTO(postInfoList, PostInfoUploadStatusResponse.class);
        return list;
    }


    @Override
    public PageResult<PostInfoVO> get(int current, int size) {
        var postInfoList = this.list();
        var tempList = Eneity2VoOrDTO(postInfoList, PostInfoVO.class);
        var list = tempList.stream().peek(item -> {
            var username = userService.getById(item.getUserId()).getUsername();
            item.setUsername(username);
        }).collect(Collectors.toList());
        var result = getPage(list, current, size);
        if (BeanUtil.isEmpty(result)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("帖子列表获取失败"));
        }
        return result;
    }


    @Override
    public PageResult<UserPostInfoVO> getUserPost(int current, int size) {
        var postInfoList = this.list(
                new LambdaQueryWrapper<PostInfo>().eq(PostInfo::getUserId, getUser().getId())
        );
        // 封装为VO
        var userPostVoList = Eneity2VoOrDTO(postInfoList, UserPostInfoVO.class);
        var result = getPage(userPostVoList, current, size);
        if (BeanUtil.isEmpty(result)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("帖子列表获取失败"));
        }
        return result;
    }


    @Override
    public void updateUserPost(PostInfoUpdateRequest postInfoUpdateRequest) {
        String url = postInfoUpdateRequest.url();
        PostInfo postInfo = this.getOne(
                new LambdaQueryWrapper<PostInfo>().eq(PostInfo::getId, postInfoUpdateRequest.id())
        );
        if (!postInfo.getUrl().equals(url)) {
            postInfo.setStatus(Status.WAIT);
            postInfo.setUrl(url);
            boolean flag = this.updateById(postInfo);
            if (flag){
                throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("更新失败"));
            }
        }
    }
}