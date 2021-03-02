package com.example.imoocmusic.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.imoocmusic.Constants.SPConstants;
import com.example.imoocmusic.helps.UserHelp;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.Utils
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/28 20:14
 * @change
 * @chang time
 * @class describe
 ******/
public class SPUtils {

//    当用户登录时，利用SharedPreferences 保存登录用户的信息

    public static boolean saveUser(Context context,String phone){
        SharedPreferences sp=context.getSharedPreferences(SPConstants.SP_NAME_USER,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sp.edit();
        editor.putString(SPConstants.SP_KEY_PHONE,phone);
        boolean result=editor.commit();
        return  result;
    }

//   验证是否存在已登录用户
    public static boolean isLoginUser(Context context){
        boolean result=false;

        SharedPreferences sp=context.getSharedPreferences(SPConstants.SP_NAME_USER,Context.MODE_PRIVATE);

        String phone =sp.getString(SPConstants.SP_KEY_PHONE,"");

        if(!TextUtils.isEmpty(phone)){
            result=true;
            UserHelp.getInstance().setPhone(phone);
        }
        return result;
    }
//

    /**
     * @param context  退出登录
     * @return
     */
    public static boolean removeUser(Context context){
        SharedPreferences sp=context.getSharedPreferences(SPConstants.SP_NAME_USER,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sp.edit();
        editor.remove(SPConstants.SP_KEY_PHONE);
        boolean result=editor.commit();
        return result;
    }
}
