package com.gddg.lookout.utils.api.javabean.News;

import com.gddg.lookout.utils.api.javabean.BasicRTN;

import lombok.Getter;

@Getter
public class NewsContentRTN extends BasicRTN {
    private int newsID;
    private String cate;
    private String title;
    private double heat;
    private String time;
    private String source;
    private String content;
    private int views;
    private int loves;
    private int starNum;
}
