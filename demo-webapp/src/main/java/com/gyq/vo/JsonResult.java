package com.gyq.vo;

import com.google.gson.Gson;

@SuppressWarnings("all")
public class JsonResult {

    private final int errorCode;
    private final String message;
    private final Object data;

    private JsonResult(Builder builder){
        this.errorCode=builder.errorCode;
        this.message=builder.message;
        this.data=builder.data;
    }

    public static class Builder{
        private int errorCode;
        private String message;
        private Object data;

        public Builder errorCode(int errorCode){
            this.errorCode=errorCode;
            return this;
        }

        public Builder message(String message){
            this.message=message;
            return this;
        }

        public Builder data(Object data){
            this.data=data;
            return this;
        }

        public JsonResult build(){
            return new JsonResult(this);
        }
    }

    public static JsonResult successResult(Object data){
        return new Builder().errorCode(0).message("success").data(data).build();
    }

    public static JsonResult failedResult(Object data){
        return failedResult("error",data);
    }

    public static JsonResult failedResult(String message,Object data){
        return new Builder().errorCode(-1).message(message).data(data).build();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
