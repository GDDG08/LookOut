package com.gddg.lookout.utils.api.javabean.User;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserEditAvatarRQT extends BasicRQT {
    private	int	userID;
    private	String avatar;


    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/user/update/avatar");
        params.addBodyParameter("userId", userID);
        params.addBodyParameter("avatar", avatar);
        return params;
    }
}
