package com.stay4it.http;

import android.os.AsyncTask;

import java.net.HttpURLConnection;

/**
 * 文件描述：.
 * <p/>
 * 作者：Created by linfeidie on 2016/11/14
 * <p/>
 * 版本号：stay4it_http
 */
public class RequestTask extends AsyncTask<Void,Integer,Object> {
    Request request;

    public RequestTask(Request request) {
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... params) {
        try {
            HttpURLConnection connection=HttpUrlConnectionUtil.execute(request);
            if(request.enableProgressUpdated) {
                return request.iCallback.parse(connection,new OnProgressUpdatedListener(){
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        publishProgress(curLen,totalLen);
                    }
                });
            }else{
                return request.iCallback.parse(connection);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o instanceof Exception) {
            request.iCallback.onFailure((Exception)o);
        }else {
            request.iCallback.onSuccess(o);
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.iCallback.onProgressUpdated(values[0],values[1]);
    }
}
