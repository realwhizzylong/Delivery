package com.example.delivery.helpers;

import android.net.Uri;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.delivery.activities.PackageInfoActivity;
import com.example.delivery.models.SiteModel;
import com.example.delivery.models.UserModel;
import com.example.delivery.models.VendorModel;
import com.example.delivery.models.PackageModel;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmHelper {

    private Realm realm;

    public RealmHelper() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .build();
        realm = Realm.getInstance(config);
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
        results.sort("createTime", Sort.ASCENDING);
        List<UserModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new UserComparatorSort());
        return list;
    }

    public List<UserModel> getAllDrivers() {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<UserModel> list = realm.copyFromRealm(results);

        Iterator<UserModel> iterator = list.iterator();
        while (iterator.hasNext()) {
            UserModel model = iterator.next();
            if (model.getEmail() != null && StringUtils.equals(model.getEmail(), "manager@example.com")) {
                iterator.remove();
            }
            if (model.getEmail() != null && StringUtils.equals(model.getEmail(), "admin@example.com")) {
                iterator.remove();
            }
        }

        Collections.sort(list, new UserComparatorSort());

        return list;
    }

    public List<String> getAllUserNames() {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<UserModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new UserComparatorSort());

        List<String> names = new ArrayList<>();
        for (UserModel model : list) {
            names.add(model.getUserName());
        }
        return names;
    }

    public List<String> getAllDriverNames() {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<UserModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new UserComparatorSort());

        List<String> names = new ArrayList<>();

        for (UserModel model : list) {
            if (!StringUtils.equals(model.getEmail(), "manager@example.com") && !StringUtils.equals(model.getEmail(), "admin@example.com")) {
                names.add(model.getUserName());
            }

        }
        return names;
    }

    public UserModel getUserByEmail(String email) {
        RealmQuery<UserModel> query = realm.where(UserModel.class)
                .equalTo("email", email);

        UserModel userModel = query.findFirst();
        return userModel;
    }

    public UserModel getUserByName(String name) {
        RealmQuery<UserModel> query = realm.where(UserModel.class)
                .equalTo("userName", name);

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
        results.sort("createTime", Sort.ASCENDING);
        List<VendorModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new ComparatorSort());
        return list;
    }

    public List<String> getAllVendorNames() {
        RealmQuery<VendorModel> query = realm.where(VendorModel.class);
        RealmResults<VendorModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<VendorModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new ComparatorSort());

        List<String> names = new ArrayList<>();
        for (VendorModel model : list) {
            names.add(model.getVendorName());
        }

        return names;
    }

    public void saveVendor(VendorModel vendorModel) {
        realm.beginTransaction();
        realm.insert(vendorModel);
        realm.commitTransaction();
    }

    public List<SiteModel> getAllSites() {
        RealmQuery<SiteModel> query = realm.where(SiteModel.class);
        RealmResults<SiteModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<SiteModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new SiteComparatorSort());
        return list;
    }

    public List<String> getAllSiteNames() {
        RealmQuery<SiteModel> query = realm.where(SiteModel.class);
        RealmResults<SiteModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<SiteModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new SiteComparatorSort());

        List<String> names = new ArrayList<>();
        for (SiteModel model : list) {
            names.add(model.getAddress());
        }

        return names;
    }

    public void saveSite(SiteModel siteModel) {
        realm.beginTransaction();
        realm.insert(siteModel);
        realm.commitTransaction();
    }

    public List<PackageModel> getAllPackage() {
        RealmQuery<PackageModel> query = realm.where(PackageModel.class);
        RealmResults<PackageModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<PackageModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new PackageComparatorSort());
        return list;
    }

    public List<PackageModel> getPackageByUser(String userName) {
        RealmQuery<PackageModel> query = realm.where(PackageModel.class);
        RealmResults<PackageModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<PackageModel> list = realm.copyFromRealm(results);
        List<PackageModel> userPackage = new ArrayList<>();
        for (PackageModel packageModel : list) {
            if (StringUtils.equals(packageModel.getDriver().getUserName(), userName)) {
                userPackage.add(packageModel);
            }
        }
        Collections.sort(userPackage, new PackageComparatorSort());
        return userPackage;
    }

    public List<PackageModel> getPackageBySite(String site) {
        RealmQuery<PackageModel> query = realm.where(PackageModel.class).equalTo("site", site);
        RealmResults<PackageModel> results = query.findAll();
        results.sort("createTime", Sort.ASCENDING);
        List<PackageModel> list = realm.copyFromRealm(results);
        Collections.sort(list, new PackageComparatorSort());
        return list;
    }

    public void savePackage(PackageModel packageModel) {
        realm.beginTransaction();
        realm.insert(packageModel);
        realm.commitTransaction();
    }

    public void deleetePackage(String packageName) {

        realm.executeTransactionAsync(r -> {
            PackageModel tony = r.where(PackageModel.class).equalTo("packageId", packageName).findFirst();
            tony.deleteFromRealm();
            tony = null;
        });
    }

    public PackageModel queryPackage(String packageName) {
        RealmQuery<PackageModel> query = realm.where(PackageModel.class)
                .equalTo("packageId", packageName);

        PackageModel model = query.findFirst();
        return model;
    }


    public void updatePackage(PackageModel packageModel, String driver, String ventor, String site) {
        realm.beginTransaction();

        UserModel userModel = getUserByName(driver);
        packageModel.setDriver(userModel);
        packageModel.setVendor(ventor);
        packageModel.setSite(site);

        realm.commitTransaction();

    }

    public void updatePackage(String packagename, String driver, String ventor, String site) {
        realm.executeTransactionAsync(r -> {
            PackageModel packageModel = r.where(PackageModel.class).equalTo("packageId", packagename).findFirst();
            UserModel userModel = r.where(UserModel.class).equalTo("userName", driver).findFirst();
            packageModel.setDriver(userModel);
            packageModel.setSite(site);
            packageModel.setVendor(ventor);
        });

    }

    public void deleteDriver(String userName) {
        realm.executeTransactionAsync(r -> {
            UserModel tony = r.where(UserModel.class).equalTo("userName", userName).findFirst();
            tony.deleteFromRealm();
        });
    }

    public void modifyDriver(String userName, String modifyame) {
        realm.executeTransactionAsync(r -> {
            UserModel tony = r.where(UserModel.class).equalTo("userName", userName).findFirst();
            tony.setUsername(modifyame);
        });
    }

    public void deleteventor(String ventor) {
        realm.executeTransactionAsync(r -> {
            VendorModel tony = r.where(VendorModel.class).equalTo("vendorName", ventor).findFirst();
            tony.deleteFromRealm();
        });
    }

    public void modifyVentor(String ventor, String name) {
        realm.executeTransactionAsync(r -> {
            VendorModel tony = r.where(VendorModel.class).equalTo("vendorName", ventor).findFirst();
            tony.setVendorName(name);
        });
    }

    public void deleteSite(String address) {
        realm.executeTransactionAsync(r -> {
            SiteModel tony = r.where(SiteModel.class).equalTo("address", address).findFirst();
            tony.deleteFromRealm();
        });
    }

    public void modifySite(String address, String name) {
        realm.executeTransactionAsync(r -> {
            SiteModel tony = r.where(SiteModel.class).equalTo("address", address).findFirst();
            tony.setAddress(name);
        });
    }

    public void markDelieved(String packageName) {
        realm.executeTransactionAsync(r -> {
            PackageModel packageModel = r.where(PackageModel.class).equalTo("packageId", packageName).findFirst();
            packageModel.setDelivered(true);
            packageModel.setDelieveTime(new Date());

        });
    }

    public void clearNotify(String packageName) {
        realm.executeTransactionAsync(r -> {
            PackageModel packageModel = r.where(PackageModel.class).equalTo("packageId", packageName).findFirst();
            packageModel.setNotified(true);

        });
    }
    public void clearDeliverNotify(String packageName) {
        realm.executeTransactionAsync(r -> {
            PackageModel packageModel = r.where(PackageModel.class).equalTo("packageId", packageName).findFirst();
            packageModel.setDeliveredNotify(true);

        });
    }

    class ComparatorSort implements Comparator<VendorModel> {

        @Override
        public int compare(VendorModel o1, VendorModel o2) {
            if (o1.getCreateTime().before(o2.getCreateTime())) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    class SiteComparatorSort implements Comparator<SiteModel> {

        @Override
        public int compare(SiteModel o1, SiteModel o2) {
            if (o1.getCreateTime().before(o2.getCreateTime())) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    class UserComparatorSort implements Comparator<UserModel> {

        @Override
        public int compare(UserModel o1, UserModel o2) {
            if (o1.getCreateTime().before(o2.getCreateTime())) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    class PackageComparatorSort implements Comparator<PackageModel> {

        @Override
        public int compare(PackageModel o1, PackageModel o2) {
            if (o1.getCreateTime().before(o2.getCreateTime())) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}