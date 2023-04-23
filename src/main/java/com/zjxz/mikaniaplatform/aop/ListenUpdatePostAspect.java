package com.zjxz.mikaniaplatform.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author hzzzzzy
 * @create 2023/4/23
 * @description 监听修改帖子方法
 */
@Aspect
@Component
public class ListenUpdatePostAspect {

    @AfterReturning("execution(* com.zjxz.mikaniaplatform.service.PostInfoService.updateUserPost(..))")
    public void afterMethod(JoinPoint joinPoint) {

    }
}
