package lsw.update;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.function.LongUnaryOperator;

import lsw.updatelib.R;
import okhttp3.Call;


/**
 * 下载的弹窗 包括下载功能
 *
 * @author lsw
 * @date 18/8/2
 */
public class BaseDialog extends Dialog implements DialogInterface.OnDismissListener {
    private Context context;
    private UpdateBean bean;
    private int res;
    private FlikerProgressBar mFlikerbar;
    private LinearLayout mLl_buttons;
    private ImageView mIv_top;
    private View line_top;
    private View line_bottom;
    private Button btnUpdate;
    private ImageView iv_bottom;
    private TextView tvTitle;
    private TextView version_no_tv;
    private TextView tvMsg;
    private ImageView mIv_new;

//    public BaseDialog(Context context, int theme, int res) {
//        super(context, theme);
//        //
//        setContentView(res);
//        init();
//        this.res = res;
//        setCanceledOnTouchOutside(false);
//    }

    public BaseDialog(Context context, int theme, int res, UpdateBean bean) {
        super(context, theme);
        this.context = context;
        //
        setContentView(res);
        this.bean = bean;
        init();
        this.res = res;
    }

    private void init() {
        mIv_top = (ImageView) findViewById(R.id.iv_top);
        mIv_new = (ImageView) findViewById(R.id.iv_new);
        iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
        line_top = findViewById(R.id.line_top);
        line_bottom = findViewById(R.id.line_bottom);
        mLl_buttons = (LinearLayout) findViewById(R.id.ll_buttons);
        mFlikerbar = (FlikerProgressBar) findViewById(R.id.flikerbar);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        version_no_tv = (TextView) findViewById(R.id.version_no_tv);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        btnUpdate = (Button) findViewById(R.id.version_dialog_commit);
        View version_dialog_next = findViewById(R.id.version_dialog_next);
        version_dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        if( bean != null && !bean.isForceUpdate()){
            version_dialog_next.setVisibility(View.GONE);
        }else {
            version_dialog_next.setVisibility(View.VISIBLE);
        }

        //设置dismiss listener 用于强制更新,dimiss会回调dialogDismiss方法
        setOnDismissListener(this);
        //可以使用之前从service传过来的一些参数比如：title。msg，downloadurl，parambundle
        tvTitle.setText(bean.getTITLE());
        // 添加 \n 作为换行
        tvMsg.setText(bean.getCONTENT());
        if (bean.getVersionNO().contains("V") || bean.getVersionNO().contains("v")) {
            version_no_tv.setText(bean.getVersionNO());
        } else {
            version_no_tv.setText("V" + bean.getVersionNO());
        }

        //可以使用之前service传过来的值

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLl_buttons.setVisibility(View.GONE);
                mFlikerbar.setVisibility(View.VISIBLE);
                //download apk
                downloadApk();
            }
        });
    }

    /**
     * 下载apk
     */
    private void downloadApk() {
//        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/回收大师");
        String path = getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        Log.e("lsw", "10 测试 path = " + path);
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
            Log.e("update", fileDir.getAbsolutePath() + "-- mkdir ok");
        }

        OkHttpUtils//
                .get()//
                .url(bean.getDOWNLOAD_URL())//
                .build()//
                .execute(new FileCallBack(fileDir.getAbsolutePath(), bean.getFileName()) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        mFlikerbar.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context, "下载失败！", Toast.LENGTH_SHORT).show();
                        Log.i("update", e.getMessage() + "  ");
                        mFlikerbar.setVisibility(View.GONE);
                        mLl_buttons.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        if (response.isFile() && response.exists()) {

                            Log.e("update", "download success ---" + response.getAbsolutePath());
                            Log.e("update", "context.getPackageName() ---" + context.getPackageName());

                            //安装 apk
                            installApk(context, response, context.getPackageName()+ ".update.fileProvider");
                        }
                    }
                });
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    @Override
    public void dismiss() {
        context = null;
        super.dismiss();
    }

    @Override
    public void show() {
        try {
            Log.i("lsw",bean.isForceUpdate()+" -- ");
            setCanceledOnTouchOutside(bean.isForceUpdate());
            if(!bean.isForceUpdate()){
                // 强制更新 外部点击不了，屏蔽返回键
                setOnKeyListener(new OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                        if (keyCode == KeyEvent.KEYCODE_BACK ) {
                            //展示第二个Dialog弹框
                            return true;
                        }
                        return false;
                    }
                });
            }

        }catch (Exception e){
            setCanceledOnTouchOutside(false);
        }

        super.show();
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public void installApk(Context context, File file, String authority) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uriData = null;
        String type = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uriData = FileProvider.getUriForFile(context, authority, file);
        } else {
            uriData = Uri.fromFile(file);
        }
        intent.setDataAndType(uriData, type);
        context.startActivity(intent);


       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = FileProvider.getUriForFile(context, "com.apk.demo.fileprovider", file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }
        try {
            context.startActivity(intent);
        }catch(Exception e){
            e.printStackTrace();
        }*/
        dismiss();


    }

    /**
     * 设置上部图片背景色
     *
     * @param imageResid
     * @return
     */
    public BaseDialog setTopImage(int imageResid) {
        mIv_top.setBackgroundResource(imageResid);
        return this;
    }

    /**
     * 设置底部背景
     *
     * @param imageResid
     * @return
     */
    public BaseDialog setBottomImage(int imageResid) {
        iv_bottom.setBackgroundResource(imageResid);
        return this;
    }

    /**
     * 普通dialog
     *
     * @return
     */
    public BaseDialog setCommonDialog() {
        mIv_top.setVisibility(View.GONE);
        line_top.setVisibility(View.VISIBLE);
        line_bottom.setVisibility(View.VISIBLE);
        return this;
    }

    public BaseDialog setBtnDrawable(int backgroundResid) {
        btnUpdate.setBackgroundResource(backgroundResid);
        return this;
    }


    public BaseDialog setProgressLoadingColor(int color) {
        mFlikerbar.setloadingColor(color);
        return this;
    }

    public BaseDialog setStopColor(int color) {
        mFlikerbar.setStopColor(color);
        return this;
    }

    /**
     * i
     *
     * @param i dp值1
     * @return
     */
    public BaseDialog setTopPadding(int i) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIv_top.getLayoutParams();
        lp.setMargins(0, dpToPx(i), 0, 0);
        mIv_top.setLayoutParams(lp);
        return this;
    }


    public int dpToPx(float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }


    public FlikerProgressBar getmFlikerbar() {
        return mFlikerbar;
    }

    public LinearLayout getmLl_buttons() {
        return mLl_buttons;
    }

    public ImageView getmIv_top() {
        return mIv_top;
    }

    public View getLine_top() {
        return line_top;
    }

    public View getLine_bottom() {
        return line_bottom;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public ImageView getIv_bottom() {
        return iv_bottom;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getVersion_no_tv() {
        return version_no_tv;
    }

    public TextView getTvMsg() {
        return tvMsg;
    }

    public ImageView getmIv_new() {
        return mIv_new;
    }
}