package com.clean.repository.service;

import com.clean.repository.model.UserModel;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface UserService {

    @GET("{id}")
    Observable<UserModel> getUserDetail(@Path("id") String id);
}
