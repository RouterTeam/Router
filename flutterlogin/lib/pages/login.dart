import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutterlogin/constant/constant.dart';
import 'dart:io';
import 'package:flutter/services.dart';

class LoginPage extends StatelessWidget {
//  @override
//  Widget build(BuildContext context) {
//    return MaterialApp(
//      title: 'Flutter Demo',
//      theme: ThemeData(
//        primarySwatch: Colors.cyan,
//      ),
//      home: Center(
//        child: Text('splash'),
//      ),
//    );
//  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        theme: ThemeData(primaryColor: Colors.white),
        home: Scaffold(
          resizeToAvoidBottomPadding: false,
//          appBar: AppBar(
//            centerTitle:true,
//            title: Text('登录'),
//            backgroundColor: Colors.white,
//          ),
          body: Center(
            child: Container(
              padding: EdgeInsets.all(20.0),
              child: new Column(
                children: <Widget>[
                  CircleAvatar(
//                    radius: ScreenUtils.screenW(context) / 3,
                    backgroundColor: Colors.white,
                    backgroundImage:
                    AssetImage(Constant.ASSETS_IMG + 'person_top.png'),
                  ),
                  Image.asset(Constant.ASSETS_IMG + 'logoin_close.png'),
                  new Expanded(child: Text("登录/注册")),
                  new Expanded(child: TextField())
                ],
              ),
            ),
          ),
        ));
  }

  void _pushSaved() {}
}
