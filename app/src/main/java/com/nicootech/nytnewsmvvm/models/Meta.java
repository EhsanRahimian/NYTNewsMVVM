package com.nicootech.nytnewsmvvm.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Meta implements Parcelable {

    private int hits;
    private int offset;
    private int time;

    public Meta(int hits, int offset, int time) {
        this.hits = hits;
        this.offset = offset;
        this.time = time;
    }

    public Meta() {
    }


    protected Meta(Parcel in) {
        hits = in.readInt();
        offset = in.readInt();
        time = in.readInt();
    }

    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };

    public void setHits(int hits){
        this.hits = hits;
    }

    public int getHits(){
        return hits;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

    public int getOffset(){
        return offset;
    }

    public void setTime(int time){
        this.time = time;
    }

    public int getTime(){
        return time;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "hits=" + hits +
                ", offset=" + offset +
                ", time=" + time +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hits);
        dest.writeInt(offset);
        dest.writeInt(time);
    }
}
