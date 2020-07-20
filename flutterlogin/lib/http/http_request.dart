import 'package:dio/dio.dart';

class HttpRequest{
 static String GET="GET";
 static String POST="POST";
 static int TIMEOUT_CONNECT=5000;
 static int TIMEOUT_RECEIVE=5000;
 static int TIMEOUT_SEND=5000;

  static void _request(String url, Function successCallback,
      {String method,
        Map<String, String> params,
        Function errorCallBack}) async {
    print("url = $url");
    String errorMsg = "";
    int statusCode;
    try {
      Response response;
      BaseOptions baseOptions = new BaseOptions(
        connectTimeout: TIMEOUT_CONNECT,
        receiveTimeout: TIMEOUT_RECEIVE,
      );
      // dio库中默认将请求数据序列化为json，此处可根据后台情况自行修改
//      contentType:new ContentType('application', 'x-www-form-urlencoded',charset: 'utf-8')
      Options options = new Options(
        connectTimeout: TIMEOUT_CONNECT,
        receiveTimeout: TIMEOUT_RECEIVE,
        sendTimeout: TIMEOUT_SEND,
      );
      Dio dio = new Dio(baseOptions);
      if (method == GET) {
        response =
        await dio.get(url, queryParameters: params, options: options);
      } else {
        response =
        await dio.post(url, queryParameters: params, options: options);
      }
      statusCode = response.statusCode;
      if (statusCode != 200) {
        errorMsg = "网络请求错误,状态码:" + statusCode.toString();
        errorCallBack( errorMsg);
      } else {
        if (successCallback != null) {
//          var data = json.decode(response.toString()); //对数据进行Json转化
          successCallback(response.toString());
          print("data = " + response.toString());
        }
      }
    } catch (exception) {
      errorCallBack(exception.toString());
    }
  }

  /// get 请求
  static getData(String url, Function successCallback,
      {Map<String, String> params, Function errorCallBack}) async {
    if (params != null && params.isNotEmpty) {
      StringBuffer stringBuffer = new StringBuffer("?");
      params.forEach((key, value) {
        stringBuffer.write("$key" + "=" + "$value" + "&");
      });
      String paramStr = stringBuffer.toString();
      paramStr = paramStr.substring(0, paramStr.length - 1);
      url += paramStr;
    }
    _request(url, successCallback,
        method: GET, params: params, errorCallBack: errorCallBack);
  }


   /// Post请求
  static postData(String url,  Function successCabllback,  {Map<String, String> params, Function errorCallBack}) async {
    _request(url, successCabllback,
        method: POST, params: params, errorCallBack: errorCallBack);
  }
}