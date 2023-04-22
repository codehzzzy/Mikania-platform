package com.zjxz.mikaniaplatform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxz.mikaniaplatform.constants.Status;
import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.model.dto.PostInfoAddRequest;
import com.zjxz.mikaniaplatform.model.entity.PostInfo;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.service.PostInfoService;
import com.zjxz.mikaniaplatform.mapper.PostinfoMapper;
import org.springframework.stereotype.Service;

import static com.zjxz.mikaniaplatform.util.User2ThreadLocalUtils.getUser;

/**
* @author hzzzzzy
* @description 针对表【postinfo(发布信息表)】的数据库操作Service实现
* @createDate 2023-04-21 16:54:07
*/
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostinfoMapper, PostInfo>
    implements PostInfoService {

    @Override
    public void addPost(PostInfoAddRequest postAddRequest, String url) {
        PostInfo postInfo = BeanUtil.copyProperties(postAddRequest, PostInfo.class);
        postInfo.setStatus(Status.WAIT);
        postInfo.setUrl(url);
        postInfo.setUserId(getUser().getId());
        boolean flag = this.save(postInfo);
        if (flag){
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("帖子添加失败"));
        }
    }
}




