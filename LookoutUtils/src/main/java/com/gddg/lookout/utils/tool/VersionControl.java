package com.gddg.lookout.utils.tool;

import static com.gddg.lookout.utils.tool.myLog.logD;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.gddg.lookout.utils.api.StdAPI;
import com.gddg.lookout.utils.api.javabean.CloudVerBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class VersionControl {

    private static Activity activity;
    private static Context mContext;
    private static int versionCode;
    private static String versionName;

    private static CloudVerBean cvb;

    public static void init(Context context) {
        mContext = context;
        initInfo();
    }

    public static void setMainActivity(Activity mainActivity) {
        activity = mainActivity;
    }

    public static void initInfo() {
        PackageManager manager = mContext.getPackageManager();//获取包管理器
        try {
            //通过当前的包名获取包的信息
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);//获取包对象信息
            versionCode = info.versionCode;
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        getSeverVer();
    }

    public static int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public static void getSeverVer() {
        RequestParams params = new RequestParams("http://123.56.44.91/RJB/update");
        params.addParameter("test","1");
        x.http().get(params, new Callback.CommonCallback<StdAPI>() {
            @Override
            public void onSuccess(StdAPI result) {
                logD("HTTP:" + "success---->" + result.getData());
                Gson gson = new Gson();
                cvb = gson.fromJson(result.getData(), CloudVerBean.class);
                check();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                logD("HTTP:" + "error---->" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                logD("HTTP:" + "cancelled");
            }

            @Override
            public void onFinished() {
                logD("HTTP:" + "finished");
            }
        });
    }

    public static void check() {

        logD("UPDATE--->" + "verCode:" + cvb.getVerCode());

        if (cvb.getCheckUpdate() == -1)
            return;
        if (cvb.getVerCode() <= getVersionCode()) {
            if (cvb.getCheckUpdate() > 0)
            Toast.makeText(activity, "已是最新版本", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "检测到新版本", Toast.LENGTH_SHORT).show();

            String force_text = "暂不更新";
            DialogInterface.OnClickListener force_click = null;
            boolean force_cancelable = true;
            if (cvb.isForceUpdate()) {
                force_text = "退出";
                force_click = (dialog, which) -> activity.finish();
                force_cancelable = false;
            }
            new AlertDialog.Builder(activity)
                    .setTitle("检测到新版本")
                    .setMessage(cvb.getVerName() + "\n\n●更新日志：\n\n" + cvb.getChagelog() + "\n\n●点击立即更新↘")
                    .setNegativeButton(force_text, force_click)
                    .setCancelable(force_cancelable)
                    .setPositiveButton("立即更新", (dialog, which) -> {
                        userUpdate();
                        if (cvb.isForceUpdate())
                            activity.finish();
                    })
                    .show();
        }
    }

    private static void userUpdate() {
        new AlertDialog.Builder(activity)
                .setTitle("请选择下载方式")
                .setMessage(cvb.getDownMsg())
                .setCancelable(true)
                .setPositiveButton("校园网访问", (view, which2) -> {
//                    mainActivity.web(cvb.getBitlink());
                })
                .setNegativeButton("校外访问", (view, which2) -> {
//                    mainActivity.web(cvb.getWeblink());
                }).show();
//        mainActivity.web(cvb.getDirectlink());
    }
}
