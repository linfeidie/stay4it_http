package com.stay4it.http;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 文件描述：.
 * <p/>
 * 作者：Created by linfeidie on 2016/11/18
 * <p/>
 * 版本号：stay4it_http
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {
    @Override
    protected T bindData(String result)throws Exception {
        JSONObject json=new JSONObject(result);
        JSONObject data=json.optJSONObject("data");
        Gson gson=new Gson();
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return gson.fromJson(data.toString(),type);
    }
}
