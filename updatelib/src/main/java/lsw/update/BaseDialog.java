package lsw.update;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import lsw.updatelib.R;
import okhttp3.Call;


/**
 * @author lsw
 * @date  18/8/2
 */
public class BaseDialog extends Dialog implements DialogInterface.OnDismissListener {
    private  Context context;
    private  UpdateBean bean;
    private int res;
    private FlikerProgressBar mFlikerbar;
    private LinearLayout mLl_buttons;

//    public BaseDialog(Context context, int theme, int res) {
//        super(context, theme);
//        //
//        setContentView(res);
//        init();
//        this.res = res;
//        setCanceledOnTouchOutside(false);
//    }

    public BaseDialog(Context context, int theme, int res,UpdateBean bean) {
        super(context, theme);
        this.context = context;
        //
        setContentView(res);
        this.bean = bean;
        init();
        this.res = res;
        setCanceledOnTouchOutside(false);

    }

    private void init() {
        mLl_buttons = (LinearLayout) findViewById(R.id.ll_buttons);
        mFlikerbar = (FlikerProgressBar) findViewById(R.id.flikerbar);

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        TextView tvMsg = (TextView) findViewById(R.id.tv_msg);
        Button btnUpdate = (Button) findViewById(R.id.version_dialog_commit);
        Button version_dialog_next = (Button) findViewById(R.id.version_dialog_next);
        if( bean != null && !bean.isForceUpdate()){
            version_dialog_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            version_dialog_next.setVisibility(View.VISIBLE);
        }else {
            version_dialog_next.setVisibility(View.GONE);
        }

        //设置dismiss listener 用于强制更新,dimiss会回调dialogDismiss方法
        setOnDismissListener(this);
        //可以使用之前从service传过来的一些参数比如：title。msg，downloadurl，parambundle
        tvTitle.setText(bean.getTITLE());
        tvMsg.setText(bean.getCONTENT());
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
        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+bean.getTITLE());
        if(!fileDir.exists()){
            fileDir.mkdirs();
            Log.e("update",fileDir.getAbsolutePath()+"-- mkdir ok");
        }

        OkHttpUtils//
                .get()//
                .url(bean.getDOWNLOAD_URL())//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), bean.getFileName()) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        mFlikerbar.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context,"下载失败！"+e.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.i("update",e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        if(response.isFile() && response.exists()){
                            Log.i("update","download success ---");
                            //安装 apk
                            installApk(context,response,getContext().getPackageName() + ".fileProvider");
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

    /**
     * 安装apk
     * @param context
     * @param file
     */
    public void installApk(Context context,File file,String authority){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uriData = null;
        String type = "application/vnd.android.package-archive";
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            uriData = FileProvider.getUriForFile(context, authority, file);
//        }else{
            uriData = Uri.fromFile(file);
//        }
        intent.setDataAndType(uriData, type);
        context.startActivity(intent);
        dismiss();
    }

}