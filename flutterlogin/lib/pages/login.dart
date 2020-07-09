import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LoginPage extends  StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.cyan,
      ),
      home: Center(
        child: Text('splash'),
      ),
//      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}