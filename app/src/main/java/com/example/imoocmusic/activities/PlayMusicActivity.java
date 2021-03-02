package com.example.imoocmusic.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.imoocmusic.Models.MusicModel;
import com.example.imoocmusic.R;
import com.example.imoocmusic.helps.RealmHelp;
import com.example.imoocmusic.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {
    public static final String MUSIC_ID="musicId";

    private ImageView mIvBg;
    private PlayMusicView mPlayMusicView;

    private TextView mTvName,mTvAuthor;
    private String mMusicId;
    private RealmHelp mRealmHelp;
    private MusicModel mMusicModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

//        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();
    }

    private void initData(){
        mMusicId=getIntent().getStringExtra(MUSIC_ID);
        mRealmHelp=new RealmHelp();
        mMusicModel=mRealmHelp.getMusic(mMusicId);

    }


    private void initView(){
        mIvBg=fd(R.id.iv_bg);
        mPlayMusicView=fd(R.id.play_music_view);
        mTvName=fd(R.id.tv_name);
        mTvAuthor=fd(R.id.tv_author);



        Glide.with(this)
                .load(mMusicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(mIvBg);

        mTvAuthor.setText(mMusicModel.getAuthor());
        mTvName.setText(mMusicModel.getName());

//        mPlayMusicView.setMusicIcon(mMusicModel.getPoster());

        mPlayMusicView.setMusic(mMusicModel);
        mPlayMusicView.PlayMusic();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayMusicView.destory();
        mRealmHelp.close();
    }

    //    后退按钮点击事件
    public void onBackClick(View view){
        onBackPressed();
    }
}
