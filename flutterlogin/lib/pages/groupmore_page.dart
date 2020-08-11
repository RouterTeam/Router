import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/services.dart';
import 'package:flutterlogin/bean/StoryResult.dart';
import 'package:flutterlogin/http/http_api.dart';

class GroupMorePage extends StatelessWidget {
  final String params;
  final String title;
  MethodChannel _methodChannel = MethodChannel("MethodChannelPlugin");

  GroupMorePage(this.params, this.title, {Key key}) : super(key: key);

  void initState() {
    // ignore: missing_return
    _methodChannel.setMethodCallHandler((handler) => Future<String>(() {}));
  }

  //flutter调用native的相应方法
  void _sendToNative(String method, [dynamic tips]) {
    Future<String> future = _methodChannel.invokeMethod(method, tips);
    future.then((message) {});
  }

  @override
  Widget build(BuildContext context) {
    initState();
    return MaterialApp(
        color: Colors.white,
        theme: ThemeData(primaryColor: Colors.white),
        home: WillPopScope(
            child: Scaffold(
                backgroundColor: Colors.white,
                appBar: AppBar(
                  centerTitle: true,
                  title: Text(title),
                  backgroundColor: Colors.white,
                  leading: IconButton(
                      icon: Icon(Icons.chevron_left),
                      onPressed: () => _sendToNative("onBackProgress", "")),
                ),
                resizeToAvoidBottomPadding: false,
                body: MyGridView(params))));
  }
}

class MyGridView extends StatefulWidget {
  final String params;

  MyGridView(this.params, {Key key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _MyGridView(params);
  }
}

class _MyGridView extends State<MyGridView> {
  MethodChannel _methodChannel = MethodChannel("MethodChannelPlugin");
  List<Story> allStory = new List();
  int pageNo = 1;
  List<String> listParams;
  final String params;
  bool isHaveNext = false;
  bool isLoading = false;

  _MyGridView(this.params) {
    _groupMoreData(pageNo.toString(), params);
  }

  @override
  void initState() {
    // ignore: missing_return
    _methodChannel.setMethodCallHandler((handler) => Future<String>(() {}));
    super.initState();
  }

  //flutter调用native的相应方法
  void _sendToNative(String method, [dynamic tips]) {
    Future<String> future = _methodChannel.invokeMethod(method, tips);
    future.then((message) {});
  }

  @override
  Widget build(BuildContext context) {
    return new NotificationListener(
        onNotification: onNotification,
        child: RefreshIndicator(
          child: GridView.count(
            crossAxisCount: 3,
//            physics: const AlwaysScrollableScrollPhysics(),
            padding: EdgeInsets.fromLTRB(13.0, 5.0, 13.0, 3.0),
            //宽高比
            childAspectRatio: 426 / 611,
            mainAxisSpacing: 6,
            children: allStory.map((Story city) {
              return _getGridViewItemUI(context, city);
            }).toList(),
          ),
          onRefresh: _handleRefresh,
        ));
  }

  Widget _getGridViewItemUI(BuildContext context, Story city) {
    return GestureDetector(
        child: Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Card(
            elevation: 2.0,
            shape: const RoundedRectangleBorder(
                borderRadius: BorderRadius.all(Radius.circular(3.0))),
            child: AspectRatio(
              aspectRatio: 214 / 272, // 宽高比
              child: ClipRRect(
                borderRadius: BorderRadius.circular(3.0),
                child: Image.network(
                  city.cover,
                  fit: BoxFit.fill,
                ),
              ),
            ),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(3.0, 3.0, 3.0, 1.0),
            child: Text(
              city.name,

              ///文本只显示一行
              softWrap: false,

              ///多出的文本渐隐方式
              overflow: TextOverflow.fade,
              style: TextStyle(
                  color: const Color(0xff323232),
                  fontSize: 11.0,
                  fontWeight: FontWeight.normal),
            ),
          ),
        ],
      ),
    ));
  }

  Future<Null> _handleRefresh() async {
    await Future.delayed(Duration(milliseconds: 200));
    pageNo = 1;
    _groupMoreData(pageNo.toString(), params);
    return null;
  }

  bool onNotification(Notification notification) {
    if (notification is! ScrollNotification) {
      // 如果不是滚动事件，直接返回
      return false;
    }
    ScrollNotification scroll = notification as ScrollNotification;
    // 当前滑动距离
    double currentExtent = scroll.metrics.pixels;
    double maxExtent = scroll.metrics.maxScrollExtent;
    if (currentExtent == maxExtent) {
      //加载更多操作
      if (isHaveNext) _groupMoreData(pageNo.toString(), params);
    }
    // 返回false，继续向上传递,返回true则不再向上传递
    return false;
  }

  _groupMoreData(String pageno, String groupId) {
    if (isLoading) return;
    print("pageno=====" + pageno);
    isLoading = true;
    API.getMoreStorys(pageno, groupId, (String data) {
      /*将字符串转成json  返回的是键值对的形式*/
      Map<String, dynamic> news = jsonDecode(data);
      var userResult = StoryResult.fromJson(news);
      setState(() {
        if (pageno == "1")
          allStory = userResult.storys;
        else
          allStory.addAll(userResult.storys);
        pageNo++;
        isLoading = false;
        isHaveNext = userResult.page.hasNext;
      });
      _sendToNative("loadingTips", 'false');
    }, (String error) {
      print('error===' + error);
      _sendToNative("loadingTips", 'false');
      setState(() {
        isLoading = false;
      });
    });
  }
}
