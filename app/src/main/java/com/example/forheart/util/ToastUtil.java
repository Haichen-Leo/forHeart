package com.example.forheart.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Util class to make toast
 */
public class ToastUtil {

    public static void centerToast(Context context, String str) {
        Toast toast = Toast.makeText(context,str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void bottomToast(Context context, String str) {
        Toast toast = Toast.makeText(context,str, Toast.LENGTH_SHORT);
        toast.show();
    }
}
