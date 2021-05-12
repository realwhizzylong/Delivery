package com.example.delivery.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.SiteModel;

import java.util.List;

public class SiteUtil {

    public static boolean createSite(Context context, String address) {
        if (address == null || address.equals((""))) {
            Toast.makeText(context, "Address is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isSiteAvailable(address)) {
            Toast.makeText(context, "Site is already created", Toast.LENGTH_SHORT).show();
            return false;
        }

        SiteModel siteModel = new SiteModel();
        siteModel.setAddress(address);

        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveSite(siteModel);
        realmHelper.close();

        return true;
    }

    public static boolean isSiteAvailable(String site) {
        boolean result = true;

        RealmHelper realmHelper = new RealmHelper();
        List<SiteModel> list = realmHelper.getAllSites();

        for (SiteModel siteModel : list) {
            if (siteModel.getAddress().equals(site)) {
                result = false;
                break;
            }
        }
        realmHelper.close();

        return result;
    }
}
