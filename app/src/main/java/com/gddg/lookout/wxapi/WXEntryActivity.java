package com.gddg.lookout.wxapi;


import static com.gddg.lookout.MainActivity.api;
import static com.gddg.lookout.SecondFragment.onAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onReq(BaseReq req) {
        Log.d("onReq--->", "");
        finish();
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            SendAuth.Resp authResp = (SendAuth.Resp)resp;
            final String code = authResp.code;
            Log.d("code", code);
            onAuth(code);
        }
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }
}
