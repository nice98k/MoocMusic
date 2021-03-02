package com.example.imoocmusic.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.example.imoocmusic.R;

public class BaseActivity extends Activity {
    private TextView mTitle;
    private ImageView mIvBack,mIvMe;

    protected <T extends View> T fd(int id){
        return findViewById(id);
    }


    protected void initNavBar(boolean isShowBack,String Title,boolean isShowMe){
        mIvBack=fd(R.id.iv_back);
        mTitle=fd(R.id.iv_navBar_title);
        mIvMe=fd(R.id.iv_me);

        mIvBack.setVisibility(isShowBack?View.VISIBLE:View.GONE);
        mIvMe.setVisibility(isShowMe?View.VISIBLE:View.GONE);
        mTitle.setText(Title);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this,MeActivity.class));
            }
        });
    }


}
