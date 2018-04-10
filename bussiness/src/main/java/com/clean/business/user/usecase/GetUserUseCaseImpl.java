package com.clean.business.user.usecase;


import com.clean.business.core.exception.BusinessException;
import com.clean.business.core.usecase.BaseUseCase;
import com.clean.business.core.usecase.UseCase;
import com.clean.business.user.callback.UserCallBack;
import com.clean.business.user.info.UserInfo;
import com.clean.business.user.repository.UserRepository;

public class GetUserUseCaseImpl extends BaseUseCase<UserInfo, UserCallBack> implements GetUserUseCase {

    private static final String USER_BLOCKED = "001";
    private static final String USER_CLOSED = "002";
    private UserRepository userRepository;

    public GetUserUseCaseImpl(UserRepository repository) {
        userRepository = repository;
    }

    @Override
    protected boolean handleResponseException(Throwable exception, UserCallBack callBack) {
        if (exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            if (businessException.getCode() == USER_BLOCKED) {
                callBack.onBlockedProfile(businessException);
            } else if (businessException.getCode() == USER_CLOSED) {
                callBack.onClosedProfile(businessException);
            }
            return true;
        }
        return false;
    }

    @Override
    public UseCase buildUseCase(String userID) {
        mObservable = userRepository.user(userID);
        return this;
    }
}
