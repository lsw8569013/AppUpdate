# AppUpdate 

## Android app 升级 下载在一个 dialog  中完成

![image](https://github.com/lsw8569013/AppUpdate/blob/master/20180802_181902.gif ) 


### maven { url 'https://jitpack.io' }

### implementation 'com.github.lsw8569013:AppUpdate:1.1.1'

## //-----------------------------

### 添加文件权限 

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //7.0 查询文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }
}


  mBean.setFileName(versionBean.getBuildName()+versionBean.getBuildVersion());
  mBean.setCONTENT(versionBean.getBuildUpdateDescription());
  mBean.setTITLE(versionBean.getBuildName());
  mBean.setDOWNLOAD_URL("https://github.com/lsw8569013/AppUpdate/blob/master/app-release.apk");
  showUPdateDialog(mBean);

if(!new PermissionsCheckerUtil(this).lacksPermissionOps(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    999);
        }else{
            showDialog();
        }

private  void showDialog(){
        versionDialog = new BaseDialog(this, R.style.BaseDialog, R.layout.custom_dialog_two_layout, mBean);
        versionDialog.show();
    }
