package com.example.delivery.utils;

import android.content.Context;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.example.delivery.activities.DelievedActivity;
import com.example.delivery.activities.NotifyActivity;
import com.example.delivery.activities.PackageInfoActivity;
import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;
import com.example.delivery.models.UserModel;

import java.util.Date;
import java.util.List;

public class PackageUtil {

    public static boolean createPackage(Context context, String newPackage, String driver, String site, String vendor) {
        if (newPackage == null || newPackage.equals((""))) {
            Toast.makeText(context, "Package is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtils.isEmpty(driver)) {
            Toast.makeText(context, "Driver is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(site)) {
            Toast.makeText(context, "Site is empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(vendor)) {
            Toast.makeText(context, "Vendor is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isDriverAvailable(driver)) {
            Toast.makeText(context, "Driver not exsit", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isPackageAvailable(newPackage)) {
            Toast.makeText(context, "Package is already created", Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmHelper realmHelper = new RealmHelper();

        PackageModel packageModel = new PackageModel();
        packageModel.setPackageId(newPackage);
        packageModel.setDriver(realmHelper.getUserByName(driver));
        packageModel.setVendor(vendor);
        packageModel.setSite(site);
        packageModel.setCreateTime(new Date());

        realmHelper.savePackage(packageModel);
        realmHelper.close();

        return true;
    }

    private static boolean isDriverAvailable(String driver) {

        RealmHelper realmHelper = new RealmHelper();
        UserModel model = realmHelper.getUserByName(driver);

        realmHelper.close();

        return model != null;
    }

    public static boolean isPackageAvailable(String newPackage) {
        boolean result = true;

        RealmHelper realmHelper = new RealmHelper();
        List<PackageModel> list = realmHelper.getAllPackage();

        for (PackageModel packageModel : list) {
            if (packageModel.getPackageId().equals(newPackage)) {
                result = false;
                break;
            }
        }
        realmHelper.close();

        return result;
    }

    public static boolean deletePackage(Context context, String packageName) {
        if (isPackageAvailable(packageName)) {
            Toast.makeText(context, "Package is not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmHelper realmHelper = new RealmHelper();

        realmHelper.deleetePackage(packageName);

        realmHelper.close();

        return true;
    }

    public static boolean updatePackage(Context context, String packageName, String driver, String ventor, String site) {
        RealmHelper realmHelper = new RealmHelper();

        realmHelper.updatePackage(packageName, driver, ventor, site);


        realmHelper.close();

        return true;
    }

    public static boolean markDelieved(Context context, String packageName) {
        RealmHelper realmHelper = new RealmHelper();

        realmHelper.markDelieved(packageName);


        realmHelper.close();

        return true;
    }

    public static boolean clearNotify(Context context, String packageName) {
        RealmHelper realmHelper = new RealmHelper();
        realmHelper.clearNotify(packageName);
        realmHelper.close();

        return true;
    }
}
