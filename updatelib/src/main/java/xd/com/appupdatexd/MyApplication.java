package xd.com.appupdatexd;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

//import com.tencent.bugly.crashreport.CrashReport;

import lsw.updatelib.BuildConfig;

/**
 * author: Created by lsw on 2018/8/2 16:38
 * description:
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), "e057581d14", BuildConfig.DEBUG);
        //7.0 查询文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }
}
