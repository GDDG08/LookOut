package com.gddg.lookout.utils.api.javabean.News;

import com.gddg.lookout.utils.api.javabean.BasicRTN;

import java.util.List;

import lombok.Getter;

@Getter
public class NewsGetInterestRTN extends BasicRTN {
    private List<String> tags;
    private int num;
}
