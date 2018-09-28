package com.stay4it.http;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * 文件描述：.
 * <p/>
 * 作者：Created by linfeidie on 2016/11/17
 * <p/>
 * 版本号：stay4it_http
 */
public abstract class AbstractCallback<T> implements ICallback<T> {
    private String path;
    @Override
    public T parse(HttpURLConnection connection,OnProgressUpdatedListener linstener) throws Exception {
         int status = connection.getResponseCode();
        if (status == 200) {
            if(path==null) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[2048];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                is.close();
                out.flush();
                out.close();
                return  bindData(new String(out.toByteArray()));
            }else{
                FileOutputStream out = new FileOutputStream(path);
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[2048];
                int len;
                int currentLen = 0;
                while ((len = is.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    currentLen+=len;
                    linstener.onProgressUpdated(currentLen,connection.getContentLength());
                }
                is.close();
                out.flush();
                out.close();
                return  bindData(path);
            }

        }
        return null;
    }
    @Override
    public T parse(HttpURLConnection connection)throws Exception{
        return  parse(connection,null);
    }

    protected abstract T bindData(String result) throws Exception;;

    public ICallback setCachePath(String path) {
        this.path=path;
        return this;
    }

}
