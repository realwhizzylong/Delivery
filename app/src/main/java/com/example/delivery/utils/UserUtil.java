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
        UserHelper.getInstance().setUserName(userModel.getUserName());
        UserHelper.getInstance().setEmail(email);
        UserHelper.getInstance().setPhone(userModel.getPhone());
        UserHelper.getInstance().setProfilePicture(userModel.getProfilePicture());
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
        UserModel userModel = realmHelper.getUserByEmail(email);
        realmHelper.addUserPhone(userModel, phone);
        UserHelper.getInstance().setPhone(phone);
        realmHelper.close();

        return true;
    }

    public static boolean updateProfilePicture(Context context, String imageURL) {
        if (imageURL == null || imageURL.equals("")) {
            Toast.makeText(context, "No picture uploaded", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmHelper realmHelper = new RealmHelper();
        String email = UserHelper.getInstance().getEmail();
        UserModel userModel = realmHelper.getUserByEmail(email);
        realmHelper.updateProfilePicture(userModel, imageURL);
        realmHelper.close();

        UserHelper.getInstance().setProfilePicture(imageURL);

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
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        RealmHelper realmHelper = new RealmHelper();
        String email = UserHelper.getInstance().getEmail();
        UserModel userModel = realmHelper.getUserByEmail(email);

        if (!EncryptUtils.encryptMD5ToString(currentPassword).equals(userModel.getPassword())) {
            Toast.makeText(context, "Current password is incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelper.changePassword(userModel, EncryptUtils.encryptMD5ToString(newPassword));
        realmHelper.close();

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
        userModel.setUsername(name);
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

    public static boolean isManager(String email){
        boolean result = false;

            if(email.equals("manager@example.com")){
                result = true;

            }

        return result;
    }

    public static boolean isRegisterAuthority(Context context, String email){
        boolean result = false;

            if(email.equals("manager@example.com")){
                result = true;
            }else{
                Toast.makeText(context, "You have no authority to register a driver", Toast.LENGTH_SHORT).show();
            }


        return result;
    }


}
