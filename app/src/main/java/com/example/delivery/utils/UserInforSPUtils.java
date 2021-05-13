package com.example.delivery.utils;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.delivery.MyApplication;
import com.example.delivery.constant.UCS;
import com.example.delivery.models.UserModel;


public class UserInforSPUtils {

    public static boolean saveName(String name) {
        if (!StringUtils.isEmpty(name)) {
            MyApplication.getInstance().getSputils().put(UCS.NAME, name);
            return true;
        } else {
            MyApplication.getInstance().getSputils().put(UCS.NAME, "");
            return false;
        }
    }

    public static String getName() {
        String email = MyApplication.getInstance().getSputils().getString(UCS.NAME);
        if (!StringUtils.isEmpty(email)) {
            return email;
        } else {
            return "";
        }
    }

    public static boolean savePhone(String phone) {
        if (!StringUtils.isEmpty(phone)) {
            MyApplication.getInstance().getSputils().put(UCS.PHONE, phone);
            return true;
        } else {
            MyApplication.getInstance().getSputils().put(UCS.PHONE, "");
            return false;
        }
    }

    public static String getPhone() {
        String email = MyApplication.getInstance().getSputils().getString(UCS.PHONE);
        if (!StringUtils.isEmpty(email)) {
            return email;
        } else {
            return "";
        }
    }

    public static boolean savePic(String pic) {
        if (!StringUtils.isEmpty(pic)) {
            MyApplication.getInstance().getSputils().put(UCS.PIC, pic);
            return true;
        } else {
            MyApplication.getInstance().getSputils().put(UCS.PIC, "");
            return false;
        }
    }

    public static String getPic() {
        String email = MyApplication.getInstance().getSputils().getString(UCS.PIC);
        if (!StringUtils.isEmpty(email)) {
            return email;
        } else {
            return "";
        }
    }

    public static boolean saveEmail(String email) {
        if (!StringUtils.isEmpty(email)) {
            MyApplication.getInstance().getSputils().put(UCS.EMAIL, email);
            return true;
        } else {
            MyApplication.getInstance().getSputils().put(UCS.EMAIL, "");
            return false;
        }
    }

    public static String getEmail() {
        String email = MyApplication.getInstance().getSputils().getString(UCS.EMAIL);
        if (!StringUtils.isEmpty(email)) {
            return email;
        } else {
            return "";
        }
    }

    public static boolean saveUser(String userModel) {
        if (userModel != null) {
            MyApplication.getInstance().getSputils().put(UCS.USER, userModel);
            return true;
        } else {
            MyApplication.getInstance().getSputils().put(UCS.USER, "");
            return false;
        }
    }

    public static String getUserJson() {
        String token = MyApplication.getInstance().getSputils().getString(UCS.USER);
        if (token != null && !"".equals(token)) {
            return token;
        } else {
            return "";
        }
    }


}

