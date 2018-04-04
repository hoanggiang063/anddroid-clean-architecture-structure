package com.clean.business.core.usecase;


import java.util.HashMap;

import com.clean.business.core.callback.BaseCallBack;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

public interface UseCase<T, R extends BaseCallBack> {

    UseCase buildUseCase(HashMap<String, String> headers);

    UseCase subscribeOn(Scheduler scheduler);

    UseCase observeOn(Scheduler scheduler);

    Subscription excuseBySubscriber(Subscriber<T> subscriber);

    Subscription excuseByCallBack(R mCallBack);
}
