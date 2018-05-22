package com.cr.crinporder.common.handler;

import com.cr.crinporder.common.enm.ResultCode;
import com.cr.crinporder.core.base.BaseReturnDTO;
import com.cr.crinporder.core.base.BusinessBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice(annotations = {Controller.class})
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Object methodArgumentTypeMismatchExceptionHandler() {
        return BaseReturnDTO.fail("入参错误！");
    }

    /**
     * 表单提交和JSON提交导常支持
     * @return BaseReturnDTO
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public Object HttpMediaTypeNotSupportedExceptionHandler() {
        return BaseReturnDTO.fail(ResultCode.UNSUPPORT_MEDIA_TYPE);
    }

//    @ExceptionHandler(value = AuthenticationException.class)
//    @ResponseBody
//    public Object authenticationExceptionHandler(HttpServletRequest req, AuthenticationException ex) throws Exception {
//        BaseReturnDTO fail = BaseReturnDTO.fail(ResultCode.USERNAME_PASSWORD_ERROR.getCode(),ResultCode.USERNAME_PASSWORD_ERROR.getMessage());
////        if(StringUtils.isNotBlank(ex.getMessage())) baseJsonDTO.setErrorMsg(ex.getMessage());
//        fail.setObj(req.getParameter("username"));
//        return fail;
//    }


        @ExceptionHandler(UncategorizedSQLException.class)
        @ResponseBody
        public Object uncategorizedSQLExceptionExceptionHandler(UncategorizedSQLException ex) {
            logger.error("UncategorizedSQLException:{}",ex);
            return BaseReturnDTO.fail("500","不支持字符集");

        }

        @ExceptionHandler(BindException.class)
        @ResponseBody
        public Object bindException(BindException ex){
            logger.error("BindException:{}",ex);
            List<String> errorList = new ArrayList<>();
            for (FieldError e:ex.getFieldErrors()) {
                errorList.add(e.getDefaultMessage());
            }
            return BaseReturnDTO.fail("500","信息填写错误",errorList);
        }

        @ExceptionHandler(BusinessBaseException.class)
        @ResponseBody
        public Object businessBaseExceptionHandler(BusinessBaseException ex){
            logger.error("BusinessBaseException:{}",ex);
            return BaseReturnDTO.fail("500",ex.getMessage());
        }
}
