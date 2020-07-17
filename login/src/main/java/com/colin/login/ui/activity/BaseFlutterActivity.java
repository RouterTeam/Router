package com.colin.login.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import java.net.URISyntaxException;

import io.flutter.app.FlutterActivity;
import io.flutter.app.FlutterFragmentActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;

import static android.content.Intent.getIntent;

public class BaseFlutterActivity extends FlutterActivity {
    private static final String ROUTE_ACTION ="android.intent.action.RUN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getIntent().putExtra("route", "login");
        FlutterMain.startInitialization(getApplicationContext());
        super.onCreate(savedInstanceState);
    }

    public static <P extends BaseFlutterActivity> void toPage(Context context, Class<P> target) {
        Intent intent = new Intent(context, target);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction(ROUTE_ACTION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }

}