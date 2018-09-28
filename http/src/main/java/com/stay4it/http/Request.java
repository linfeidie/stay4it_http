package com.stay4it.http;

import java.util.Map;

/**
 * Created by Stay on 24/6/15.
 * Powered by www.stay4it.com
 */
public class Request {
    public ICallback iCallback;
    public void setCallback(ICallback iCallback) {
        this.iCallback=iCallback;
    }

    public boolean enableProgressUpdated=false;

    public enum RequestMethod {GET, POST, PUT, DELETE}


    public String url;
    public String content;
    public Map<String, String> headers;

    public RequestMethod method;

    public Request(String url,RequestMethod method){
        this.url = url;
        this.method = method;
    }

    public Request(String url){
        this.url = url;
        this.method = RequestMethod.GET;
    }
}
