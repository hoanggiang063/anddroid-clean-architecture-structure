package com.clean.repository.repository;


import com.clean.business.user.info.UserInfo;
import com.clean.business.user.repository.UserRepository;
import com.clean.repository.base.BaseInfoMapper;
import com.clean.repository.base.BaseRepository;
import com.clean.repository.model.UserModel;
import com.clean.repository.service.UserService;
import rx.Observable;
import rx.functions.Func1;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    private UserService mService;

    public UserRepositoryImpl(UserService service) {
        mService = service;
    }

    @Override
    public Observable<UserInfo> user(String userId) {
        return processRequestV2(mService.getUserDetail(userId)
                .map(new Func1<UserModel, UserInfo>() {
                    @Override
                    public UserInfo call(UserModel userInfo) {
                        return new BaseInfoMapper<UserInfo, UserModel>() {
                            @Override
                            public UserInfo transform(UserModel value) {
                                UserInfo info = new UserInfo();
                                info.setId(value.getId());
                                info.setEmail(value.getEmail());
                                info.setName(value.getName());
                                return info;
                            }
                        }.transform(userInfo);
                    }
                }));
    }
}
