package com.example.imoocmusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/******
 *      1. 直接在Activity中去创建播放音乐， 音乐 与 Activity 绑定，Activity运行时播放音乐，Activity退出时音乐停止播放
 *      2. 通过全局单例类与Application绑定，Application运行时播放音乐，Application被杀死时音乐停止播放
*       3. 通过Service进行音乐播放  Service运行时播放音乐，Service被杀死时音乐停止播放
 *
 *
 *
 *
 ******/
public class MediaPlayerHelp {

    private String mPath;
    private  MediaPlayerHelp mMediaPlayerHelp;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private static MediaPlayerHelp instance;
    private OnMeidaPlayerHelperListener onMeidaPlayerHelperListener;

    public void setOnMeidaPlayerHelperListener(OnMeidaPlayerHelperListener onMeidaPlayerHelperListener) {
        this.onMeidaPlayerHelperListener = onMeidaPlayerHelperListener;
    }

    public static MediaPlayerHelp getInstance(Context context){
        if(instance==null){
            synchronized(MediaPlayerHelp.class){
                if(instance==null){
                    instance=new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelp(Context context){
        mContext=context;
        mMediaPlayer=new MediaPlayer();

    }

/**
 *  1. setPath  当前播放的音乐
 *  2. start 播放音乐
 *  3. pause 暂停播放
 */
    public void setPath(String path){
        /**
         *  1.音乐正在播放或者切换了音乐，重置音乐播放状态
         *
         */

        if(mMediaPlayer.isPlaying()|| !path.equals(mPath)){
            mMediaPlayer.reset();
        }
        mPath=path;
        /**
         * 2.设置音乐播放路径
         */
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 3. 准备播放
         *    异步加载
         */
        mMediaPlayer.prepareAsync();

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(onMeidaPlayerHelperListener!=null){
                    onMeidaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
//
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(onMeidaPlayerHelperListener!=null){
                    onMeidaPlayerHelperListener.onCompletion(mp);
                }
            }
        });


    }


    public String  getPath(){
        return mPath;
    }


    /**
     * 播放音乐
     */
    public void start(){
        if(mMediaPlayer.isPlaying()){
            return ;
        }
        mMediaPlayer.start();
    }

    /**
     *  暂停播放
     */
    public void pause(){
        mMediaPlayer.pause();
    }

    public interface OnMeidaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);
        void onCompletion(MediaPlayer mp);
    }
}
