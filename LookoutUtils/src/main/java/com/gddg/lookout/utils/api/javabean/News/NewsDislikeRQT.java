package com.gddg.lookout.utils.api.javabean.News;/*
 **Created by 24606 at 16:06 2022.
 */

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewsDislikeRQT extends BasicRQT {
    private int userID;
    private int newsID;
    private String reason;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/news/interest/dislike");
        params.addParameter("userID", userID);
        params.addParameter("newsID", newsID);
        params.addParameter("reason", reason);
        return params;
    }
}
