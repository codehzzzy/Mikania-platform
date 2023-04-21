package com.zjxz.mikaniaplatform.exception;

import com.aliyun.oss.OSSException;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author hzzzzzy
 * @create 2023/4/1
 * @description 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ResponseEntity<Result<Object>> error(HttpServletRequest request, Exception e) {
//        // 业务失败
//        log.info(e.getMessage(), e);
//        // HTTP请求方式
//        if (e instanceof HttpRequestMethodNotSupportedException) {
//            return ResponseEntity.badRequest().body(new Result<>().fail(BusinessFailCode.REQUEST_METHOD_NOT_SUPPORTED));
//        }
//        if (
//            // 参数类型匹配错误
//                e instanceof HttpMessageNotReadableException || e instanceof MethodArgumentTypeMismatchException
//        ) {
//            return ResponseEntity.badRequest().body(new Result<>().fail());
//        }
//        if (e instanceof ConstraintViolationException) {
//            String[] validationStrings = e.getMessage().split(",");
//            String mRes = null;
//            for (String validationString : validationStrings) {
//                String substring = validationString.substring(validationString.indexOf(".") + 1).trim();
//                if (mRes == null) {
//                    mRes = substring;
//                } else {
//                    mRes = mRes.concat(", ").concat(substring);
//                }
//            }
//            return ResponseEntity.badRequest().body(new Result<>().fail(BusinessFailCode.PARAMETER_ERROR).message(mRes));
//        }
//        // 无业务处理能力，返回错误
//        log.error(e.getMessage(), e);
//        return ResponseEntity.internalServerError().body(new Result<>().error());
//    }

    @ExceptionHandler(value = OSSException.class)
    public void ossError(OSSException oe){
        log.error("捕获了OSSException，请求已发送到OSS，但由于某种原因被拒绝并收到错误响应");
        log.error("Error Message:" + oe.getErrorMessage());
        log.error("Error Code:" + oe.getErrorCode());
        log.error("Request ID:" + oe.getRequestId());
        log.error("Host ID:" + oe.getHostId());
    }

    @ExceptionHandler(value = ClientException.class)
    public void ossClientError(ClientException ce){
        log.error("捕获了客户端异常，客户端遇到了尝试与OSS通信时出现严重的内部问题");
        log.error(ce.getMessage());
    }
}
