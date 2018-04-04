package com.clean.application.feature.user.view;


import com.clean.business.user.info.UserInfo;

public interface UserView {
    public void showUserInfo(UserInfo info);

    public void showError(Throwable model);

    public void showBlockedProfileScreen();

    public void showClosedProfileScreen();
}
