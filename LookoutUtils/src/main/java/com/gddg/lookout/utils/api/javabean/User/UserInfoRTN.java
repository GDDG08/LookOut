package com.gddg.lookout.utils.api.javabean.User;

import com.gddg.lookout.utils.api.javabean.BasicRTN;

import lombok.Getter;

@Getter
public class UserInfoRTN extends BasicRTN {
    private	String	username;
    private	String	nickname;
    private	String	avatar;
    private	String	gender;
    private	String	city;
    private	String	job;
    private	int	age;
    private	String	province;
    private	String	country;
}
