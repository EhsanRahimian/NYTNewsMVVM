package com.nicootech.nytnewsmvvm.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ResponseBean implements Parcelable {
    private List<Docs> docs;
    private Meta meta;

    public ResponseBean(List<Docs> docs, Meta meta) {
        this.docs = docs;
        this.meta = meta;
    }

    public ResponseBean() {
    }

    protected ResponseBean(Parcel in) {
        docs = in.createTypedArrayList(Docs.CREATOR);
        meta = in.readParcelable(Meta.class.getClassLoader());
    }

    public static final Creator<ResponseBean> CREATOR = new Creator<ResponseBean>() {
        @Override
        public ResponseBean createFromParcel(Parcel in) {
            return new ResponseBean(in);
        }

        @Override
        public ResponseBean[] newArray(int size) {
            return new ResponseBean[size];
        }
    };

    public List<Docs> getDocs() {
        return docs;
    }

    public void setDocs(List<Docs> docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "docs=" + docs +
                ", meta=" + meta +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(docs);
        dest.writeParcelable(meta, flags);
    }
}
