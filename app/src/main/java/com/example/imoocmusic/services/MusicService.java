package com.example.imoocmusic.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.imoocmusic.Models.MusicModel;
import com.example.imoocmusic.R;
import com.example.imoocmusic.activities.WelcomeActivity;
import com.example.imoocmusic.helps.MediaPlayerHelp;

/**
 *  * 1、通过Service 连接 PlayMusicView 和 MediaPlayHelper
 *  * 2、PlayMusicView -- Service：
 *  *      1、播放音乐、暂停音乐
 *  *      2、启动Service、绑定Service、解除绑定Service
 *  * 3、MediaPlayHelper -- Service：
 *  *      1、播放音乐、暂停音乐
 *  *      2、监听音乐播放完成，停止 Service
 *
 *
 *
 *
 */

public class MusicService extends Service {
//  不可为0
    public static final int NOTIFICATION_ID=1;

    private MediaPlayerHelp mMediaPlayerHelp;
    private MusicModel mMusicModel;

    @Override
    public void onCreate() {
        super.onCreate();

        mMediaPlayerHelp=MediaPlayerHelp.getInstance(this);
    }

    /**
     * 系统默认不允许不可见的后台服务播放音乐，
     * Notification ，
     */
    /**
     * 设置服务在前台可见
     */

    private void startForeground(){
        /*
        * 通知栏点击跳转页面
        * */
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,new Intent(this, WelcomeActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);



        /*
        * 创建Notification
        * */
        Notification notification=new Notification.Builder(this)
                .setContentTitle(mMusicModel.getName())
                .setContentText(mMusicModel.getAuthor())
                .setSmallIcon(R.mipmap.logo)
                .setContentIntent(pendingIntent)
                .build();


        /*
        * 设置 notification  在前台展示
        *
        * */
        startForeground(NOTIFICATION_ID,notification);

    }










    public MusicService() {
    }

    public class MusicBind extends Binder {
        /*
        *  设置音乐 (MusicModel)
        * */

        public void setMusic(MusicModel musicModel){
            mMusicModel=musicModel;
            startForeground();
        }
        /*
        * 播放音乐
        * */

        public void PlayMusic(){
//        1.判断当前音乐是否是已经在播放的音乐
//        2.如果是，那么执行start方法
//        3.如果当前音乐不是，那么调用setPath方法

            if(mMediaPlayerHelp.getPath()!=null&&mMediaPlayerHelp.getPath().equals(mMusicModel.getPath())){
                mMediaPlayerHelp.start();
            }
            else{
                mMediaPlayerHelp.setPath(mMusicModel.getPath());
                mMediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayerHelp.OnMeidaPlayerHelperListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayerHelp.start();
                    }

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopSelf();
                    }
                });
            }
        }
        /*
        * 暂停播放
        * */

        public void StopMusic(){
            mMediaPlayerHelp.pause();
        }


    }


    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBind();
    }
}
