package com.example.imoocmusic.helps;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.imoocmusic.Models.AlbumModel;
import com.example.imoocmusic.Models.MusicModel;
import com.example.imoocmusic.Models.MusicSourceModel;
import com.example.imoocmusic.Models.UserModels;
import com.example.imoocmusic.Utils.Datautils;
import com.example.imoocmusic.migration.Migration;


import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/******
 ******/
public class RealmHelp {

    private Realm mRealm;

    public RealmHelp() {
        mRealm=Realm.getDefaultInstance();
    }

    /**
     * 保存用户信息
     * @param userModels
     */
    public void saveUser(UserModels userModels){
        mRealm.beginTransaction();
        mRealm.insert(userModels);
        mRealm.commitTransaction();
    }
    /**
     * Realm数据库发生结构性变化（模型或者模型中的字段发生了新增、修改、删除）的时候，我们就需要对数据库进行迁移
     * @return
     */
//   返回RealmConfiguration
    private static RealmConfiguration getRealmConf(){
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }


    public static void migration(){
        RealmConfiguration conf=getRealmConf();
//        Realm 设置最新的配置
        Realm.setDefaultConfiguration(conf);
//        告诉Realm数据需要迁移
        try {
            Realm.migrateRealm(conf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




    /**
     * 关闭数据库
     */
    public void close(){
        if(mRealm!=null||!mRealm.isClosed())
            mRealm.close();
    }


    /**
     * 返回所有用户
     */
    public List<UserModels> getAllUser(){
        RealmQuery<UserModels> query=mRealm.where(UserModels.class);
        RealmResults<UserModels> results=query.findAll();
        return results;
    }


    public  boolean validateUser(String phone,String password){
        boolean res=false;
        RealmQuery<UserModels> query=mRealm.where(UserModels.class);
        query=query.equalTo("phone",phone).equalTo("password",password);

        UserModels userModels=query.findFirst();
        if(userModels!=null){
            res=true;
        }
        return res;
    }

    /**
     *  获取当前用户
     */
    public UserModels getUser(){
        RealmQuery<UserModels> query=mRealm.where(UserModels.class);

        UserModels userModels=query.equalTo("phone",UserHelp.getInstance().getPhone()).findFirst();
        return userModels;
    }


    /**
     * 修改密码
     */
    public void ChangePassWord(String password){
        UserModels userModels=getUser();
        mRealm.beginTransaction();
        userModels.setPassword(password);
        mRealm.commitTransaction();
    }
//  保存音乐源数据
    public void setMusicSource(Context context){
//        拿到资源文件中的数据
        String musicSourceJson= Datautils.getJsonFromAssets(context,"DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceModel.class,musicSourceJson);
        mRealm.commitTransaction();

    }

//   删除音乐元数据
    public void removeMusicSource(){
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceModel.class);
        mRealm.delete(MusicModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
    }

    /**
     * 返回音乐源数据
     */
    public MusicSourceModel getMusicSource(){
        return mRealm.where(MusicSourceModel.class).findFirst();
    }


    public AlbumModel getAlbum(String albumId){
        return mRealm.where(AlbumModel.class).equalTo("albumId",albumId).findFirst();
    }

    /*
    * 返回音乐
    *
    * */
    public MusicModel getMusic(String musicId){
        return mRealm.where(MusicModel.class).equalTo("musicId",musicId).findFirst();
    }



}
