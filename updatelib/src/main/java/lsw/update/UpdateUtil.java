package lsw.update;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * author: Created by lsw on 2018/8/2 17:02
 * description:
 */
public class UpdateUtil {
    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String VersionName = "";
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
//            VersionName = context.getPackageManager().getPackageInfo("com.example.brook.myapplicationtest0924", 0).versionName;
            VersionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return VersionName;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static int getVersionNo(Context context) {
        int VersionName  = 0 ;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
//            VersionName = context.getPackageManager().getPackageInfo("com.example.brook.myapplicationtest0924", 0).versionName;
            VersionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return VersionName;
    }


}
