package com.stay4it.test_http;

/**
 * 文件描述：.
 * <p/>
 * 作者：Created by linfeidie on 2016/11/17
 * <p/>
 * 版本号：stay4it_http
 */
public class User {
    public String id;
    public String account;
    public String email;
    public String username;
    public String token;

    @Override
    public String toString() {
        return username + " : " + email;
    }
}
