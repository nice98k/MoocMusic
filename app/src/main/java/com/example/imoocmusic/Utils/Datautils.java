package com.example.imoocmusic.Utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.Utils
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/3/1 9:14
 * @change
 * @chang time
 * @class describe
 ******/
public class Datautils {


    /**
     * 读取资源文件中的数据
     *
     */
    public static String getJsonFromAssets(Context context, String filename){
        /**
         * 1.StringBuilder 存放读取出的数据
         * 2.AssetManager  资源管理器, Open方法打开指定的资源文件 ，返回InputStream
           3.InputStreamReader （字节到字符的桥接器） BufferedReader (存放读取字符的缓冲区）
         * 4.循环利用 bufferedReader 的  readLine 方法读取每一行的数据，并且把读取出来的数据放入到StringBuilder里面
         * 5. 返回读取出来的所有数据
         */
//        * 1.StringBuilder 存放读取出的数据
        StringBuilder stringBuilder=new StringBuilder();
//AssetManager  资源管理器
        AssetManager assetManager=context.getAssets();
//      Open方法打开指定的资源文件 ，返回InputStream
        try {
            InputStream inputStream=assetManager.open(filename);
//      InputStreamReader （字节到字符的桥接器） BufferedReader (存放读取字符的缓冲区）
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//          循环利用BufferedReader的readLine 方法 读取每一行的数据
            String line;
            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
