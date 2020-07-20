import 'dart:core';

import 'package:flutterlogin/bean/BaseModel.dart';

class UserResult extends BaseModel{
  User user;

  UserResult({status, msg, this.user}) : super.fromJson(null);

  UserResult.fromJson(Map<String, dynamic> json):super.fromJson(json) {

    user = json['user'] != null ? new User.fromJson(json['user']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['status'] = this.status;
    data['msg'] = this.msg;
    if (this.user != null) {
      data['user'] = this.user.toJson();
    }
    return data;
  }
}

class User {
  int id;
  String nick;
//  String username;
//  int sex;
//  String image;
//  String interCode;
  String phone;
//  String email;
//  String logtype;
  String token;
//  String channel;
//  int istest;
//  int deviceLimit;
//  int ispreview;
//  int isOffical;
//  Null point;
//  Null balance;
//  Null androidBalance;
//  int hasBindPhone;
  String avatarUrl;
//  Null loginStyle;
//  String createTimeFormat;
//  int createTime;

  User(
      {this.id,
        this.nick,
//        this.username,
//        this.sex,
//        this.image,
//        this.interCode,
        this.phone,
//        this.email,
//        this.logtype,
        this.token,
//        this.channel,
//        this.istest,
//        this.deviceLimit,
//        this.ispreview,
//        this.isOffical,
//        this.point,
//        this.balance,
//        this.androidBalance,
//        this.hasBindPhone,
        this.avatarUrl,
//        this.loginStyle,
//        this.createTimeFormat,
//        this.createTime
      });


  User.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nick = json['nick'];
//    username = json['username'];
//    sex = json['sex'];
//    image = json['image'];
//    interCode = json['interCode'];
    phone = json['phone'];
//    email = json['email'];
//    logtype = json['logtype'];
    token = json['token'];
//    channel = json['channel'];
//    istest = json['istest'];
//    deviceLimit = json['deviceLimit'];
//    ispreview = json['ispreview'];
//    isOffical = json['isOffical'];
//    point = json['point'];
//    balance = json['balance'];
//    androidBalance = json['android_balance'];
//    hasBindPhone = json['hasBindPhone'];
    avatarUrl = json['avatarUrl'];
//    loginStyle = json['loginStyle'];
//    createTimeFormat = json['createTimeFormat'];
//    createTime = json['createTime'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['nick'] = this.nick;
//    data['username'] = this.username;
//    data['sex'] = this.sex;
//    data['image'] = this.image;
//    data['interCode'] = this.interCode;
    data['phone'] = this.phone;
//    data['email'] = this.email;
//    data['logtype'] = this.logtype;
    data['token'] = this.token;
//    data['channel'] = this.channel;
//    data['istest'] = this.istest;
//    data['deviceLimit'] = this.deviceLimit;
//    data['ispreview'] = this.ispreview;
//    data['isOffical'] = this.isOffical;
//    data['point'] = this.point;
//    data['balance'] = this.balance;
//    data['android_balance'] = this.androidBalance;
//    data['hasBindPhone'] = this.hasBindPhone;
    data['avatarUrl'] = this.avatarUrl;
//    data['loginStyle'] = this.loginStyle;
//    data['createTimeFormat'] = this.createTimeFormat;
//    data['createTime'] = this.createTime;
    return data;
  }
}
