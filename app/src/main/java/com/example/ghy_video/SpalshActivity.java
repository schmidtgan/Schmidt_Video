package com.example.ghy_video;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SpalshActivity extends Activity {

    private SharedPreferences mSharePreferences;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GO_HOME:
                    startHomeActivity();
                    break;
                case GO_GUIDE:
                    startGuideActivity();
                    break;
                default:
                    break;

            }
        }
    };
    private static final int GO_HOME =0;
    private static final int GO_GUIDE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharePreferences=getSharedPreferences("config",MODE_PRIVATE);
        init();
    }

    private void init() {
        Log.e("Splash","init");
        boolean isFirstIn= mSharePreferences.getBoolean("misFirstIn",true);
        if (isFirstIn){
            handler.sendEmptyMessageDelayed(GO_GUIDE,2000);
            Log.e("Spalsh","inGuIDE");
        }else{
            handler.sendEmptyMessageDelayed(GO_HOME,2000);
            Log.e("Spalsh","inHome");
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(SpalshActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void startGuideActivity() {
        Intent intent = new Intent(SpalshActivity.this,GuideActivity.class);
        startActivity(intent);
        finish();
    }
}
