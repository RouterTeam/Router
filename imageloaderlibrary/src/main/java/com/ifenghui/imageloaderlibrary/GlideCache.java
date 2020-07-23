package com.ifenghui.imageloaderlibrary;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.ifenghui.imageloaderlibrary.progress.ProgressManager;

import java.io.InputStream;

@GlideModule
public class GlideCache extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        super.applyOptions(context, builder);

        //通过builder.setXXX进行配置.
        long diskCacheSizeBytes = 1024 * 1024 * 500; // 500 MB
        //手机app路径
        String appRootPath = context.getCacheDir().getPath();
//        Log.e("-----","缓存路径="+appRootPath);
        builder.setDiskCache(
                new DiskLruCacheFactory( appRootPath+"/GlideDisk", diskCacheSizeBytes )
        );
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        //通过glide.register进行配置.  用okhttp实现网络请求
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(ProgressManager.getOkHttpClient()));
    }
}
