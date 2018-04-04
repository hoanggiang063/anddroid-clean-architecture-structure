package com.clean.business.user.repository;


import com.clean.business.user.info.UserInfo;
import rx.Observable;

public interface UserRepository {
    Observable<UserInfo> user(String userId);
}
