package com.gddg.lookout.utils.api.javabean.Cloud;

import static com.gddg.lookout.utils.api.HostHandler.getHost2;

import com.gddg.lookout.utils.api.javabean.BasicRQT;

import org.xutils.http.RequestParams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TTS_RQT extends BasicRQT {
    String text;

    public RequestParams genParams() {
        RequestParams params = new RequestParams(getHost2() + "/cloudAPI/text2audio");
        params.addBodyParameter("text", text);
        return params;
    }
}