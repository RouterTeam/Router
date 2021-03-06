package com.ifenghui.commonlibrary.application;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.colin.library.DiskCacheMenu;
import com.colin.library.GlideImageLoader;
import com.colin.skinlibrary.SkinManager;
import com.ifenghui.apilibrary.api.entity.User;
import com.ifenghui.commonlibrary.R;
import com.ifenghui.commonlibrary.utils.PreferencesManager;
import com.ifenghui.commonlibrary.utils.RouterManger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class BaseApplication extends MultiDexApplication {
    private static Context INSTANCE;
    public static User mCurrentUser;
    // 渠道名
    public static String channelName;
    public static String appVersion= "";
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = BaseApplication.this;
        // 尽可能早，推荐在Application中初始化
        RouterManger.initRouter(this);
        PreferencesManager.initPreferencesManager(this);
        SkinManager.initSkinManager(this);
        initImageLoader();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for
            // heap analysis.
            // You should not init your app in this process.
            return;
        }

        RefWatcher install = LeakCanary.install(this);

    }

    /**
     * image loader 初始化
     */
    private void initImageLoader(){
        GlideImageLoader.apply(DiskCacheMenu.RESOURCE)
                .apply(R.mipmap.item_default, R.mipmap.item_default)
                .apply(600)
                .apply(1025 * 1024 * 10L);
//              .apply("cachpath");
    }

    /**
     * 超过64K解决方法
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * onLowMemory 当后台程序已经终止资源还匮乏时会调用这个方法
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();// 告诉系统回收
        System.runFinalization();
    }

    /**
     * 当终止应用程序对象时调用，不保证一定被调用，当程序是被内核终止以便为其他应用程序释放资源
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        RouterManger.destoryRouter();
    }
}
