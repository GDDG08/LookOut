package com.gddg.lookout.utils.api.javabean;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import org.xutils.http.RequestParams;

public class BasicRQT {

    public  BasicRQT(){

    }
    public RequestParams genParams() {
        return new RequestParams(getHost() + "/basic");
    }
}
