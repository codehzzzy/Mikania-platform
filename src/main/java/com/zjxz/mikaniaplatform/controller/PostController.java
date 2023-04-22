package com.zjxz.mikaniaplatform.controller;

import cn.hutool.core.io.FileUtil;
import com.zjxz.mikaniaplatform.constants.BucketName;
import com.zjxz.mikaniaplatform.constants.DirectoryName;
import com.zjxz.mikaniaplatform.enums.BusinessFailCode;
import com.zjxz.mikaniaplatform.exception.GlobalException;
import com.zjxz.mikaniaplatform.model.dto.PostInfoAddRequest;
import com.zjxz.mikaniaplatform.model.entity.Result;
import com.zjxz.mikaniaplatform.service.PostInfoService;
import com.zjxz.mikaniaplatform.util.MyFileUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.zjxz.mikaniaplatform.util.MultipartFileToFileUtils.multipartFileToFile;


/**
 * @author hzzzzzy
 * @date 2023/4/5
 * @description 文件控制器
 */
@Tag(name = "1-帖子管理")
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private MyFileUtil myFileUtil;
    @Autowired
    private PostInfoService postInfoService;

    @ApiOperation("添加帖子")
    @PostMapping("/addPost")
    public Result addPost(
            @RequestPart(value = "file")
            @Parameter(description = "文件", required = true)
            MultipartFile file,
            @Parameter(description = "帖子添加请求", required = true)
            @NotEmpty
            PostInfoAddRequest postAddRequest
    ) {
        var url = "";
        var fileName = file.getOriginalFilename();
        // 拼接文件名
        var sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 获取文件的后缀名
        var suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        var newName = sdf.format(new Date()) +suffixName;
        try {
            var newFile = multipartFileToFile(file);
            File finalFile = FileUtil.rename(newFile, newName, true);
            var path = MyFileUtil.spliceBatch(DirectoryName.GUANGZHOU_CITY, newName);
            myFileUtil.uploadFile(BucketName.OTHER_PROVINCE, path, finalFile, true);
            // 拼接地址
            url = MyFileUtil.spliceUrl(BucketName.OTHER_PROVINCE, path);
        } catch (Exception e) {
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("MultipartFile转File失败"));
        }
        postInfoService.addPost(postAddRequest, url);
        return new Result<>().success().message("上传成功").data(url);
    }

    //todo 暴露接口让人工智能调用，获取

    //todo 分页展示帖子

    //todo 暴露接口让人工智能调用，获取url

}