package com.gddg.lookout.utils.api.javabean.User;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSigninRQT extends BasicRQT {
    private String username;
    private String pwd;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/user/register");
        params.addBodyParameter("username", username);
        params.addBodyParameter("pwd", pwd);
        return params;
    }
}
