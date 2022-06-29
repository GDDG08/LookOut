package com.gddg.lookout.utils.api.javabean.User;

import com.gddg.lookout.utils.api.javabean.BasicRTN;

import lombok.Getter;

@Getter
public class UserDetailRTN extends BasicRTN {
    private int id;
    private int phone;
    private String wechatID;
    private int exp;
    private int level;
    private int days;
    private int regTime;

}
