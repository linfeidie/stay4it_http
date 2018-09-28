package com.stay4it.http;

/**
 * 文件描述：.
 * <p/>
 * 作者：Created by linfeidie on 2016/11/18
 * <p/>
 * 版本号：stay4it_http
 */
public abstract class XMLCallback<T> extends AbstractCallback<T> {
    @Override
    protected T bindData(String result) throws Exception {
        return null;
    }
}
