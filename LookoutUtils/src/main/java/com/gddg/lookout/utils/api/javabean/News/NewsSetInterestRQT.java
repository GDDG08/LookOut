package com.gddg.lookout.utils.api.javabean.News;

import static com.gddg.lookout.utils.api.HostHandler.getHost;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewsSetInterestRQT extends BasicRQT {
    private int userID;
    private List<String> tags;
    private int num;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost() + "/news/interest/setTag");
        params.addBodyParameter("tags", tags);
        params.addBodyParameter("num", num);
        return params;
    }
}
