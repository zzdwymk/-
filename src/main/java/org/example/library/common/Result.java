package org.example.library.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;
    private String message;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }
    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(400);
        result.setMessage("error");
        return result;
    }

    public static Result error(String message){
        return error(400, message, null);
    }

    public static Result error(int code, String message, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}