package com.stay4it.test_http;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.stay4it.http.FileCallback;
import com.stay4it.http.Request;
import com.stay4it.http.RequestTask;

import java.io.File;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private Button mRunOnSubThreadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRunOnSubThreadBtn = (Button)findViewById(R.id.mRunOnSubThreadBtn);
        mRunOnSubThreadBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mRunOnSubThreadBtn:
                //testHttpPostOnSubThread();
                testHttpPostOnSubThreadForDownload();
                break;
        }
    }

  /*  private void testHttpPostOnSubThread() {
        String url = "http://api.stay4it.com/v1/public/core/?service=user.login";
        String content = "account=stay4it&password=123456";
        Request request = new Request(url, Request.RequestMethod.POST);
        request.content=content;
        request.setCallback(new JsonCallback<User>(){
            @Override
            public void onSuccess(User result) {
                Log.e("linfd", "testHttpPost return:" + result);
            }

            @Override
            public void onFailure(Exception e) {

            }

        });
        RequestTask task=new RequestTask(request);
        task.execute();
    }*/
    private void testHttpPostOnSubThreadForDownload() {
        String url = "http://api.stay4it.com/uploads/test.jpg";
        Request request = new Request(url, Request.RequestMethod.GET);
        String path = Environment.getExternalStorageDirectory() + File.separator + "demo.txt";
        request.setCallback(new FileCallback(){
            @Override
            public void onSuccess(String result) {
                Log.e("linfd", "testHttpPost return:" + result);
            }

            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onProgressUpdated(Integer value, Integer value1) {
                Log.e("linfd",value+"---"+value1);
            }
        }.setCachePath(path));
        request.enableProgressUpdated=true;
        RequestTask task=new RequestTask(request);
        task.execute();
    }

}
