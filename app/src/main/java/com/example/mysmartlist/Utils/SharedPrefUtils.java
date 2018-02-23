package com.example.mysmartlist.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by abdallah on 1/30/2018.
 */

public class SharedPrefUtils {

    public static int getSharedPrefValue(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("lang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        return sharedPref.getInt("language",1);
    }

}
