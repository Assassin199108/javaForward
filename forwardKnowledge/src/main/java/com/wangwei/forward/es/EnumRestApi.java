package com.wangwei.forward.es;

/**
 * es的rest api
 *
 * @author wangwei
 */
public enum EnumRestApi {

    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE"),
    ;

    private String type;

    EnumRestApi(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
