package com.clean.repository.base;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.clean.business.core.exception.UnknownException;
import okhttp3.ResponseBody;

public abstract class BaseEntityMapper<T> {

    public abstract Class<T> getMyType();

    public T transform(ResponseBody response) {
        Gson gson = new Gson();

        InputStream inputStream = response.byteStream();
        try {

            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader streamReader = new BufferedReader(reader);
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            T result = gson.fromJson(responseStrBuilder.toString(), getMyType());
            return result;
        } catch (Exception e) {
            throw new UnknownException();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
