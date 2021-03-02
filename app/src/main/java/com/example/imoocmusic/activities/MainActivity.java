package com.example.imoocmusic.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imoocmusic.Models.MusicSourceModel;
import com.example.imoocmusic.R;
import com.example.imoocmusic.adapters.MusicGridAdapter;
import com.example.imoocmusic.adapters.MusicListAdapter;
import com.example.imoocmusic.helps.RealmHelp;
import com.example.imoocmusic.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView,mRvList;
    private MusicGridAdapter musicGridAdapter;
    private MusicListAdapter musicListAdapter;

    private RealmHelp mRealmHelp;
    private MusicSourceModel mMusicSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mRealmHelp=new RealmHelp();
        mMusicSourceModel=mRealmHelp.getMusicSource();



    }

    private void initView() {
        initNavBar(false,"慕课音乐",true);
        mRecyclerView=fd(R.id.rv_grid);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize),mRecyclerView));
        musicGridAdapter=new MusicGridAdapter(this,mMusicSourceModel.getAlbum());
        mRecyclerView.setAdapter(musicGridAdapter);

        mRvList=fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        musicListAdapter=new MusicListAdapter(this,mRvList,mMusicSourceModel.getHot());
        mRvList.setAdapter(musicListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelp.close();
    }
}
