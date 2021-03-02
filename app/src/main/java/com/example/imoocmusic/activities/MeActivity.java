package com.example.imoocmusic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.imoocmusic.R;
import com.example.imoocmusic.Utils.Userutils;
import com.example.imoocmusic.helps.UserHelp;

public class MeActivity extends BaseActivity {
    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
        mTvUser=fd(R.id.tv_user);
        mTvUser.setText("用户名:"+ UserHelp.getInstance().getPhone());
    }
// 退出登录
    public void onLogoutClick(View view) {
        Userutils.logout(this);

    }
// 修改密码点击事件
    public void onChangeClick(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));
    }
}
