package com.example.delivery.helpers;

public class UserHelper {

    private static UserHelper userInstance;

    private UserHelper() {
    }

    public static UserHelper getInstance() {
        if (userInstance == null) {
            synchronized (UserHelper.class) {
                if (userInstance == null) {
                    userInstance = new UserHelper();
                }
            }
        }
        return userInstance;
    }

    private String email;
    private String userName;
    private String phone;
    private String profilePicture;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
