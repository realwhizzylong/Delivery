package com.example.delivery.utils;

import android.content.Context;
import android.widget.Toast;

public class UserUtil {

    public static boolean validateLogin(Context context, String name, String password) {
        if (name == null || name.equals("")) {
            Toast.makeText(context, "Name is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.equals((""))) {
            Toast.makeText(context, "Password is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
