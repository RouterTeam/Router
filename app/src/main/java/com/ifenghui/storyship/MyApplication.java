package com.ifenghui.storyship;

import com.ifenghui.apilibrary.api.entity.User;
import com.ifenghui.commonlibrary.application.BaseApplication;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        mCurrentUser=new User();
        mCurrentUser.nick="Colin";
    }
}
