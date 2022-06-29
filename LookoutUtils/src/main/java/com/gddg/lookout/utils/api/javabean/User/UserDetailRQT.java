package com.gddg.lookout.utils.api.javabean.User;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDetailRQT extends BasicRQT {
    private int userID;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/user/get/info/detail");
        params.addParameter("userID", userID);
        return params;
    }
}
