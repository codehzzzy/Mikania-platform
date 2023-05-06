package com.zjxz.mikaniaplatform.service;

import com.zjxz.mikaniaplatform.model.dto.PostInfoAddRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUpdateRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUploadStatusRequest;
import com.zjxz.mikaniaplatform.model.dto.PostInfoUploadStatusResponse;
import com.zjxz.mikaniaplatform.model.entity.PageResult;
import com.zjxz.mikaniaplatform.model.entity.PostInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.model.vo.PostInfoVO;
import com.zjxz.mikaniaplatform.model.vo.UserPostInfoVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @return 是否成功
     */
    void addPost(PostInfoAddRequest postAddRequest, HttpServletRequest request);

    /**
     * 更新帖子状态
     *
     * @param postInfoUploadStatusRequestList 帖子添加请求列表
     */
    void uploadStatus(List<PostInfoUploadStatusRequest> postInfoUploadStatusRequestList);

    /**
     * 返回图片/视频url
     *
     * @return 对象列表
     */
    List<PostInfoUploadStatusResponse> getUrl();


    /**
     * 获取帖子信息
     *
     * @param current 当前页
     * @param size 页面容量
     * @return 对象列表
     */
    PageResult<PostInfoVO> get(int current, int size);


    /**
     * 查看用户帖子
     *
     * @param current 当前页
     * @param size 页面容量
     * @return 对象列表
     */
    PageResult<UserPostInfoVO> getUserPost(int current, int size, HttpServletRequest request);


    /**
     * 修改用户帖子
     *
     * @param postInfoUpdateRequest 帖子更新请求
     */
    void updateUserPost(PostInfoUpdateRequest postInfoUpdateRequest);
}
