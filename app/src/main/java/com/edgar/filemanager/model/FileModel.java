package com.edgar.filemanager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edgar on 2018/10/23.
 */
public class FileModel implements Parcelable {

    private int type;
    private String filePath;

    public FileModel() {}

    protected FileModel(Parcel in) {
        type = in.readInt();
        filePath = in.readString();
    }

    public static final Creator<FileModel> CREATOR = new Creator<FileModel>() {
        @Override
        public FileModel createFromParcel(Parcel in) {
            return new FileModel(in);
        }

        @Override
        public FileModel[] newArray(int size) {
            return new FileModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(filePath);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}