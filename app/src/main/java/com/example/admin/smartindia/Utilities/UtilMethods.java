package com.example.admin.smartindia.Utilities;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by admin on 24/03/2017.
 */
public class UtilMethods {

    public static  void ToastS(Context context,String msg){
        Toast toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static  void ToastL(Context context,String msg){
        Toast toast=Toast.makeText(context,msg,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
