
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutterlogin/pages/passwordlogin_page.dart';
class Router{
  static const homePage = 'app://';
  static const passWordLoginPage = 'app://PasswordLoginPage';


  Widget _getPage(String url, dynamic params) {
    if (url.startsWith('https://') || url.startsWith('http://')) {
//      return WebViewPage(url, params: params);
    }else {
      switch (url) {
        case passWordLoginPage:
          return PasswordLoginPage();
      }
    }
    return null;
  }

  Router.pushNoParams(BuildContext context, String url) {
    Navigator.push(context, MaterialPageRoute(builder: (context) {
      return _getPage(url, null);
    }));
  }

  Router.push(BuildContext context, String url, dynamic params) {
    Navigator.push(context, MaterialPageRoute(builder: (context) {
      return _getPage(url, params);
    }));
  }
}
