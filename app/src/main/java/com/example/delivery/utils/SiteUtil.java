package com.example.delivery.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.delivery.helpers.RealmHelper;
import com.example.delivery.models.SiteModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        siteModel.setSitId(UUID.randomUUID().toString());
        siteModel.setAddress(address);
        siteModel.setCreateTime(new Date());

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

    public static String getFirstSite() {

        RealmHelper realmHelper = new RealmHelper();
        List<String> list = realmHelper.getAllSiteNames();
        realmHelper.close();
        return (list != null && !list.isEmpty()) ? list.get(0) : "";
    }
}
