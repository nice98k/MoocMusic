package com.example.imoocmusic.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.Models
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/28 15:38
 * @change
 * @chang time
 * @class describe
 ******/
public class UserModels extends RealmObject {

    @PrimaryKey
    private String phone;
    @Required
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
