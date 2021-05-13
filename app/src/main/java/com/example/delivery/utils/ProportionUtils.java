package com.example.delivery.utils;


import com.blankj.utilcode.util.ScreenUtils;

/**
 *
 * 获取比例
 * 宽度公式为 屏幕宽度 / 基准宽度750
 * 宽度公式为 屏幕高度 / 基准高度1334
 * Created by 陈小龙 on 2017/3/5.
 */
public class ProportionUtils {
    //根据宽度获取比例
    public static float getWidthProportion(){
        int screenWidth = ScreenUtils.getScreenWidth();
        float widthProportion = (float) screenWidth / 750;
        return widthProportion;
    }

    //根据宽度获取比例
    public static float getHightProportion(){
        int screenHight = ScreenUtils.getScreenHeight();
        float hightProportion = (float) screenHight / 1334;
        return hightProportion;
    }
}
