package com.example.delivery.helpers;

import com.example.delivery.models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelper {

    private Realm realm;

    public RealmHelper() {
        realm = Realm.getDefaultInstance();
    }

    public void close() {
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

    public void saveUser(UserModel userModel) {
        realm.beginTransaction();
        realm.insert(userModel);
        realm.commitTransaction();
    }

    public List<UserModel> getAllUsers() {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    public UserModel getUserByEmail(String email) {
        RealmQuery<UserModel> query = realm.where(UserModel.class)
                .equalTo("email", email);

        UserModel userModel = query.findFirst();
        return userModel;
    }

    public boolean validateUser(String email, String password) {
        RealmQuery<UserModel> query = realm.where(UserModel.class)
                .equalTo("email", email)
                .equalTo("password", password);

        UserModel userModel = query.findFirst();
        return userModel != null;
    }

    public void addUserPhone(UserModel userModel, String phone) {
        realm.beginTransaction();
        userModel.setPhone(phone);
        realm.commitTransaction();
    }

    public void changePassword(UserModel userModel, String password) {
        realm.beginTransaction();
        userModel.setPassword(password);
        realm.commitTransaction();
    }
}
