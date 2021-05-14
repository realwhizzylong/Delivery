package com.example.delivery.utils;


import com.blankj.utilcode.util.ScreenUtils;


public class ProportionUtils {

    public static float getWidthProportion(){
        int screenWidth = ScreenUtils.getScreenWidth();
        float widthProportion = (float) screenWidth / 750;
        return widthProportion;
    }


    public static float getHightProportion(){
        int screenHight = ScreenUtils.getScreenHeight();
        float hightProportion = (float) screenHight / 1334;
        return hightProportion;
    }
}
