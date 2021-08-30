package com.nicootech.nytnewsmvvm.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Docs implements Parcelable {
    private String web_url;
    private String snippet;
    private String lead_paragraph;
    private List<MultiMedia> multimedia;
    private Headline headline;
    private String _id;
    private Byline byline;
    private String pub_date;

    public Docs(String web_url, String snippet, String lead_paragraph, List<MultiMedia> multimedia,
                Headline headline, String _id, Byline byline, String pub_date) {
        this.web_url = web_url;
        this.snippet = snippet;
        this.lead_paragraph = lead_paragraph;
        this.multimedia = multimedia;
        this.headline = headline;
        this._id = _id;
        this.byline = byline;
        this.pub_date = pub_date;
    }

    public Docs() {
    }


    protected Docs(Parcel in) {
        web_url = in.readString();
        snippet = in.readString();
        lead_paragraph = in.readString();
        multimedia = in.createTypedArrayList(MultiMedia.CREATOR);
        headline = in.readParcelable(Headline.class.getClassLoader());
        _id = in.readString();
        byline = in.readParcelable(Byline.class.getClassLoader());
        pub_date = in.readString();
    }

    public static final Creator<Docs> CREATOR = new Creator<Docs>() {
        @Override
        public Docs createFromParcel(Parcel in) {
            return new Docs(in);
        }

        @Override
        public Docs[] newArray(int size) {
            return new Docs[size];
        }
    };

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLead_paragraph() {
        return lead_paragraph;
    }

    public void setLead_paragraph(String lead_paragraph) {
        this.lead_paragraph = lead_paragraph;
    }

    public List<MultiMedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MultiMedia> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(web_url);
        dest.writeString(snippet);
        dest.writeString(lead_paragraph);
        dest.writeTypedList(multimedia);
        dest.writeParcelable(headline, flags);
        dest.writeString(_id);
        dest.writeParcelable(byline, flags);
        dest.writeString(pub_date);
    }
}
