package com.itcuc.common;

import lombok.Data;

import java.util.List;

@Data
public class Result {

    private int code;

    private String msg;

    private Integer count;

    private Object data;

    public Result(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    public static Result success() {
        Result result = new Result(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(data);
        if(data instanceof List) {
            result.setCount(((List) data).size());
        }
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result(resultCode);
        result.setData(data);
        return result;
    }
}
