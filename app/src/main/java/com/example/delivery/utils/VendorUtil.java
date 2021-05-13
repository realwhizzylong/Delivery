package com.example.delivery.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.VendorModel;

import java.util.List;
import java.util.UUID;

public class VendorUtil {

    public static boolean createVendor(Context context, String vendor) {
        if (vendor == null || vendor.equals((""))) {
            Toast.makeText(context, "Vendor is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isVendorAvailable(vendor)) {
            Toast.makeText(context, "Vendor is already created", Toast.LENGTH_SHORT).show();
            return false;
        }

        VendorModel vendorModel = new VendorModel();
        vendorModel.setVentorId(UUID.randomUUID().toString());
        vendorModel.setVendorName(vendor);

        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveVendor(vendorModel);
        realmHelper.close();

        return true;
    }

    public static boolean isVendorAvailable(String vendor) {
        boolean result = true;

        RealmHelper realmHelper = new RealmHelper();
        List<VendorModel> list = realmHelper.getAllVendors();

        for (VendorModel vendorModel : list) {
            if (vendorModel.getVendorName().equals(vendor)) {
                result = false;
                break;
            }
        }
        realmHelper.close();

        return result;
    }
}
