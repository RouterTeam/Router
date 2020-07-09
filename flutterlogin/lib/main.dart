import 'package:flutter/material.dart';
import 'package:flutterlogin/pages/login.dart';
import 'dart:io';
import 'dart:ui';
//程序入口函数
void main() => runApp(chooseWidget(window.defaultRouteName));

Widget chooseWidget(String routeName) {
  switch (routeName) {
    case "login":
      return LoginPage();
    default:
      return Center(
        child: Text("Unknown Route"),
      );
  }
}

class StudyFeedbackView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Card(
        color: Colors.white,
        shape: RoundedRectangleBorder(),
        child: Center(
          child: Text("My Flutter View"),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          print("哈哈");
        },
        child: Icon(Icons.add),
      ),
    );
  }
}