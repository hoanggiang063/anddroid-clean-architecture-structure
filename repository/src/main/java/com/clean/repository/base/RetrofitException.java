package com.clean.repository.base;


import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitException extends RuntimeException {
    private final String url;
    private final Response response;
    private final RetrofitException.Kind kind;
    private final Retrofit retrofit;

    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RetrofitException(message, url, response, RetrofitException.Kind.HTTP, (Throwable)null, retrofit);
    }

    public static RetrofitException networkError(String url, Response response, IOException exception) {
        return new RetrofitException(exception.getMessage(), url, response, RetrofitException.Kind.NETWORK, exception, (Retrofit)null);
    }

    public static RetrofitException unexpectedError(String url, Response response, Throwable exception) {
        return new RetrofitException(exception.getMessage(), url, response, RetrofitException.Kind.UNEXPECTED, exception, (Retrofit)null);
    }

    RetrofitException(String message, String url, Response response, RetrofitException.Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    public String getUrl() {
        return this.url;
    }

    public Response getResponse() {
        return this.response;
    }

    public RetrofitException.Kind getKind() {
        return this.kind;
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }

    public <T> T getErrorBodyAs(Class<T> type) throws IOException {
        if(this.response != null && this.response.errorBody() != null) {
            Converter converter = this.retrofit.responseBodyConverter(type, new Annotation[0]);
            return (T) converter.convert(this.response.errorBody());
        } else {
            return null;
        }
    }

    public static enum Kind {
        NETWORK,
        HTTP,
        UNEXPECTED;

        private Kind() {
        }
    }
}

