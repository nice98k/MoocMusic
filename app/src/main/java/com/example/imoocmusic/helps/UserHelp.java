package com.example.imoocmusic.helps;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.helps
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/28 18:03
 * @change
 * @chang time
 * @class describe
 *   1.用户登录
 *         1.当用户登录时，利用SharedPreferences 保存登录用户的信息
 *         2.利用全局单例类UserHelp保存登录用户信息
 *              1.用户登录之后
 *              2.用户打开应用程序，检测SharedPreferences 中是否存在登录用户标记
 *              如果存在则为UserHelp进行赋值，并进入主页，如果不存在，则进入登录页面。
 *   2.用户退出
 *         1.删除掉SharedPreferences保存的用户标记，退出到登录页面。
 *
 ******/
public class UserHelp {

    private static UserHelp instance;

    private UserHelp(){

    };

    /**
     * 单例模式
     */
    public static UserHelp getInstance(){
        if(instance==null){
            synchronized (UserHelp.class){
                if(instance==null){
                    instance=new UserHelp();
                }
            }
        }
        return instance;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
