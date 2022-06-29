package com.gddg.lookout.utils.tool;

import java.util.Base64;

public class Base64Utils {
    final static Base64.Decoder decoder = Base64.getMimeDecoder();
    final static Base64.Encoder encoder = Base64.getMimeEncoder();

    final public static String encode(byte[] b) {
        //编码
        return encoder.encodeToString(b);
    }

    final public static byte[] decode(String base64) {
        //解码
        return decoder.decode(base64);
    }
}
