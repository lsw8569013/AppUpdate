package lsw.update;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: Created by lsw on 2018/8/2 11:25
 * @description:
 */
public class UpdateBean implements Parcelable {

    private  String TITLE = "title", CONTENT = "content", DOWNLOAD_URL = "download_url" ,fileName;
    private  boolean forceUpdate = true;



    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getDOWNLOAD_URL() {
        return DOWNLOAD_URL;
    }

    public void setDOWNLOAD_URL(String DOWNLOAD_URL) {
        this.DOWNLOAD_URL = DOWNLOAD_URL;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TITLE);
        dest.writeString(this.CONTENT);
        dest.writeString(this.DOWNLOAD_URL);
        dest.writeString(this.fileName);
        dest.writeByte(this.forceUpdate ? (byte) 1 : (byte) 0);
    }

    public UpdateBean() {
    }

    protected UpdateBean(Parcel in) {
        this.TITLE = in.readString();
        this.CONTENT = in.readString();
        this.DOWNLOAD_URL = in.readString();
        this.fileName = in.readString();
        this.forceUpdate = in.readByte() != 0;
    }

    public static final Creator<UpdateBean> CREATOR = new Creator<UpdateBean>() {
        @Override
        public UpdateBean createFromParcel(Parcel source) {
            return new UpdateBean(source);
        }

        @Override
        public UpdateBean[] newArray(int size) {
            return new UpdateBean[size];
        }
    };
}
