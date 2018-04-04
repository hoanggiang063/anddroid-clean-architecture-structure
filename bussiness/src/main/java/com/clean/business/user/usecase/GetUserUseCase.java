package com.clean.business.user.usecase;


import com.clean.business.core.usecase.UseCase;
import com.clean.business.user.callback.UserCallBack;
import com.clean.business.user.info.UserInfo;

public interface GetUserUseCase extends UseCase<UserInfo, UserCallBack> {
    public UseCase buildUseCase(String userID);
}
