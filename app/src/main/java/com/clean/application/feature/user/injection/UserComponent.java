package com.clean.application.feature.user.injection;

import com.clean.application.feature.user.view.UserFragment;
import dagger.Component;

@PerView
@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(UserFragment fragment);
}
