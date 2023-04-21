package com.zjxz.mikaniaplatform.util;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.zjxz.mikaniaplatform.constants.Common;
import com.zjxz.mikaniaplatform.constants.MetadataHeader;
import com.zjxz.mikaniaplatform.model.entity.ContentTypeExcel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author hzzzzzy
 * @date 2023/4/5
 * @description 文件工具类
 */
@Component
@Slf4j
public class MyFileUtil {

    @Autowired
    private OSS ossClient;

    /**
     * 拼接多个路径
     *
     * @param name 名称
     * @return 拼接后的路径
     */
    public static String spliceBatch(String... name) {
        StringBuilder path = new StringBuilder();
        Arrays.stream(name).collect(Collectors.toList()).forEach(directory -> path.append(directory).append("/"));
        path.deleteCharAt(path.length()-1);
        return path.toString();
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param flag 是否需要覆盖同名的object
     * @param file 文件
     * @return 文件路径
     */
    @SneakyThrows
    public void uploadFile(String bucketName, String path, File file, boolean flag) {
        ObjectMetadata metadata = new ObjectMetadata();
        if (flag){
            metadata.setHeader(MetadataHeader.OVERWRITE, Common.TRUE);
        }
        metadata.setContentType(getContentType(file.getName()));
        ossClient.putObject(bucketName, path, FileUtil.getInputStream(file), metadata);
    }

    /***
     * 获取文件类型
     *
     * @param fileName 文件名称
     * @return 文件类型
     */
    @SneakyThrows
    private static String getContentType(String fileName) {
        // 获取文件后缀
        String suffix = getFileSuffix(fileName);
        // 读取excel文件
        AtomicReference<String> type = new AtomicReference<>("");
        String excelPath = ResourceUtils.getFile("classpath:" + "ContentType.xlsx").getPath();
        EasyExcel.read(excelPath, ContentTypeExcel.class, new PageReadListener<ContentTypeExcel>(list -> list.forEach(contentTypeExcel -> {
            if (contentTypeExcel.getFileExtension().equals(suffix)){
                type.set(contentTypeExcel.getType());
            }
        }))).sheet().doRead();
        log.info("文件contentType类型为: {}", type);
        return type.get();
    }

    /**
     * 获取文件后缀
     *
     * @param fileName 文件名称
     * @return 文件后缀
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 拼接Url
     *
     * @param bucketName 桶名称
     * @param path 文件路径
     * @return 文件Url
     */
    public static String spliceUrl(String bucketName, String path){
        return "https://" + bucketName + "." + "oss-cn-guangzhou.aliyuncs.com" + "/" + path;
    }
}
