package com.clean.business.user.callback;


import com.clean.business.core.callback.BaseCallBack;
import com.clean.business.core.exception.BusinessException;

public interface UserCallBack<T, R> extends BaseCallBack<T, R> {
    public void onBlockedProfile(BusinessException businessException);

    public void onClosedProfile(BusinessException businessException);
}
