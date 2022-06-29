package com.gddg.lookout.utils.api;


import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class RequestHandler {

    public void get(BasicRQT rqt, APICallback acb, Class RtnCls) {
        RequestParams params = rqt.genParams();
        params.setUseCookie(true);
        RequestCallback rcb = new RequestCallback(acb, RtnCls);
        x.http().get(params, rcb);
    }

    public void post(BasicRQT rqt, APICallback acb, Class RtnCls) {
        RequestParams params = rqt.genParams();
        params.setAsJsonContent(true);
        params.setUseCookie(true);
//        params.addHeader("Content-Type", "application/json");
        RequestCallback rcb = new RequestCallback(acb, RtnCls);
        x.http().post(params, rcb);
    }
}
