package com.gddg.lookout.utils.api.javabean.News;

import com.gddg.lookout.utils.api.javabean.BasicRTN;

import java.util.List;

import lombok.Getter;

@Getter
public class NewsParaRTN extends BasicRTN {
    private int newsID;
    private List<Para_list> content;

    @Getter
    public class Para_list {
        private int paraID;
        private String content;
        private String scheme;
    }
}


