/*
 * Copyright (C), 2020-2021 广州小鹏汽车科技有限公司
 * FileName: PageResult
 * Author:   谭荣巧
 * Date:     2021/2/18 15:46
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号             描述
 */
package com.example.mokitotestdemo.global;

import lombok.Data;

/**
 * Rest接口请求结果对象<br>
 *
 */
@Data
public class Result<T> {
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回码描述
     */
    private String msg;
    /**
     * 异常错误信息
     */
    private String err;
    /**
     * 传递给请求者的数据
     */
    private T data;

    private String tracerId;

    public Result() {
        super();
    }

    /**
     * 构建失败报文
     *
     * @param code 错误码
     * @param msg  错误描述
     * @return
     */
    public static <T> Result<T> fail(int code, String msg) {
        Result<T> result = new Result<>();
        result.code(code).msg(msg);
        return result;
    }

    /**
     * 构建失败报文
     *
     * @param msg 错误描述
     * @return
     */
    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.code(9999).msg(msg);
        return result;
    }


    public Result<T> code(int code) {
        this.code = code;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> err(String err) {
        this.err = err;
        return this;
    }
}
