import 'dart:convert';

class BaseModel{
  String msg;
  int status;

  BaseModel.fromParams({this.msg, this.status});

  factory BaseModel(jsonStr)=> jsonStr == null ? null :jsonStr is String ? new BaseModel.fromJson(json.decode(jsonStr)) : new BaseModel.fromJson(jsonStr);

  BaseModel.fromJson(jsonRes) {
    msg= jsonRes['msg'];
    status= jsonRes['status'];
  }
}