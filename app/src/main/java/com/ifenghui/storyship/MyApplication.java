package com.ifenghui.storyship;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.ifenghui.apilibrary.api.entity.User;
import com.ifenghui.commonlibrary.application.BaseApplication;
import com.ifenghui.commonlibrary.utils.PhoneManager;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        mCurrentUser=new User();
        if (PhoneManager.isMainProcess(this)){
            getAppInfo();
        }
    }

    /**
     * 获取app相关信息
     */
    private void getAppInfo(){
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            channelName = appInfo.metaData.getString("APP_CHANNEL");
            appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
