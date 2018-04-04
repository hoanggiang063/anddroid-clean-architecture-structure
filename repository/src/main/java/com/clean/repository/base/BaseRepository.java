package com.clean.repository.base;

import android.util.Log;

import com.clean.business.core.exception.BusinessException;
import com.clean.business.core.exception.GenericException;
import com.clean.business.core.exception.IOException;
import com.clean.business.core.exception.NetworkException;
import com.clean.business.core.exception.UnknownException;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class BaseRepository {

    public <T> Observable<T> processRequestV2(final Observable<T> observableRequest) {
        return observableRequest.onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {
                return getObservableOnErrorV2(throwable);
            }
        });
    }

    private <T> Observable<T> getObservableOnErrorV2(final Throwable throwable) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                if (throwable instanceof HttpException) {
                    Response response = ((HttpException) throwable).response();
                    if (isBusinessException(response)) {
                        ErrorModel errorModel = new Gson().fromJson(getStringFromResponseBody(response.errorBody()), ErrorModel.class);
                        BusinessException exception = new BusinessException();
                        exception.setCode(errorModel.getErrorCode());
                        exception.setMessage(errorModel.getErrorMessage());
                        subscriber.onError(exception);
                    } else {
                        subscriber.onError(new GenericException());
                    }
                } else if (throwable instanceof SocketTimeoutException) {
                    subscriber.onError(new NetworkException());
                } else if (throwable instanceof IOException) {
                    subscriber.onError(new IOException());
                } else {
                    subscriber.onError(new UnknownException());
                }
            }
        });
    }

    protected boolean isBusinessException(Response response) {
        return HttpStatus.valueOf(response.code()) == HttpStatus.BAD_REQUEST && response.errorBody() != null;
    }

    private String getStringFromResponseBody(ResponseBody responseBody) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (java.io.IOException e) {
            Log.e("example", "Get string error");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (java.io.IOException e) {
                    Log.e("example", "Get string error");
                }
            }
        }
        return sb.toString();
    }
}
