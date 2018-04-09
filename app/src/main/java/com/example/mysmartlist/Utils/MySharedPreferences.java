package com.example.mysmartlist.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MySharedPreferences {

    static Context context;
    static String FileName="smart_list";
    static SharedPreferences sharedPref;
    static SharedPreferences.Editor editor;

    public static void setUpMySharedPreferences(Context context_){
         context=context_;
        sharedPref = context.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        editor=sharedPref.edit();
    }

    public static void setUserSetting(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }

    public static String getUserSetting(String key){

        String UserSetting=sharedPref.getString(key,"");

        return UserSetting;
    }

    public static boolean isFirstTime(){
        String check=sharedPref.getString("FirstTime","");

        if(check.equals("yes"))
            return false;
         return true;
    }

    public static void firstTime(){
        editor.putString("FirstTime","yes");
        editor.commit();
    }

    public void Clear(){
        editor.clear();
        editor.commit();
    }

}
