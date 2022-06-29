package com.gddg.lookout.utils.api.javabean.Cloud;

import static com.gddg.lookout.utils.api.HostHandler.getHost2;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class STT_RQT extends BasicRQT {
    String audioBase64;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost2() + "/cloudAPI/audio2text");
        params.addBodyParameter("imgBase64", audioBase64);
        return params;
    }
}