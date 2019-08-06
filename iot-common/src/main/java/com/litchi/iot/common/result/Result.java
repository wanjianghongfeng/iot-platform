package com.litchi.iot.common.result;

import java.io.Serializable;

import com.litchi.iot.common.enums.ErrorCodeEnum;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = -5772988220428759062L;

	private Integer code;

    private String msg;

    private T result;

    private Long timestamp;

    private String gotoUrl;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getGotoUrl() {
        return gotoUrl;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }

    /**
     * 系统定义的错误返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error() {
        return error(ErrorCodeEnum.BUSINESS_FAIL.getCode(), ErrorCodeEnum.BUSINESS_FAIL.getMsg());
    }

    /**
     * 自定义返回错误结果 默认错误码为1 业务处理失败
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message) {
        return error(ErrorCodeEnum.BUSINESS_FAIL.getCode(), message);
    }

    public static <T> Result<T> ok(String message) {
        return error(ErrorCodeEnum.RESULT_SUCCESS.getCode(), message);
    }

    /**
     * @param code 传入对应的错误码 自动返回对应错误消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer code) {
        return error(code, ErrorCodeEnum.getMsg(code));
    }

    /**
     * 全部自定义消息 与错误码
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> msg = new Result<>();
        msg.msg = message;
        msg.code = code;
        return msg.putTimeStamp();
    }

    /**
     * 使用系统默认错误码 传入错误返回结果
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Result<T> errorResult(T result) {
        return new Result<T>().result(result).putTimeStamp().code(ErrorCodeEnum.BUSINESS_FAIL.getCode()).msg(ErrorCodeEnum.BUSINESS_FAIL.getMsg());
    }

    /**
     * 请求成功 默认code为0 掺入对应的返回结果
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T result) {
        return new Result<T>().result(result).putTimeStamp().code(ErrorCodeEnum.RESULT_SUCCESS.getCode()).msg(ErrorCodeEnum.RESULT_SUCCESS.getMsg());
    }

    /**
     * 请求成功 默认code为0 掺入对应的返回结果
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        return new Result<T>().putTimeStamp().code(ErrorCodeEnum.RESULT_SUCCESS.getCode()).msg(ErrorCodeEnum.RESULT_SUCCESS.getMsg());
    }

    private Result<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public Result() {

    }

    public Result<T> result(T result) {
        this.result = result;
        return this;
    }

    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                ", timestamp=" + timestamp +
                ", gotoUrl='" + gotoUrl + '\'' +
                '}';
    }
}
