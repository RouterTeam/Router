
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutterlogin/anim/anim_page_route_builder.dart';
import 'package:flutterlogin/pages/forget_page.dart';
import 'package:flutterlogin/pages/password_page.dart';
class Router{
  static const homePage = 'app://';
  static const passwordPage = 'app://PasswordPage';
  static const forgetPage = 'app://ForgetPage';


  Widget _getPage(String url, dynamic params) {
    if (url.startsWith('https://') || url.startsWith('http://')) {
//      return WebViewPage(url, params: params);
    }else {
      switch (url) {
        case passwordPage:
          return PasswordPage();
        case forgetPage:
          return ForgetPage();
      }
    }
    return null;
  }

  Router.pushNoParams(BuildContext context, String url) {
//    Navigator.push(context, MaterialPageRoute(builder: (context) {
////      return _getPage(url, null);
////    }));
    Navigator.push(context, SlidePageRouteBuilder(_getPage(url, null)));
  }

  Router.push(BuildContext context, String url, dynamic params) {
    Navigator.push(context, MaterialPageRoute(builder: (context) {
      return _getPage(url, params);
    }));
  }
}
