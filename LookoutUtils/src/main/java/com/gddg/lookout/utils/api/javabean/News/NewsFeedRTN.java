package com.gddg.lookout.utils.api.javabean.News;

import com.gddg.lookout.utils.api.javabean.BasicRTN;

import java.util.List;

import lombok.Getter;

@Getter
public class NewsFeedRTN extends BasicRTN {

    private List<News_list> news_list;
    private int nums;

    @Getter
    public class News_list {
        private int newsID;
        private String cate;
        private String title;
        private String digest;
        private String hpic;
        private double heat;
        private String keywords;
    }
}

