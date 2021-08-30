package com.nicootech.nytnewsmvvm.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nicootech.nytnewsmvvm.models.ResponseBean;

public class ArticleSearchResponse {


        @SerializedName("status")
        @Expose()
        private String status;

        @SerializedName("copyright")
        @Expose()
        private String copyright;

        @SerializedName("response")
        @Expose()
        private ResponseBean response;

        public String getStatus() {
            return status;
        }

        public String getCopyright() {
            return copyright;
        }

        public ResponseBean getResponse() {
            return response;
        }

        @Override
        public String toString() {
            return "ArticleSearchResponse{" +
                    "status='" + status + '\'' +
                    ", copyright='" + copyright + '\'' +
                    ", response=" + response +
                    '}';
        }

}
