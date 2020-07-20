package com.colin.login.channel;

import android.app.Activity;
import android.widget.Toast;

import com.colin.login.ui.activity.FlutterActivity;
import com.ifenghui.commonlibrary.application.Constance;
import com.ifenghui.commonlibrary.base.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.flutter.Log;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

import static com.ifenghui.commonlibrary.application.BaseApplication.mCurrentUser;

public class MethodChannelPlugin implements MethodChannel.MethodCallHandler {
    private Activity activity;
    private MethodChannel channel;

    public static MethodChannelPlugin registerWith(FlutterView flutterView) {
        MethodChannel channel = new MethodChannel(flutterView, "MethodChannelPlugin");
        MethodChannelPlugin methodChannelPlugin = new MethodChannelPlugin((Activity) flutterView.getContext(), channel);
        channel.setMethodCallHandler(methodChannelPlugin);
        return methodChannelPlugin;
    }

    private MethodChannelPlugin(Activity activity, MethodChannel channel) {
        this.activity = activity;
        this.channel = channel;
    }

    //调用flutter端方法，无返回值
    public void invokeMethod(String method, Object o) {
        channel.invokeMethod(method, o);
    }

    //调用flutter端方法，有返回值
    public void invokeMethod(String method, Object o, MethodChannel.Result result) {
        channel.invokeMethod(method, o, result);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        Object arguments = methodCall.arguments;
        Log.e("--------", arguments + "");
        switch (methodCall.method) {
            case "onLoginSuccess":
                result.success("MethodChannelPlugin收到：" + methodCall.arguments);

                if (activity instanceof FlutterActivity && arguments instanceof Map) {
                    mCurrentUser.nick = (String) ((Map) arguments).get("nick");
                    mCurrentUser.avatarUrl = (String) ((Map) arguments).get("avatarUrl");
                    mCurrentUser.phone = (String) ((Map) arguments).get("phone");
                    mCurrentUser.token = (String) ((Map) arguments).get("token");
                    mCurrentUser.id = (int) ((Map) arguments).get("id");
                    EventBus.getDefault().post(new BaseEvent(Constance.REFRESH_USER_CODE));
                    activity.onBackPressed();
                }
                break;
            case "onBackProgress"://返回的方法名
                //给flutter端的返回值
                result.success("MethodChannelPlugin收到：" + methodCall.arguments);
                if (activity instanceof FlutterActivity) {
                    activity.onBackPressed();
                }
                break;
            case "onToast":
                Toast.makeText(activity, (String) methodCall.arguments, Toast.LENGTH_LONG).show();
                break;
            case "loadingTips":
                if (activity instanceof FlutterActivity) {
                    if (arguments.equals("true")) {
                        ((FlutterActivity) activity).showLoadingTipsView();
                    } else {
                        ((FlutterActivity) activity).hideAllTipsView();
                    }
                }
                break;
            default:
                result.notImplemented();
                break;
        }
    }
}
