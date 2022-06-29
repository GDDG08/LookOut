package com.gddg.lookout.utils.api.javabean.User;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserEditPwdRQT extends BasicRQT {
    private	int	userID;
    private	String oldpwd;
    private	String newpwd;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/user/update/pwd");
        params.addBodyParameter("userId", userID);
        params.addBodyParameter("oldpwd", oldpwd);
        params.addBodyParameter("newpwd", newpwd);

        return params;
    }
}
