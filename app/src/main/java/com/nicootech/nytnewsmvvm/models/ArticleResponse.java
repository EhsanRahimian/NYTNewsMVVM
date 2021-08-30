package com.nicootech.nytnewsmvvm.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ArticleResponse implements Parcelable {

    private String status;
    private String copyright;
    private ResponseBean response;
public ArticleResponse(String status, String copyright,ResponseBean response) {
        this.status = status;
        this.copyright = copyright;
        this.response = response;
        }

public ArticleResponse() {
        }

public String getStatus() {
        return status;
        }

public void setStatus(String status) {
        this.status = status;
        }

public String getCopyright() {
        return copyright;
        }

public void setCopyright(String copyright) {
        this.copyright = copyright;
        }

public ResponseBean getResponse() {
        return response;
        }

public void setResponse(ResponseBean response) {
        this.response = response;
        }

@Override
public int describeContents() {
        return 0;
        }

@Override
public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(copyright);
        }
protected ArticleResponse(Parcel in) {
        status = in.readString();
        copyright = in.readString();
        }

public static final Creator<ArticleResponse> CREATOR = new Creator<ArticleResponse>() {
@Override
public ArticleResponse createFromParcel(Parcel in) {
        return new ArticleResponse(in);
        }

@Override
public ArticleResponse[] newArray(int size) {
        return new ArticleResponse[size];
        }
        };

@Override
public String toString() {
        return "ArticleResponse{" +
        "status='" + status + '\'' +
        ", copyright='" + copyright + '\'' +
        ", response=" + response +
        '}';
        }
        }
