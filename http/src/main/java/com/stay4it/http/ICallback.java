package com.stay4it.http;

import java.net.HttpURLConnection;

/**
 * 文件描述：.
 * <p/>
 * 作者：Created by linfeidie on 2016/11/14
 * <p/>
 * 版本号：stay4it_http
 */
public interface ICallback<T> {
    void onSuccess(T result);
    void onFailure(Exception e);
    T parse(HttpURLConnection connection,OnProgressUpdatedListener listener) throws Exception;
    T parse(HttpURLConnection connection) throws Exception;
    void onProgressUpdated(Integer value, Integer value1);
}
