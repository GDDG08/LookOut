package com.gddg.lookout.utils.api.javabean.Cloud;

import com.gddg.lookout.utils.api.javabean.BasicRTN;
import com.gddg.lookout.utils.tool.Base64Utils;

import lombok.Getter;

@Getter
public class TTS_RTN extends BasicRTN {
    String audioBase64;

    public byte[] getAudio(){
        return Base64Utils.decode(audioBase64);
    }
}
