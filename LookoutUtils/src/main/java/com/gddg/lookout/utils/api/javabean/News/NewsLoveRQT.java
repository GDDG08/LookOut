package com.gddg.lookout.utils.api.javabean.News;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewsLoveRQT extends BasicRQT {
    private int userID;
    private int newsID;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/news/onlove");
        params.addParameter("userID", userID);
        params.addParameter("newsID", newsID);
        return params;
    }
}
