package com.zjxz.mikaniaplatform.util;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hzzzzzy
 * @create 2023/4/1
 * @description 将EntityList转为对应的VOList
 */
public class EntityList2VOList {
    public static <Eneity,VO> List<VO> Eneity2VoOrDTO(List<Eneity> eneityList, Class<VO> vo){
        return eneityList.stream().map(item -> BeanUtil.copyProperties(item, vo)).collect(Collectors.toList());
    }
}