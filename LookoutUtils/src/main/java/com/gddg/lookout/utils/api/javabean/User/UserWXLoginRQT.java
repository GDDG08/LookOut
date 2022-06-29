package com.gddg.lookout.utils.api.javabean.User;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserWXLoginRQT extends BasicRQT {
    private int userID;
    private String code;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/user/login/wechat");
        params.addParameter("userID",userID);
        params.addParameter("code", code);
        return params;
    }
}
