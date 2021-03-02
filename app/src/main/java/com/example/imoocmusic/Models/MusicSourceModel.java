package com.example.imoocmusic.Models;

import io.realm.RealmList;
import io.realm.RealmObject;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.Models
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/3/1 9:02
 * @change
 * @chang time
 * @class describe
 ******/
public class MusicSourceModel extends RealmObject {

    private RealmList<AlbumModel> album;
    private RealmList<MusicModel> hot;

    public RealmList<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<AlbumModel> album) {
        this.album = album;
    }

    public RealmList<MusicModel> getHot() {
        return hot;
    }

    public void setHot(RealmList<MusicModel> hot) {
        this.hot = hot;
    }
}
