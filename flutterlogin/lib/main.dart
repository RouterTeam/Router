import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutterlogin/pages/login_page.dart';
import 'dart:io';
import 'dart:ui';
//程序入口函数
void main() {
  if (Platform.isAndroid) {
    // 以下两行 设置android状态栏为透明的沉浸。写在组件渲染之后，是为了在渲染后进行set赋值，覆盖状态栏，写在渲染之前MaterialApp组件会覆盖掉这个值。
    SystemUiOverlayStyle systemUiOverlayStyle =
    SystemUiOverlayStyle(statusBarColor: Colors.transparent)
        .copyWith(statusBarBrightness: Brightness.light);
//    SystemUiOverlayStyle systemUiOverlayStyle = SystemUiOverlayStyle.light.copyWith(statusBarBrightness: Brightness.light);
    SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
//    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.light.copyWith(statusBarBrightness: Brightness.light));

  }
  runApp(chooseWidget(window.defaultRouteName));
}

Widget chooseWidget(String routeName) {
  switch (routeName) {
    case "login":
      return LoginPage();
    default:
      return new UnKnowView();
  }
}

class UnKnowView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(primaryColor: Colors.white),
        home: Scaffold(
          resizeToAvoidBottomPadding: false,
          body: Center(
            child: Center(
              child: Text("没能找到您想要的页面"),
            )
          ),
        ));
  }
}