package com.gddg.lookout.utils.api.javabean.News;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewsParaRQT extends BasicRQT {
    int newsID;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/news/paragraph");
        params.addParameter("newsID", newsID);
        return params;
    }
}
