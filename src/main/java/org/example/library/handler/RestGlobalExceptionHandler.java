package org.example.library.handler;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotPermissionException;
import org.example.library.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExceptionHandler {
    // 全局异常拦截
    @ExceptionHandler(NotPermissionException.class)
    public Result notPermissionException(NotPermissionException e) {
        SaHolder.getResponse().setStatus(403);
        return Result.error(e.getMessage());
    }
}