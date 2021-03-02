package com.example.imoocmusic.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.imoocmusic.Models.UserModels;
import com.example.imoocmusic.R;
import com.example.imoocmusic.activities.LoginActivity;
import com.example.imoocmusic.helps.RealmHelp;
import com.example.imoocmusic.helps.UserHelp;

import java.util.List;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.Utils
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/21 11:37
 * @change
 * @chang time
 * @class describe
 ******/
public class Userutils {
    private static final String TAG = "Userutils";
    
    /**
     * @description:    检查用户输入合法性
     * @param:          
     * @return:         
     * @author:         nice
     * @time:           2021/2/21 11:40
     */
    public static boolean validateLogin(Context context,String phone,String  password){


        if(!RegexUtils.isMobileExact(phone)){

            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,"密码为空,请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
//        1.用户当前手机号是否已经被注册了
//        2.用户输入的手机号和密码是否匹配
        if(!Userutils.userExistFromPhone(phone)){
            Toast.makeText(context,"当前手机号尚未注册！",Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmHelp realmHelp=new RealmHelp();
        boolean result=realmHelp.validateUser(phone,EncryptUtils.encryptMD5ToString(password));

        if(!result){
            Toast.makeText(context,"手机号或者密码不正确！",Toast.LENGTH_SHORT).show();
            return false;
        }

//        保存用户登录信息
        boolean isSave=SPUtils.saveUser(context,phone);
        if(!isSave){
            Toast.makeText(context,"系统错误，请稍后重试！",Toast.LENGTH_SHORT).show();

            return false;
        }
//      保存用户标记，在全局单例类之中
        UserHelp.getInstance().setPhone(phone);

//        保存音乐源数据
        realmHelp.setMusicSource(context);

        realmHelp.close();

        return true;
        
    }


//  退出登录
    public static void logout(Context context){
//        删除sp保存的用户标记
        boolean isRemove=SPUtils.removeUser(context);
        if(!isRemove){
            Toast.makeText(context,"系统错误，请稍后重试！",Toast.LENGTH_SHORT).show();
            return;
        }
//      删除数据源
        RealmHelp realmHelp=new RealmHelp();
        realmHelp.removeMusicSource();
        realmHelp.close();

        Intent intent=new Intent(context, LoginActivity.class);

//        清除栈，再建立一个新栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//       overridePendingTransition 一定要在startActivity后面执行
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }


    /**
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     * @return
     */
    public static boolean registerUser(Context context,String phone,String password,String passwordConfirm){
        if(!RegexUtils.isMobileExact(phone)){

            Toast.makeText(context, "无效手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isEmpty(password)){
            Toast.makeText(context, "请填写密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!password.equals(passwordConfirm)){
            Toast.makeText(context, "请确认两次密码是否一致", Toast.LENGTH_SHORT).show();
            return false;
        }

//        用户当前输入的手机号是否已经被注册

//           1.通过Realm获取到当前已经注册的所有用户
//           2.根据用户输入的手机号匹配查询的所有用户，如果匹配到则证明手机号已经注册了
        if(Userutils.userExistFromPhone(phone)){
            Toast.makeText(context, "该手机号已存在！", Toast.LENGTH_SHORT).show();
            return false;
        }




        UserModels userModels=new UserModels();
        userModels.setPhone(phone);
        userModels.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModels);
        return true;
    }


    /**
     * 保存用户到数据库
     */
    public static void saveUser(UserModels userModels){
        RealmHelp realmHelp=new RealmHelp();
        realmHelp.saveUser(userModels);
        realmHelp.close();

    }


    /**
     * 根据手机号判断用户是否存在
     *
     * @param phone
     * @return
     */
    public static  boolean userExistFromPhone(String phone){
        boolean result=false;

        RealmHelp realmHelp=new RealmHelp();
        List<UserModels> allUser=realmHelp.getAllUser();

        for(UserModels userModels: allUser){
            if(userModels.getPhone().equals(phone)){
                result=true;
                break;
            }
        }
        realmHelp.close();
        return  result;
    }

//   验证是否存在已登录用户
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }


//

    /**
     * 修改密码
     *  1.数据验证
     *      1.原密码是否输入
     *      2.新密码是否输入且新密码与确定密码是否相同
     *      3. 原密码输入是否正确
     *            1. Realm 获取到当前登录的用户模型
     *            2. 根据用户模型中保存的密码匹配用户原密码
     *
     *  2. 利用Realm模型自动更新特性来完成密码的修改
     */
    public static boolean ChangePassWord(Context context,String oldPassWord,String passWord,String passWordConfirm){

        if(TextUtils.isEmpty(oldPassWord)){
            Toast.makeText(context, "请输入原密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(passWord)||!passWord.equals(passWordConfirm)){
            Log.d(TAG, "ChangePassWord: "+passWord+"   "+passWordConfirm+"   :"+TextUtils.isEmpty(passWord)+"  :"+passWord.equals(passWordConfirm));
            Toast.makeText(context, "请输入密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        Log.d(TAG, "ChangePassWord: 123");
//        验证原密码是否正确
        RealmHelp realmHelp=new RealmHelp();
        UserModels userModels=realmHelp.getUser();

        if(!EncryptUtils.encryptMD5ToString(oldPassWord).equals(userModels.getPassword())){
            Toast.makeText(context, "原密码不正确！", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelp.ChangePassWord(EncryptUtils.encryptMD5ToString(passWord));


        realmHelp.close();
        return  true;
    }

}
