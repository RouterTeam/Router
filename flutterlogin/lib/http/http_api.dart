import 'package:flutterlogin/StringUtils.dart';
import 'package:flutterlogin/http/http_request.dart';

class API {
  static const BASE_URL = 'https://open3.vistastory.com';

  ///登录
  static const String Login_API = '/v3/api/user/phone_register_and_login';

  static login(
      String phone, Function successCabllback, Function errorCallback) {
    String diviceToken = "AuyC4ac8lNtESrD4zPx-pTJsmSXY-Y7Dzd4hRTIycZ2a";
    Map<String, String> params = <String,String>{
      'phone': '15910672817',
      'deviceUnique': diviceToken,
      'sign': StringUtils.generateMd5("15910672817" + diviceToken + "ktx")
    };
    HttpRequest.postData(BASE_URL + Login_API, successCabllback,
        params: params, errorCallBack: errorCallback);
  }
}
