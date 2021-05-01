package com.example.delivery.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.delivery.activities.LoginActivity;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.helpers.UserHelper;
import com.example.delivery.models.UserModel;

import java.util.List;

public class UserUtil {

    public static boolean login(Context context, String email, String password) {
        if (email == null || email.equals("")) {
            Toast.makeText(context, "Email is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.equals((""))) {
            Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (isEmailAvailable(email)) {
            Toast.makeText(context, "Invalid login credential", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmHelper realmHelper1 = new RealmHelper();
        boolean result = realmHelper1.validateUser(email, EncryptUtils.encryptMD5ToString(password));
        realmHelper1.close();

        if (!result) {
            Toast.makeText(context, "Invalid login credential", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmHelper realmHelper2 = new RealmHelper();
        UserModel userModel = realmHelper2.getUserByEmail(email);
        UserHelper.getInstance().setUserName(userModel.getUsername());
        UserHelper.getInstance().setEmail(email);
        if (userModel.getPhone() != null) {
            UserHelper.getInstance().setPhone(userModel.getPhone());
        }
        realmHelper2.close();

        return true;
    }

    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean addPhoneNum(Context context, String phone) {
        if (phone == null || phone.equals("")) {
            Toast.makeText(context, "Phone Number is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (c < '0' || c > '9') {
                Toast.makeText(context, "Phone Number is invalid", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        RealmHelper realmHelper = new RealmHelper();
        String email = UserHelper.getInstance().getEmail();
        realmHelper.addUserPhone(email, phone);
        UserHelper.getInstance().setPhone(phone);
        realmHelper.close();
        return true;
    }

    public static boolean validateChangePassword(Context context, String currentPassword, String newPassword, String confirmPassword) {
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

    public static boolean register(Context context, String name, String email, String password) {
        if (name == null || name.equals("")) {
            Toast.makeText(context, "Name is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email == null || email.equals("")) {
            Toast.makeText(context, "Email is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.equals((""))) {
            Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isEmailAvailable(email)) {
            Toast.makeText(context, "Email is already registered", Toast.LENGTH_SHORT).show();
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setUserName(name);
        userModel.setEmail(email);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveUser(userModel);
        realmHelper.close();

        return true;
    }

    public static boolean isEmailAvailable(String email) {
        boolean result = true;

        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> list = realmHelper.getAllUsers();

        for (UserModel userModel : list) {
            if (userModel.getEmail().equals(email)) {
                result = false;
                break;
            }
        }
        realmHelper.close();
        return result;
    }

}
