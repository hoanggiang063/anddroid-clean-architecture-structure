package com.clean.repository.base;


public abstract class BaseInfoMapper<T, R> {
    public abstract T transform(R value);
}
