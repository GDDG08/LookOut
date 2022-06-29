package com.gddg.lookout.utils.api.javabean.News;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewsFeedRQT extends BasicRQT {
    private int userID;
    private String cate;
    private int num;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/news/feed");
        params.addParameter("userID", userID);
        params.addParameter("cate", cate);
        params.addParameter("num", num);
        return params;
    }
}
