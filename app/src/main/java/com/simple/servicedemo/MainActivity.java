package com.simple.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent;//MyService
    private Intent intent2;//MiddleService
    private MiddleService.MyBinder myBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (MiddleService.MyBinder) iBinder;
            myBinder.setData("这是修改后的信息");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,MyService.class);
        intent2 = new Intent(this,MiddleService.class);
    }

    public void start(View view){
        startService(intent2);
    }



    public void stop(View view){
        stopService(intent2);
    }

    public void bind(View view){
        bindService(intent2,serviceConnection,BIND_AUTO_CREATE);
    }



    public void unbind(View view){
        unbindService(serviceConnection);
    }
}
