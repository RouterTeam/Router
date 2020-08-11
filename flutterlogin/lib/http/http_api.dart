import 'package:flutterlogin/StringUtils.dart';
import 'package:flutterlogin/http/http_request.dart';

class API {
  static const BASE_URL = 'https://open3.vistastory.com';
  static const SHIP_BASE_URL = 'https://storybook.ifenghui.com';
  ///登录
  static const String Login_API = '/v3/api/user/phone_register_and_login';

  ///分组更多
  static const String GROUP_MORE_API='/api/story/new_group_story_list?';

  ///登录
  static login(String phone, Function successCabllback, Function errorCallback) {
    String diviceToken = "AuyC4ac8lNtESrD4zPx-pTJsmSXY-Y7Dzd4hRTIycZ2a";
    Map<String, String> params = <String,String>{
      'phone': '15910672817',
      'deviceUnique': diviceToken,
      'sign': StringUtils.generateMd5("15910672817" + diviceToken + "ktx")
    };
    HttpRequest.postData(BASE_URL + Login_API, successCabllback, params: params, errorCallBack: errorCallback);
  }

  ///分组更多
  static getMoreStorys(String pageNo,String groupId, Function successCabllback, Function errorCallback){
    Map<String, String> params = <String,String>{
      'pageNo': pageNo,
      'pageSize':'36',
      'groupId': groupId
    };
    HttpRequest.getData(SHIP_BASE_URL + GROUP_MORE_API, successCabllback, params: params, errorCallBack: errorCallback);
  }

}
