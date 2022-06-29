package com.gddg.lookout.utils.api;

import static com.gddg.lookout.utils.tool.myLog.logD;

import android.widget.Toast;

import com.gddg.lookout.utils.api.javabean.BasicRTN;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.x;

public class RequestCallback implements Callback.CommonCallback<StdAPI> {

    private final Class<BasicRTN> cls;
    private final APICallback acb;

    public RequestCallback(APICallback acb, Class<BasicRTN> cls) {
        this.acb = acb;
        this.cls = cls;
    }

    @Override
    public void onSuccess(StdAPI result) {
        logD("HTTP:" + "success---->" + result.getData());
        if (result.getCode() == 200) {
            Gson gson = new Gson();
            BasicRTN resultObject = gson.fromJson(result.getData(), cls);
            acb.onSuccess(result.getMsg(), resultObject, result.getData());
        } else
            acb.onError("业务错误：" + result.getCode() + "--" + result.getMsg());
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        logD("HTTP:" + "error---->" + ex.getMessage());
        Toast.makeText(x.app().getApplicationContext(), "HTTP Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancelled(CancelledException cex) {
        logD("HTTP:" + "cancelled");
    }

    @Override
    public void onFinished() {
        logD("HTTP:" + "finished");
    }
}
