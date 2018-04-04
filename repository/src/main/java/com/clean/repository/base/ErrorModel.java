package com.clean.repository.base;

import com.google.gson.annotations.SerializedName;

public class ErrorModel {

    private int mHttpCode;
    @SerializedName("errorCode")
    private String mErrorCode;

    @SerializedName("errorMessage")
    private String mErrorMessage;

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String errorCode) {
        this.mErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

    public int getHttpCode() {
        return mHttpCode;
    }

    public void setHttpCode(int httpCode) {
        this.mHttpCode = httpCode;
    }

}
