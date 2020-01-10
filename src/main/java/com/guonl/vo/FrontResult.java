package com.guonl.vo;

import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;


public class FrontResult<T> implements Serializable {

    // 结果信息 0:成功
    private Integer code = 0;

    // 错误信息
    private String message = "请求成功";

    // 具体数据
    private T data = null;

    public FrontResult() {
    }

    public FrontResult(Integer errorCode, String errorMsg) {
        this.code = errorCode;
        this.message = errorMsg;
    }

    public FrontResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public FrontResult(Error error) {
        if (error == null) {
            code = -1;
            message = "请求异常";
        } else {
            code = error.getErrcode();
            message = error.getErrmsg();
        }

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer errorCode) {
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMsg) {
        this.message = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> FrontResult<T> success(T data) {
        FrontResult<T> frontResult = new FrontResult<T>();
        frontResult.setData(data);
        return frontResult;
    }

    public static <T> FrontResult<T> error(int code, String message) {
        return new FrontResult<>(code, message);
    }

    public static <T> FrontResult<T> success(int code, String message, T data) {
        FrontResult<T> frontResult = new FrontResult<>(code, message);
        frontResult.setData(data);
        return frontResult;
    }

    /**
     * 页面参数校验失败提示
     *
     * @param code
     * @param errors
     * @return
     */
    public static FrontResult<List<ObjectError>> errorValidated(int code, List<ObjectError> errors) {
        return new FrontResult<List<ObjectError>>(code, "参数传递不正确", errors);
    }


}
