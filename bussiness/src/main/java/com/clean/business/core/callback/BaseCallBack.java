package com.clean.business.core.callback;


public interface BaseCallBack<T, R> {
    void onStart();

    public void onError(R model);

    public void onSuccess(T info);

    public void onComplete();
}
