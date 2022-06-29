package com.gddg.lookout.utils.api.javabean.User;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserEditProfileRQT extends BasicRQT {
    private	int	userId;
    private	String	gender;
    private	String	city;
    private	String	nickname;
    private	String	job;
    private	String	province;
    private	String	country;


    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/user/update/info");
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("gender", gender);
        params.addBodyParameter("city", city);
        params.addBodyParameter("nickname", nickname);
        params.addBodyParameter("job", job);
        params.addBodyParameter("province", province);
        params.addBodyParameter("country", country);
        return params;
    }
}
