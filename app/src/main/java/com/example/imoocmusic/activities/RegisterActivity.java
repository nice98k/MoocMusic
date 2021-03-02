package com.example.imoocmusic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.imoocmusic.R;
import com.example.imoocmusic.Utils.Userutils;
import com.example.imoocmusic.views.InputView;

/**
 *
 */
public class RegisterActivity extends BaseActivity {
    private InputView mPhone,mPassword,mPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }
    private void initView(){
        initNavBar(true,"注册",false);
        mPhone=fd(R.id.input_phone_register);
        mPassword=fd(R.id.input_passwordR);
        mPasswordConfirm=fd(R.id.input_passwordR_confirm);
    }


    /**
     * @param v 注册按钮点击事件
     *   1. 用户输入合法性验证
     *          1. 用户输入的手机号是不是合法的
     *          2.用户是否输入了密码和确定密码，并且两次输入是否相同
     *          3. 用户输入的手机号是否被注册
*        2. 保存用户输入的手机号和密码 （MD5加密密码）
     */
    public void onRegisterClick(View v){
        String phone=mPhone.getInputStr().trim();
        String password=mPassword.getInputStr().trim();
        String passwordC=mPasswordConfirm.getInputStr().trim();

        boolean res=Userutils.registerUser(this,phone,password,passwordC);

        if(res==true){
            onBackPressed();
        }
        else{
            return ;
        }


    }

}
