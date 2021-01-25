package xd.com.appupdatexd;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.telecom.Call;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import lsw.update.BaseDialog;
import lsw.update.PermissionsCheckerUtil;
import lsw.update.UpdateBean;
import lsw.update.UpdateUtil;
import lsw.update.VersionBean;



public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private BaseDialog versionDialog;
    private UpdateBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //3、获取屏幕的默认分辨率
        Display display = getWindowManager().getDefaultDisplay();
        int heigth = display.getWidth();
        int width = display.getHeight();


        Log.e("lsw", "height - "+heigth+ " width-" + width);


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



    public void updateTestApp(View view) {
        mBean = new UpdateBean();
        mBean.setFileName("apkname"+ "6.6");
        mBean.setCONTENT("升级测试");
        mBean.setTITLE("新版本升级");
        mBean.setVersionNO("6.6.0");
        mBean.setDOWNLOAD_URL("http://wap.apk.anzhi.com/data5/apk/202101/12/ebe7b9bfb3cb766734a0d1097066ff70_17588800.apk");
        mBean.setForceUpdate(true);
        showUPdateDialog(mBean);
    }

    public void updateApp(View view) {
        Log.e("lsw", "升级---");

        mBean = new UpdateBean();
        // 请求示例
        OkHttpUtils
                .post()
                .url("https://www.pgyer.com/apiv2/app/view")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addParams("_api_key", "b9ceae317e6a31d762f6da14ed1a19ce")
                .addParams("appKey", "c565fbd08c576519a75e5393399c4eb7")
                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("lsw", response);
                        VersionBean.DataBean versionBean = new Gson().fromJson(response, VersionBean.class).getData();
                        //比较版本
                        if (Integer.parseInt(versionBean.getBuildVersionNo()) > UpdateUtil.getVersionNo(MainActivity.this)) {
                            Log.i("lsw", "need update ");
                            mBean.setFileName(versionBean.getBuildName() + versionBean.getBuildVersion());
                            mBean.setCONTENT(versionBean.getBuildUpdateDescription());
                            mBean.setTITLE(versionBean.getBuildName());
                            mBean.setVersionNO(versionBean.getBuildVersion());
                            mBean.setDOWNLOAD_URL("http://gdown.baidu.com/data/wisegame/283e9789be54e63c/weixin_1560.apk");
                            mBean.setForceUpdate(true);
                            showUPdateDialog(mBean);
                        }
                    }
                });
    }

    private void showUPdateDialog(UpdateBean bean) {

        if (!new PermissionsCheckerUtil(this).lacksPermissionOps(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    999);
        } else {
            showDialog();
        }

    }

    private void showDialog() {
        versionDialog = new BaseDialog(this, R.style.BaseDialog, R.layout.custom_dialog_two_layout, mBean)
//        .setCommonDialog()
                // 修改升级的图片，按钮颜色
                .setProgressLoadingColor(0xFF0000FF)
        .setBtnDrawable(R.drawable.selector_btn_blue)
        .setTopImage(R.drawable.blue_top_bg)
        .setTopPadding(25)
        ;

        versionDialog.getmIv_new().setVisibility(View.INVISIBLE);

        versionDialog.
                show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 999) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showDialog();
            }
        }

    }

    /**
     * versiondialog dismiss 的时候会回调此方法
     * 这里面可以进行强制更新操作
     * <p>
     * 建议用一个ActivityManger记录每个Activity出入堆栈
     * 最后全部关闭activity 实现app exit
     * ActivityTaskManger.finishAllActivity();
     *
     * @param
     */
    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Log.e("lsw","--- 取消升级");
    }


}
