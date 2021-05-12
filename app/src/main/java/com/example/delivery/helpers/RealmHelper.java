package com.example.delivery.helpers;

import android.net.Uri;

import com.example.delivery.models.SiteModel;
import com.example.delivery.models.UserModel;
import com.example.delivery.models.VendorModel;
import com.example.delivery.models.PackageModel;


import java.net.URL;
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

    public void updateProfilePicture(UserModel userModel, String imageURL) {
        realm.beginTransaction();
        userModel.setProfilePicture(imageURL);
        realm.commitTransaction();
    }

    public List<VendorModel> getAllVendors() {
        RealmQuery<VendorModel> query = realm.where(VendorModel.class);
        RealmResults<VendorModel> results = query.findAll();
        return results;
    }

    public void saveVendor(VendorModel vendorModel) {
        realm.beginTransaction();
        realm.insert(vendorModel);
        realm.commitTransaction();
    }

    public List<SiteModel> getAllSites() {
        RealmQuery<SiteModel> query = realm.where(SiteModel.class);
        RealmResults<SiteModel> results = query.findAll();
        return results;
    }

    public void saveSite(SiteModel siteModel) {
        realm.beginTransaction();
        realm.insert(siteModel);
        realm.commitTransaction();
    }

    public List<PackageModel> getAllPackage(){
        RealmQuery<PackageModel> query = realm.where(PackageModel.class);
        RealmResults<PackageModel> results = query.findAll();
        return results;
    }

    public void savePackage(PackageModel packageModel){
        realm.beginTransaction();
        realm.insert(packageModel);
        realm.commitTransaction();
    }
}
