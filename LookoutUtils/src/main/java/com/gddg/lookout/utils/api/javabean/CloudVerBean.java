package com.gddg.lookout.utils.api.javabean;

import lombok.Data;

@Data
public class CloudVerBean {
    private int checkUpdate;
    private int verCode;
    private String verName;
    private String chagelog;
    private boolean forceUpdate;
    private int type;
    private String directlink;
    private String weblink;
    private String bitlink;
    private String downMsg;
}


