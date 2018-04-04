package com.clean.application.feature.user.presenter;


import com.clean.business.core.exception.BusinessException;
import com.clean.business.user.callback.UserCallBack;
import com.clean.business.user.info.UserInfo;
import com.clean.business.user.usecase.GetUserUseCase;
import com.clean.application.feature.user.view.UserView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPresenterImpl implements UserPresenter {

    private GetUserUseCase userUseCase;
    private UserView mView;

    public UserPresenterImpl(GetUserUseCase usecase, UserView view) {
        userUseCase = usecase;
        mView = view;
    }

    @Override
    public void onGetUserInfo(String userID) {
        userUseCase
                .buildUseCase(userID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .excuseByCallBack(new UserCallBack<UserInfo, Throwable>() {

                    @Override
                    public void onBlockedProfile(BusinessException businessException) {
                        mView.showBlockedProfileScreen();
                    }

                    @Override
                    public void onClosedProfile(BusinessException businessException) {
                        mView.showClosedProfileScreen();
                    }

                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onError(Throwable model) {
                        mView.showError(model);
                    }

                    @Override
                    public void onSuccess(UserInfo info) {
                        mView.showUserInfo(info);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
