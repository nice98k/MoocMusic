package com.example.imoocmusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.imoocmusic.helps.RealmHelp;

import io.realm.Realm;

public class Myapplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        初始化application
        Utils.init(this);
        Realm.init(this);

        RealmHelp.migration();
    }
}
