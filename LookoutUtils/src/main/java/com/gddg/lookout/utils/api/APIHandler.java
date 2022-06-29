package com.gddg.lookout.utils.api;

import com.gddg.lookout.utils.api.javabean.BasicRTN;
import com.gddg.lookout.utils.api.javabean.Cloud.OCR_RQT;
import com.gddg.lookout.utils.api.javabean.Cloud.OCR_RTN;
import com.gddg.lookout.utils.api.javabean.Cloud.STT_RQT;
import com.gddg.lookout.utils.api.javabean.Cloud.STT_RTN;
import com.gddg.lookout.utils.api.javabean.Cloud.TTS_RQT;
import com.gddg.lookout.utils.api.javabean.Cloud.TTS_RTN;
import com.gddg.lookout.utils.api.javabean.News.NewsClickRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsClickRTN;
import com.gddg.lookout.utils.api.javabean.News.NewsContentRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsContentRTN;
import com.gddg.lookout.utils.api.javabean.News.NewsDislikeRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsFeedRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsFeedRTN;
import com.gddg.lookout.utils.api.javabean.News.NewsGetInterestAllRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsGetInterestRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsGetInterestRTN;
import com.gddg.lookout.utils.api.javabean.News.NewsLoveRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsLoveRTN;
import com.gddg.lookout.utils.api.javabean.News.NewsParaRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsParaRTN;
import com.gddg.lookout.utils.api.javabean.News.NewsSetInterestRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsSetInterestRTN;
import com.gddg.lookout.utils.api.javabean.News.NewsStarRQT;
import com.gddg.lookout.utils.api.javabean.News.NewsStarRTN;
import com.gddg.lookout.utils.api.javabean.User.UserDetailRQT;
import com.gddg.lookout.utils.api.javabean.User.UserDetailRTN;
import com.gddg.lookout.utils.api.javabean.User.UserEditAvatarRQT;
import com.gddg.lookout.utils.api.javabean.User.UserEditProfileRQT;
import com.gddg.lookout.utils.api.javabean.User.UserEditPwdRQT;
import com.gddg.lookout.utils.api.javabean.User.UserInfoRQT;
import com.gddg.lookout.utils.api.javabean.User.UserInfoRTN;
import com.gddg.lookout.utils.api.javabean.User.UserLoginRQT;
import com.gddg.lookout.utils.api.javabean.User.UserLoginRTN;
import com.gddg.lookout.utils.api.javabean.User.UserLogoutRQT;
import com.gddg.lookout.utils.api.javabean.User.UserSigninRQT;
import com.gddg.lookout.utils.api.javabean.User.UserWXLoginRQT;
import com.gddg.lookout.utils.tool.Base64Utils;
import com.gddg.lookout.utils.tool.MD5Utils;

import java.util.List;

public class APIHandler {

    private static RequestHandler mRequestHandler = new RequestHandler();

    public int userID = -1;

    public APIHandler(int userID) {
        this.userID = userID;
    }

    public APIHandler() {
    }

    public void CONFIG_UserID(int userID) {
        this.userID = userID;
    }

    public void getNewsFeed(String cate, int num, APICallback acb) {
        NewsFeedRQT rqt = new NewsFeedRQT(userID, cate, num);
        mRequestHandler.get(rqt, acb, NewsFeedRTN.class);
    }

    public void getNewsContent(int newsID, APICallback acb) {
        NewsContentRQT rqt = new NewsContentRQT(newsID);
        mRequestHandler.get(rqt, acb, NewsContentRTN.class);
    }

    public void getNewsPara(int newsID, APICallback acb) {
        NewsParaRQT rqt = new NewsParaRQT(newsID);
        mRequestHandler.get(rqt, acb, NewsParaRTN.class);
    }

    public void setNewsInterest(List<String> tags, int num, APICallback acb) {
        NewsSetInterestRQT rqt = new NewsSetInterestRQT(userID, tags, num);
        mRequestHandler.post(rqt, acb, NewsSetInterestRTN.class);
    }

    public void getNewsInterestUser(APICallback acb) {
        NewsGetInterestRQT rqt = new NewsGetInterestRQT(userID);
        mRequestHandler.get(rqt, acb, NewsGetInterestRTN.class);
    }

    public void getNewsInterestAll(APICallback acb) {
        NewsGetInterestAllRQT rqt = new NewsGetInterestAllRQT();
        mRequestHandler.get(rqt, acb, NewsGetInterestRTN.class);
    }

    public void onNewsClick(int newsID, APICallback acb) {
        NewsClickRQT rqt = new NewsClickRQT(newsID, userID);
        mRequestHandler.get(rqt, acb, NewsClickRTN.class);
    }

    public void onNewsLove(int newsID, APICallback acb) {
        NewsLoveRQT rqt = new NewsLoveRQT(userID, newsID);
        mRequestHandler.get(rqt, acb, NewsLoveRTN.class);
    }

    public void onNewsStar(int newsID, APICallback acb) {
        NewsStarRQT rqt = new NewsStarRQT(userID, newsID);
        mRequestHandler.get(rqt, acb, NewsStarRTN.class);
    }

    public void onNewsDislike(int newsID, String reason, APICallback acb) {
        NewsDislikeRQT rqt = new NewsDislikeRQT(userID, newsID, reason);
        mRequestHandler.get(rqt, acb, BasicRTN.class);
    }

    public void getUserInfo(APICallback acb) {
        UserInfoRQT rqt = new UserInfoRQT(userID);
        mRequestHandler.get(rqt, acb, UserInfoRTN.class);
    }

    public void getUserDetail(APICallback acb) {
        UserDetailRQT rqt = new UserDetailRQT(userID);
        mRequestHandler.get(rqt, acb, UserDetailRTN.class);
    }

    public void onUserSignin(String username, String pwd, APICallback acb) {
        String pwd_md5 = MD5Utils.encrypt(pwd);
        UserSigninRQT rqt = new UserSigninRQT(username, pwd_md5);
        mRequestHandler.post(rqt, acb, BasicRTN.class);
    }

    public void onUserLogin(String username, String pwd, APICallback acb) {
        String pwd_md5 = MD5Utils.encrypt(pwd);
        UserLoginRQT rqt = new UserLoginRQT(username, pwd_md5);
        mRequestHandler.post(rqt, acb, UserLoginRTN.class);
    }

    public void onUserWXLogin(String code, APICallback acb) {
        UserWXLoginRQT rqt = new UserWXLoginRQT(userID, code);
        mRequestHandler.get(rqt, acb, UserLoginRTN.class);
    }

    public void onUserLogout(APICallback acb) {
        UserLogoutRQT rqt = new UserLogoutRQT(userID);
        mRequestHandler.get(rqt, acb, BasicRTN.class);
    }

    public void setUserProfile(String gender, String city, String nickname, String job, String province, String country, APICallback acb) {
        UserEditProfileRQT rqt = new UserEditProfileRQT(userID, gender, city, nickname, job, province, country);
        mRequestHandler.post(rqt, acb, BasicRTN.class);
    }

    public void setUserAvator(String avator, APICallback acb) {
        UserEditAvatarRQT rqt = new UserEditAvatarRQT(userID, avator);
        mRequestHandler.post(rqt, acb, BasicRTN.class);
    }

    public void setUserPassword(String oldpwd, String newpwd, APICallback acb) {
        String oldpwd_md5 = MD5Utils.encrypt(oldpwd);
        String newpwd_md5 = MD5Utils.encrypt(newpwd);
        UserEditPwdRQT rqt = new UserEditPwdRQT(userID, oldpwd_md5, newpwd_md5);
        mRequestHandler.post(rqt, acb, BasicRTN.class);
    }

    public void cloudOCR(byte[] b, APICallback acb) {
        String imgBase64 = Base64Utils.encode(b);
        OCR_RQT rqt = new OCR_RQT(imgBase64);
        mRequestHandler.post(rqt, acb, OCR_RTN.class);
    }

    public void cloudTTS(String text, APICallback acb) {
        TTS_RQT rqt = new TTS_RQT(text);
        mRequestHandler.post(rqt, acb, TTS_RTN.class);
    }

    public void cloudSTT(byte[] b, APICallback acb) {
        String audioBase64 = Base64Utils.encode(b);
        STT_RQT rqt = new STT_RQT(audioBase64);
        mRequestHandler.post(rqt, acb, STT_RTN.class);
    }
}
