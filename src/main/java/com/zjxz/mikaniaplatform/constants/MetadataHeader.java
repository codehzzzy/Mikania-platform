package com.zjxz.mikaniaplatform.constants;

/**
 * @author hzzzzzy
 * @date 2023/4/6
 * @description 元数据头
 */
public interface MetadataHeader {
    /**
     * 覆盖标识
     */
    String OVERWRITE = "x-oss-forbid-overwrite";

    /**
     * 权限控制
     */
    String ACCESS_PERMISSIONS = "x-oss-object-acl";

    /**
     * 添加元数据索引
     */
    String META_INDEX = "x-oss-meta-index";

}
