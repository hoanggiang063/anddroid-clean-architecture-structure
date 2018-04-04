package com.clean.business.core.usecase;


import rx.Subscription;

public class NonSubscription implements Subscription {
    @Override
    public void unsubscribe() {

    }

    @Override
    public boolean isUnsubscribed() {
        return true;
    }
}
