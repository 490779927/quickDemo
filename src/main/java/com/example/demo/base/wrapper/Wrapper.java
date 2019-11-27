package com.example.demo.base.wrapper;

import com.example.demo.base.dto.WrapperBool;
import com.example.demo.base.enums.ErrorCodeEnum;
import com.example.demo.base.enums.ErrorType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：yangjin.
 * @Date ：Created in 20:35 2019/11/27
 */
@ApiModel(value = "返回对象", description = "返回对象")
@Data
public class Wrapper<T> implements Serializable {
    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 4893280118017319089L;

    /**
     * 成功码.
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 成功码.
     */
    public static final int PAY_ERROR_CODE = 800;

    /**
     * 成功信息.
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 错误码.
     */
    public static final int ERROR_CODE = 500;

    /**
     * 错误信息.
     */
    public static final String ERROR_MESSAGE = "服务器开小差了";

    /**
     * 错误码：参数非法
     */
    public static final int ILLEGAL_ARGUMENT_CODE_ = 100;

    /**
     * 错误信息：参数非法
     */
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";

    /**
     * 编号.
     */
    @ApiModelProperty("编码")
    private int code;

    /**
     * 信息.
     */
    @ApiModelProperty("提示信息")
    private String message;

    /**
     * 结果数据
     */
    @ApiModelProperty("结果数据")
    private T result;

    /**
     * Instantiates a new wrapper. default code=200
     */
    Wrapper() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     */
    Wrapper(int code, String message) {
        this(code, message, null);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param code    the code
     * @param message the message
     * @param result  the result
     */
    Wrapper(int code, String message, T result) {
        super();
        this.code(code).message(message).result(result);
    }


    /**
     * Sets the 编号 , 返回自身的引用.
     *
     * @param code the new 编号
     * @return the wrapper
     */
    private Wrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    /**
     * Sets the 信息 , 返回自身的引用.
     *
     * @param message the new 信息
     * @return the wrapper
     */
    private Wrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param result the new 结果数据
     * @return the wrapper
     */
    public Wrapper<T> result(T result) {
        this.setResult(result);
        return this;
    }


    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
     *
     * @return code !=200,true;否则 false.
     */
    @JsonIgnore
    public boolean error() {
        return !isSuccess();
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    @JsonIgnore
    public static Wrapper fail(ErrorType errorType) {
        return Wrapper.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    @JsonIgnore
    public static Wrapper fail(ErrorType errorType, Object data) {
        return new Wrapper<>(errorType, data);
    }

    /**
     * @param errorType
     * @param result
     */
    @JsonIgnore
    public Wrapper(ErrorType errorType, T result) {
        this(errorType);
        this.result = result;
    }

    /**
     * @param errorType
     */
    @JsonIgnore
    public Wrapper(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    @JsonIgnore
    public static Wrapper fail() {
        return new Wrapper(ErrorCodeEnum.SYSTEM_ERROR);
    }

    public static Wrapper failMsg(ErrorCodeEnum errorCodeEnum) {
        return new Wrapper(errorCodeEnum);
    }

    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE == (this.code) && this.getResult() != null;
    }

    public boolean isCodeSuccess() {
        return SUCCESS_CODE == (this.code);
    }

    /**
     * 快速创建成功结果并返回结果数据
     * 这个方法可以返回true和false
     *
     * @param data
     * @return Result
     */
    @JsonIgnore
    public static Wrapper success(Object data) {
        return new Wrapper<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }


    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    @JsonIgnore
    public static Wrapper success() {
        return new Wrapper<>(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static Wrapper success(String message) {
        return new Wrapper<>(SUCCESS_CODE, message, null);
    }

    public static Wrapper success(boolean flag) {
        WrapperBool bool = new WrapperBool();
        bool.setFlag(flag);
        return new Wrapper<>(SUCCESS_CODE, SUCCESS_MESSAGE, bool);
    }

    public static Wrapper success(int successCode, String successMessage, Object data) {
        return new Wrapper<>(successCode, successMessage, data);
    }
}
