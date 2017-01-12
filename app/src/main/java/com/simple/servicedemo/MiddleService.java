package com.simple.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MiddleService extends Service {
    private String data = "默认信息";
    private MyBinder myBinder = new MyBinder();
    private boolean running = true;
    public MiddleService() {

    }

    @Override
    public void onCreate() {
        Log.d("MiddleService","onCreate");
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (running){
                    Log.d("MiddleService",data);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MiddleService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("MiddleService","onDestroy");
        running = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return  myBinder;
    }

    class MyBinder extends Binder{
        public void setData(String string){
            MiddleService.this.data = string;
        }
    }
}
