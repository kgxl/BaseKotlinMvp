package com.kgxl.common.base;

import com.google.gson.annotations.SerializedName;

/***
 * Create by kgxl on 2019/9/6
 */
public class BaseBean<T> {
    private int status;
    private String info;
    //这是接口数据的字段名，需要根据接口更改
    //gson解析时通过字段名查找
    @SerializedName("books")
    private T data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
