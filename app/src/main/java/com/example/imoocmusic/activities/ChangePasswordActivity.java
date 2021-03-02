package com.example.imoocmusic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.imoocmusic.R;
import com.example.imoocmusic.Utils.Userutils;
import com.example.imoocmusic.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView mOldPassWord,mPassword,mPassWordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
        initNavBar(true,"修改密码",false);

        mOldPassWord=fd(R.id.input_oldPassword_ChangePassword);
        mPassword=fd(R.id.input_password_ChangePassword);
        mPassWordConfirm=fd(R.id.input_password_ChangePassword_confirm);

    }

    public void onChangePassWordClick(View v){
        String oldPassword=mOldPassWord.getInputStr();
        String password=mPassword.getInputStr();
        String passwordConfirm=mPassWordConfirm.getInputStr();

        boolean result=Userutils.ChangePassWord(this,oldPassword,password,passwordConfirm);
        if(!result){
            return ;
        }
        Userutils.logout(this);
    }
}
