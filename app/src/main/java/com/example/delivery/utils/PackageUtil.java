package com.example.delivery.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.PackageModel;

import java.util.List;

public class PackageUtil {

    public static boolean createPackage(Context context, String newPackage) {
        if (newPackage == null || newPackage.equals((""))) {
            Toast.makeText(context, "Package is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isPackageAvailable(newPackage)) {
            Toast.makeText(context, "Package is already created", Toast.LENGTH_SHORT).show();
            return false;
        }

        PackageModel packageModel = new PackageModel();
        packageModel.setPackageId(newPackage);

        RealmHelper realmHelper = new RealmHelper();
        realmHelper.savePackage(packageModel);
        realmHelper.close();

        return true;
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
}
