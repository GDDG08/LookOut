package com.gddg.lookout.utils;

import android.app.Application;
import android.content.Context;

import com.gddg.lookout.utils.tool.CrashHandler;
import com.gddg.lookout.utils.tool.VersionControl;
import org.xutils.x;

public class LookoutUtils {
    public static void init(Application context){
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(context);
        x.Ext.init(context);

//        NotchScreenManager.getInstance().setDisplayInNotch(this);
        VersionControl.init(context);
    }
}
