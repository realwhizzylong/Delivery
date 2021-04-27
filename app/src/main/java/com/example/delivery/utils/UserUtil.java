package com.example.delivery.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.delivery.activities.LoginActivity;

public class UserUtil {

    public static boolean validateLogin(Context context, String email, String password) {
        if (email == null || email.equals("")) {
            Toast.makeText(context, "Email is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.equals((""))) {
            Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean validatePhoneNum(Context context, String phoneNum) {
        if (phoneNum == null || phoneNum.equals("")) {
            Toast.makeText(context, "Phone Number is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < phoneNum.length(); i++) {
            char c = phoneNum.charAt(i);
            if (c < '0' || c > '9') {
                Toast.makeText(context, "Phone Number is invalid", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public static boolean changePassword(Context context, String currentPassword, String newPassword, String confirmPassword) {
        if (currentPassword == null || currentPassword.equals("")) {
            Toast.makeText(context, "Current password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPassword == null || newPassword.equals("")) {
            Toast.makeText(context, "New Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (confirmPassword == null || confirmPassword.equals("")) {
            Toast.makeText(context, "Confirm Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!confirmPassword.equals(newPassword)) {
            Toast.makeText(context, "New Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
