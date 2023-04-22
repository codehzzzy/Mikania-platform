package com.zjxz.mikaniaplatform.service;

import com.zjxz.mikaniaplatform.model.dto.PostInfoAddRequest;
import com.zjxz.mikaniaplatform.model.entity.PostInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hzzzzzy
* @description 针对表【postinfo(发布信息表)】的数据库操作Service
* @createDate 2023-04-21 16:54:07
*/
public interface PostInfoService extends IService<PostInfo> {

    /**
     * 添加帖子
     *
     * @param postAddRequest 帖子添加请求
     * @param url 图片/视频 url
     * @return 是否成功
     */
    void addPost(PostInfoAddRequest postAddRequest, String url);
}
