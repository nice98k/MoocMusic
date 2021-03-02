package com.example.imoocmusic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imoocmusic.R;
import com.example.imoocmusic.Utils.Userutils;
import com.example.imoocmusic.views.InputView;

public class LoginActivity extends BaseActivity {
    private InputView mInputPhone,mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }
    /**
     * @description:    初始化View
     * @param:          
     * @return:         
     * @author:         nice
     * @time:           2021/2/20 17:25
     */
    private void initView(){
        initNavBar(false,"登陆",false);
        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
    }
    //注册点击事件
    public void onRegisterClick(View v){
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);


    }

    /**
     * 登录
     */
    public void onCommitClick(View v){
        String phone=mInputPhone.getInputStr();
        String password=mInputPassword.getInputStr();

//      验证用户和密码
        if(!Userutils.validateLogin(this,phone,password)){
            return ;
        }
//      跳转到主页
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
