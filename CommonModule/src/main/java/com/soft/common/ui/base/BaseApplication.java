package com.soft.common.ui.base;

import android.app.Application;
import android.content.Context;

/**
 * description:基本 application类文件
 * Date: 2016/9/8 18:04
 * User: shaobing
 */
public class BaseApplication extends Application {

    private static Context mContext;

    /**
     * 获取系统Context
     * @return 返回值
     */
    public static Context getAppContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
