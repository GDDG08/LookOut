package com.gddg.lookout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.gddg.lookout.databinding.ActivityMainBinding;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    public static final String APP_ID = "wx3d6ebf387ae5fb4f";

    // IWXAPI 是第三方 app 和微信通信的 openApi 接口
    public static IWXAPI api;

    private void regToWx() {
        // 通过 WXAPIFactory 工厂，获取 IWXAPI 的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
//        api = WXAPIFactory.createWXAPI(this, APP_ID);


        // 将应用的 appId 注册到微信
        boolean succ = api.registerApp(APP_ID);
//        Log.d("WX-->", "register: "+succ);
        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该 app 注册到微信
                api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_test";
                api.sendReq(req);
            }
        });
        regToWx();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //    public interface WXRetrListener{
//        void onAuth(String code);
//    }


}