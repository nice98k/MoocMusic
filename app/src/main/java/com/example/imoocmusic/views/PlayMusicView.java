package com.example.imoocmusic.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imoocmusic.Models.MusicModel;
import com.example.imoocmusic.R;
import com.example.imoocmusic.helps.MediaPlayerHelp;
import com.example.imoocmusic.services.MusicService;


/******
 ******/
public class PlayMusicView extends FrameLayout {
//    private MediaPlayerHelp mMediaPlayerHelp;
    private Context mContext;
    private View mView;
    private boolean isPlaying,IsBindService;

    private ImageView mIvIcon,mIvNeedle,mIvPlay;
    private FrameLayout mFramelayout;
    private MusicModel mMusicModel;

    private MusicService.MusicBind mMusicBind;
    private Intent MyServiceIntent;
    private Animation mMusic_Play,mStartNeedle,mStopNeedle;

    public void initView(Context context){
        mContext=context;

        mView=LayoutInflater.from(mContext).inflate(R.layout.play_music,this,false);

        mIvIcon=mView.findViewById(R.id.iv_icon);
        mFramelayout=mView.findViewById(R.id.Fl_playMusic);
        mIvNeedle=mView.findViewById(R.id.iv_needle);
        mIvPlay=mView.findViewById(R.id.iv_play);


        mMusic_Play= AnimationUtils.loadAnimation(mContext,R.anim.play_music_animation);
        mStartNeedle= AnimationUtils.loadAnimation(mContext,R.anim.play_needle_anim);
        mStopNeedle= AnimationUtils.loadAnimation(mContext,R.anim.stop_needle_anim);

       mFramelayout.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               trigger();
           }
       });
        addView(mView);


    }

    /**
     * 切换音乐状态
     */
    public void trigger(){
        if(isPlaying){
            StopMusic();
        }
        else{
            PlayMusic();
        }
    }

    /**
     *
     */
//   播放音乐
    public void PlayMusic(){
        isPlaying=true;
        mIvPlay.setVisibility(View.GONE);
        mFramelayout.startAnimation(mMusic_Play);
        mIvNeedle.startAnimation(mStartNeedle);
        startMusicService();
////        1.判断当前音乐是否是已经在播放的音乐
////        2.如果是，那么执行start方法
////        3.如果当前音乐不是，那么调用setPath方法
//
//        if(mMediaPlayerHelp.getPath()!=null&&mMediaPlayerHelp.getPath().equals(path)){
//            mMediaPlayerHelp.start();
//        }
//        else{
//            mMediaPlayerHelp.setPath(path);
//            mMediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayerHelp.OnMeidaPlayerHelperListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mMediaPlayerHelp.start();
//                }
//            });
//        }




    }
    //   暂停音乐
    public void StopMusic(){
        isPlaying=false;
        mIvPlay.setVisibility(View.VISIBLE);
        mFramelayout.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedle);
        if(mMusicBind!=null){
            mMusicBind.StopMusic();
        }

//        mMediaPlayerHelp.pause();
    }

//    设置光盘中显示的音乐封面图片

    public void setMusicIcon(){
        Glide.with(mContext)
                .load(mMusicModel.getPoster())
                .into(mIvIcon);


    }
    /*
    * 启动音乐服务
    * */
    private  void startMusicService(){
//       启动Service
        if(MyServiceIntent==null){
            MyServiceIntent=new Intent(mContext, MusicService.class);
            mContext.startService(MyServiceIntent);
        }
        else{
            mMusicBind.PlayMusic();
        }
//        绑定Service:如果当前service未绑定的话，我们就给他绑定
        if(!IsBindService){
            IsBindService=true;
            mContext.bindService(MyServiceIntent,conn,Context.BIND_AUTO_CREATE);
        }
    }
    /*
    * 解除绑定
    * */
    public void destory(){
//        如果已经绑定了服务，解除绑定
        if(IsBindService){
            IsBindService=false;
            mContext.unbindService(conn);
        }
    }

    public void setMusic(MusicModel music){
        mMusicModel=music;
        setMusicIcon();
    }



    ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicBind=(MusicService.MusicBind)service;
            mMusicBind.setMusic(mMusicModel);
            mMusicBind.PlayMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public PlayMusicView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }
}
