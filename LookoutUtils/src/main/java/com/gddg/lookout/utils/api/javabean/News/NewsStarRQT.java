package com.gddg.lookout.utils.api.javabean.News;/*
 **Created by 24606 at 15:41 2022.
 */

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewsStarRQT extends BasicRQT {
    private int userID;
    private int newsID;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/news/onStar");
        params.addParameter("userID", userID);
        params.addParameter("newsID", newsID);
        return params;
    }
}
