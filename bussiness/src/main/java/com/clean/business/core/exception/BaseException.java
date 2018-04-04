package com.clean.business.core.exception;


public abstract class BaseException extends RuntimeException {

    private String mCode;
    private String mMessage;

    public String getCode() {
        return mCode;
    }

    public void setCode(String mCode) {
        this.mCode = mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
