package com.example.backend.common;

import lombok.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    private Result() {
    }

    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    public static Result success(Object data) {
        return new Result(200, "操作成功", data);
    }

    public static Result fail(int code ,String message) {
        return new Result(code, message, null);
    }
    
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{\"code\":500,\"message\":\"JSON序列化失败\"}";
        }
    }

    public static void main(String[] args) {
        System.out.println(Result.success("测试成功").toJson());
    }
}
