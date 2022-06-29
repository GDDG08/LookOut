package com.gddg.lookout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gddg.lookout.databinding.FragmentSecondBinding;
import com.gddg.lookout.utils.api.APICallback;
import com.gddg.lookout.utils.api.APIHandler;
import com.gddg.lookout.utils.api.javabean.BasicRTN;
import com.gddg.lookout.utils.api.javabean.Cloud.TTS_RTN;
import com.gddg.lookout.utils.api.javabean.User.UserLoginRTN;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.List;

public class SecondFragment extends Fragment {

    private static TextView textviewSecond;
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    private int spinner_pos = 0;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_pos = position;
//                Toast.makeText(getContext(), "你点击的是:"+languages[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.buttonGo.setOnClickListener(view2 -> {
            api_test();

        });

        textviewSecond = binding.textviewSecond;
    }

    private static APICallback acb = new APICallback() {
        @Override
        public void onSuccess(String msg, BasicRTN rtn, String originData) {
            textviewSecond.setText("API Success:\n" + "msg\n" + originData);
        }

        @Override
        public void onError(String info) {
            textviewSecond.setText("API Error:\n" + info);
        }
    };

    private static APICallback acb_login = new APICallback() {
        @Override
        public void onSuccess(String msg, BasicRTN rtn, String originData) {
            UserLoginRTN data = (UserLoginRTN) rtn;
            api.CONFIG_UserID(data.getUserID());
            acb.onSuccess(msg, rtn, originData);
        }

        @Override
        public void onError(String info) {
            acb.onError(info);
        }
    };
    static APIHandler api = new APIHandler();
    private void api_test() {
        String[] apis = getResources().getStringArray(R.array.apis);
        APIHandler api2 = new APIHandler(1234);

        api2.onUserLogin("1", "1", new APICallback() {
            @Override
            public void onSuccess(String msg, BasicRTN rtn, String originData) {
                UserLoginRTN data = (UserLoginRTN) rtn;
                data.getUserID();
            }

            @Override
            public void onError(String info) {

            }
        });
        switch (apis[spinner_pos]) {
            case "User Login":
                api.onUserLogin("TEST", "123456", acb_login);
                break;
            case "User Register":
                api.onUserSignin("TEST", "123456", acb);
                break;
            case "User LoginWX":
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_test";
                MainActivity.api.sendReq(req);
                break;
            case "User Logout":
                api.onUserLogout(acb);
                break;
            case "User Info":
                api.getUserInfo(acb);
                break;
            case "User Detail":
                api.getUserDetail(acb);
                break;
            case "User EditInfo":
                api.setUserProfile("female", "Beijing", "Nickname", "job", "Beijing", "China", acb);
                break;
            case "User EditAvatar":
                api.setUserAvator("http://nonepic", acb);
                break;
            case "User EditPwd":
                api.setUserPassword("123456", "123456", acb);
                break;
            case "News Feed":
                api.getNewsFeed("财经", 3, acb);
                break;
            case "News Content":
                api.getNewsContent(1, acb);
                break;
            case "News Para":
                api.getNewsPara(1, acb);
                break;
            case "News SetInterest":
                String[] list = new String[]{"科技", "财经"};
                api.setNewsInterest(Arrays.asList(list), list.length, acb);
                break;
            case "News GetInterestUser":
                api.getNewsInterestUser(acb);
                break;
            case "News GetInterestAll":
                api.getNewsInterestAll(acb);
                break;
            case "News Click":
                api.onNewsClick(1,acb);
                break;
            case "News Love":
                api.onNewsLove(1,acb);
                break;
            case "News Star":
                api.onNewsStar(1,acb);
                break;
            case "News Dislike":
                api.onNewsDislike(1,"too much;", acb);
                break;
        }
    }

    public static void onAuth(String code) {
        textviewSecond.setText("获取到的Code：" + code);
        APIHandler api = new APIHandler();
        api.onUserWXLogin(code, acb_login);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}