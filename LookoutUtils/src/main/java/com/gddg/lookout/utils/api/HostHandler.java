package com.gddg.lookout.utils.api;

public class HostHandler {

    private static String host = "http://cloud.gddg08.top:5001";
    private static String host2 = "http://124.71.236.106:5000";
//    private static String host = "http://123.56.44.91/RJB";

    public static void setHost(String host) {
        HostHandler.host = host;
    }

    public static String getHost() {
        return host;
    }
    public static String getHost2() {
        return host;
    }
}
