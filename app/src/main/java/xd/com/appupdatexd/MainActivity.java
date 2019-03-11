package xd.com.appupdatexd;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import okhttp3.Call;


public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener {

    private BaseDialog versionDialog;
    private UpdateBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //3、获取屏幕的默认分辨率
        Display display = getWindowManager().getDefaultDisplay();
        int heigth = display.getWidth();
        int width = display.getHeight();


        Log.e("lsw", "height - "+heigth+ " width-" + width);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void updateApp(View view) {
        Log.e("lsw", "升级---");

        mBean = new UpdateBean();

        OkHttpUtils
                .post()
                .url("https://www.pgyer.com/apiv2/app/view")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addParams("_api_key", "b9ceae317e6a31d762f6da14ed1a19ce")
                .addParams("appKey", "c565fbd08c576519a75e5393399c4eb7")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
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
                            mBean.setDOWNLOAD_URL("http://2018_10_30.azsoft-kuaila-down.31town.com/2018/Q4/shangheyun.apk?ch=pckuaila&sid=139293&name=%e5%95%86%e5%90%88%e4%ba%91&rec=False&stype=%e5%b8%b8%e7%94%a8%e5%b7%a5%e5%85%b7&tags=%2c%e6%95%88%e7%8e%87%e5%8a%9e%e5%85%ac%2c%e5%95%86%e5%8a%a1%2c%e7%b3%bb%e7%bb%9f%2c&ua=Mozilla%2f5.0+(Windows+NT+10.0%3b+WOW64)+AppleWebKit%2f537.36+(KHTML%2c+like+Gecko)+Chrome%2f70.0.3538.77+Safari%2f537.36");
                            mBean.setForceUpdate(true);
                            showUPdateDialog(mBean);
                        }
                    }
                });
    }

    private void showUPdateDialog(UpdateBean bean) {

        if (!new PermissionsCheckerUtil(this).lacksPermissionOps(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    999);
        } else {
            showDialog();
        }

    }

    private void showDialog() {
        versionDialog = new BaseDialog(this, R.style.BaseDialog, R.layout.custom_dialog_two_layout, mBean)
//        .setCommonDialog()
                .setProgressLoadingColor(0xFF00FF00)
        ;
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
