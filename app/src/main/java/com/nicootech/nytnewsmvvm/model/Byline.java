package com.nicootech.nytnewsmvvm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Byline implements Parcelable {
    private String original;

    public Byline(String original) {
        this.original = original;
    }

    public Byline() {
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public static Creator<Byline> getCREATOR() {
        return CREATOR;
    }

    protected Byline(Parcel in) {
        original = in.readString();
    }

    public static final Creator<Byline> CREATOR = new Creator<Byline>() {
        @Override
        public Byline createFromParcel(Parcel in) {
            return new Byline(in);
        }

        @Override
        public Byline[] newArray(int size) {
            return new Byline[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original);
    }

    @Override
    public String toString() {
        return "Byline{" +
                "original='" + original + '\'' +
                '}';
    }
}
