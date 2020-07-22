import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutterlogin/anim/anim_page_route_builder.dart';
import 'package:flutterlogin/pages/forget_page.dart';
import 'package:flutterlogin/pages/password_page.dart';

class Router {
  static const homePage = 'app://';
  static const passwordPage = 'app://PasswordPage';
  static const forgetPage = 'app://ForgetPage';

  static Widget _getPage(String url, dynamic params) {
    if (url.startsWith('https://') || url.startsWith('http://')) {
//      return WebViewPage(url, params: params);
    } else {
      switch (url) {
        case passwordPage:
          return PasswordPage(params);
        case forgetPage:
          return ForgetPage(params);
      }
    }
    return null;
  }

  ///不携带参数跳转
  Router.pushNoParams(BuildContext context, String url) {
    Navigator.push(context, SlidePageRouteBuilder(_getPage(url, null)));
  }

  ///携带参数跳转
  Router.push(BuildContext context, String url, dynamic params) {
    Navigator.push(context, SlidePageRouteBuilder(_getPage(url, params)));
  }

  ///第一种异步方式 async await   启动并等待返回结果
  static void pushNoParamsAndResult(BuildContext context, String url,Function onResult) async {
    var result = await Navigator.push(
        context, SlidePageRouteBuilder(_getPage(url, null)));
    onResult(result);
//    Scaffold.of(context)
//        .showSnackBar(new SnackBar(content: new Text("$result")));
  }

  ///第一种异步方式 async await   启动并等待返回结果
  static void pushAndResult(BuildContext context, String url, dynamic params,Function onResult) async {
    var result = await Navigator.push(
        context, SlidePageRouteBuilder(_getPage(url, params)));
    onResult(result);
//    Scaffold.of(context)
//        .showSnackBar(new SnackBar(content: new Text("$result")));
  }

  /// 第二种异步方式 Future API  启动后不等待返回结果 但是返回后有回调函数 相当于注册了一个接收者
  Router.pushNoParamsAndDisplay(BuildContext context, String url,Function onResult) {
    Navigator.push(
        context, SlidePageRouteBuilder(_getPage(url, null))).then((result) {
      onResult(result);
//      Scaffold.of(context)
//          .showSnackBar(new SnackBar(content: new Text("$result")));
    }).catchError((error) {
      print("$error");
    });
  }


  /// 第二种异步方式 Future API  启动后不等待返回结果 但是返回后有回调函数 相当于注册了一个接收者
  Router.pushAndDisplay(BuildContext context, String url,dynamic params,Function onResult) {
    Navigator.push(
        context, SlidePageRouteBuilder(_getPage(url, params))).then((result) {
      onResult(result);
//      Scaffold.of(context)
//          .showSnackBar(new SnackBar(content: new Text("$result")));
    }).catchError((error) {
      print("$error");
    });
  }

}
