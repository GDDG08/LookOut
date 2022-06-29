package com.gddg.lookout.utils.api;

import org.xutils.http.annotation.HttpResponse;

@HttpResponse(parser = com.gddg.lookout.utils.api.StdAPIParser.class)
public class StdAPI {
    private int code;
    private String msg;
    private String data;

    public StdAPI(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }
}
