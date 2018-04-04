package com.clean.application.feature.user.injection;


import com.clean.business.user.repository.UserRepository;
import com.clean.business.user.usecase.GetUserUseCase;
import com.clean.business.user.usecase.GetUserUseCaseImpl;
import com.clean.repository.repository.UserRepositoryImpl;
import com.clean.repository.service.UserService;
import com.clean.application.feature.user.presenter.UserPresenter;
import com.clean.application.feature.user.presenter.UserPresenterImpl;
import com.clean.application.feature.user.view.UserView;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@PerView
public class UserModule {
    private UserView view;


    public UserModule(UserView view) {
        this.view = view;
    }

    @Provides
    @PerView
    UserView provideView() {
        return view;
    }

    @Provides
    @PerView
    UserPresenter provideUserPresenter(GetUserUseCase userUseCase, UserView view) {
        return new UserPresenterImpl(userUseCase, view);
    }

    @Provides
    @PerView
    GetUserUseCase provideGetUserUseCase(UserRepository repository) {
        return new GetUserUseCaseImpl(repository);
    }

    @Provides
    @PerView
    UserRepository provideVerifyOtpUseCase(Retrofit repository) {
        return new UserRepositoryImpl(repository.create(UserService.class));
    }

    @Provides
    @PerView
    OkHttpClient provideOkHttpClient2() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(null);
        return builder.build();
    }

    @Provides
    @PerView
    String provideURL() {

        return " http://www.mocky.io/v2/";
    }

    @Provides
    @PerView
    Retrofit provideApiAdapter(String endpoint,
                               OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
