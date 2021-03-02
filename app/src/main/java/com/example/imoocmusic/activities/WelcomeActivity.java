package com.example.imoocmusic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.UserPresenceUnavailableException;
import android.util.Log;

import com.example.imoocmusic.R;
import com.example.imoocmusic.Utils.Userutils;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    /**
     * @description:    
     * @param:          
     * @return:         
     * @author:         nice
     * @time:           2021/2/20 16:39
     */
    private void init() {
        final boolean isLogin= Userutils.validateUserLogin(this);
        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "run: "+Thread.currentThread());
                if(isLogin){
                    ToMain();
                }
                else{
                    ToLogin();
                }


            }
        },3*1000);




    }

    /**
     * @description:    cmt
     * @param:          
     * @return:         
     * @author:         nice
     * @time:           2021/2/20 17:13
     */
    private void ToLogin() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void ToMain() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
