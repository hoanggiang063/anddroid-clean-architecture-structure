package com.clean.business.core.usecase;


import com.clean.business.core.callback.BaseCallBack;

import java.util.HashMap;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class BaseUseCase<T, R extends BaseCallBack> implements UseCase<T, R> {

    protected Observable mObservable;
    protected Scheduler observeOn = AndroidSchedulers.mainThread();
    protected Scheduler subscribeOn = Schedulers.io();

    @Override
    public UseCase subscribeOn(Scheduler scheduler) {
        subscribeOn = scheduler;
        return this;
    }

    @Override
    public UseCase observeOn(Scheduler scheduler) {
        observeOn = scheduler;
        return this;
    }

    @Override
    public UseCase buildUseCase(HashMap<String, String> headers) {
        return this;
    }

    @Override
    public Subscription excuseBySubscriber(Subscriber subscriber) {
        return mObservable
                .observeOn(observeOn)
                .subscribeOn(subscribeOn)
                .subscribe(subscriber);
    }

    @Override
    public Subscription excuseByCallBack(final R mCallBack) {
        return mObservable
                .observeOn(observeOn)
                .subscribeOn(subscribeOn)
                .subscribe(new Subscriber<T>() {

                    @Override
                    public void onStart() {
                        mCallBack.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        mCallBack.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!handleResponseException(e, mCallBack)) {
                            mCallBack.onComplete();
                            mCallBack.onError(e);

                        } else {
                            mCallBack.onComplete();
                        }
                    }

                    @Override
                    public void onNext(T t) {
                        mCallBack.onSuccess(t);
                        mCallBack.onComplete();
                    }
                });
    }

    protected abstract boolean handleResponseException(Throwable e, R callBack);

}
