package com.gddg.lookout.utils.api;

import com.gddg.lookout.utils.api.javabean.BasicRTN;

public interface APICallback{
    void onSuccess(String msg, BasicRTN rtn, String originData);
    void onError(String info);
}
