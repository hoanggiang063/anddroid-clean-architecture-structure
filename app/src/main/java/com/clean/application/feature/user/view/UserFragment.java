package com.clean.application.feature.user.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.clean.application.feature.user.injection.DaggerUserComponent;
import com.clean.application.feature.user.injection.UserModule;
import com.clean.application.feature.user.presenter.UserPresenter;
import com.clean.business.user.info.UserInfo;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cbsahub.projectstructure.R;

public class UserFragment extends Fragment implements UserView {

    @BindView(R.id.btnLoaduser)
    Button mLoadUser;

    @BindView(R.id.btnShowResult)
    TextView mTextResult;

    @Inject
    UserPresenter userPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDagger();
        mLoadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPresenter.onGetUserInfo("59af9772100000fe00db1ec0");
            }
        });
    }

    private void setupDagger() {
        DaggerUserComponent
                .builder()
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void showUserInfo(UserInfo info) {
        mTextResult.setText(info.getName() + "\n" + info.getEmail());
    }

    @Override
    public void showError(Throwable model) {
        mTextResult.setText("Error");
    }

    @Override
    public void showBlockedProfileScreen() {
        mTextResult.setText("This profile is blocked, please contact call center at XXX XXX XXX to unblock");
    }

    @Override
    public void showClosedProfileScreen() {
        mTextResult.setText("This profile is closed, please contact call center at XXX XXX XXX to re-open");
    }
}
