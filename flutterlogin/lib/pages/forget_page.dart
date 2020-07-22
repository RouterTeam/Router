import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutterlogin/constant/constant.dart';
import 'package:flutterlogin/widgets/EditTextFieldWidget.dart';

// ignore: must_be_immutable
class ForgetPage extends StatelessWidget {
  String phone;

  ForgetPage(this.phone, {Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        color: Colors.white,
        theme: ThemeData(primaryColor: Colors.white),
        home: WillPopScope(
            child: Scaffold(
                backgroundColor: Colors.white,
                appBar: AppBar(
                  centerTitle: true,
                  title: Text('找回密码'),
                  backgroundColor: Colors.white,
                  leading: IconButton(
                    icon: Icon(Icons.chevron_left),
                    onPressed: () => Navigator.of(context).pop(),
                  ),
                ),
                resizeToAvoidBottomPadding: false,
                body: ForgetView(phone))));
  }
}

class ForgetView extends StatefulWidget {
  String phone = '';

  ForgetView(this.phone);

  @override
  State<StatefulWidget> createState() {
    return _ForgetView(phone);
  }
}

class _ForgetView extends State<ForgetView> {
  String hintPhone = '手机号';
  String hintCode = '请输入验证码';
  String hintPassword = '请输入4~20位密码';
  String phone = '';

  //1:申请变量
  var defaultText = new TextEditingController();

  _ForgetView(this.phone);

  @override
  void initState() {
    super.initState();
    defaultText.text = phone;
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      body: Container(
          color: Colors.white,
          padding: EdgeInsets.fromLTRB(40.0, 60.0, 40.0, 30.0),
          child: SafeArea(
            child: Stack(
              children: <Widget>[
                Column(
                  crossAxisAlignment: CrossAxisAlignment.end,
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: <Widget>[
                    SizedBox(
                      width: 10,
                      height: 50,
                    ),
                    Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: <Widget>[
                        Container(
                          height: 50,
                          alignment: AlignmentDirectional.center,
                          child: Text("+86",
                              textScaleFactor: 1.0,
                              textAlign: TextAlign.justify,
                              style: TextStyle(
                                  color: const Color(0xff323232), //字体颜色
                                  fontSize: 16, //字体大小
                                  height: 1)),
                        ),
                        SizedBox(
                          width: 5,
                          height: 50,
                        ),
                        Image.asset(Constant.ASSETS_IMG + 'login_arrow.png',
                            width: 5, height: 5),
                        SizedBox(
                          width: 10,
                          height: 50,
                        ),
                        SizedBox(
                          //垂直分割线
                          width: 1,
                          height: 20,
                          child: DecoratedBox(
                            decoration:
                                BoxDecoration(color: const Color(0xff323232)),
                          ),
                        ),
                        SizedBox(
                          width: 10,
                          height: 50,
                        ),
                        EditTextFieldWidget(
                          margin: EdgeInsets.all(0),
                          hintText: hintPhone,
                          defaultText: defaultText,
                          inputFormatter:[//只允许输入数字
                            WhitelistingTextInputFormatter(RegExp("[a-zA-Z]")),WhitelistingTextInputFormatter.digitsOnly],
                          onTab: () {},
                        ),
                      ],
                    ),
                    Divider(
                        //水平分割线
                        height: 10.0,
                        indent: 0.0,
                        color: const Color(0xffc4c4c4)),
                    Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: <Widget>[
                        EditTextFieldWidget(
                          margin: EdgeInsets.all(0),
                          hintText: hintCode,
                          inputFormatter: [//只允许输入数字
                            WhitelistingTextInputFormatter.digitsOnly],
                          onTab: () {},
                        ),
                        Container(
                          height: 30,
                          width: 95,
                          alignment: AlignmentDirectional.center,
                          decoration: BoxDecoration(

                              ///圆角
                              borderRadius: BorderRadius.circular(7),

                              ///边框颜色、宽
                              border: Border.all(
                                  color: const Color(0xffea4c44), width: 1)),
                          child: Text("发送验证码",
                              textScaleFactor: 1.0,
                              textAlign: TextAlign.justify,
                              style: TextStyle(
                                  color: const Color(0xffea4c44), //字体颜色
                                  fontSize: 14, //字体大小
                                  height: 1)),
                        ),
                      ],
                    ),
                    Divider(
                        //水平分割线
                        height: 0.0,
                        indent: 0.0,
                        color: const Color(0xffc4c4c4)),
                    Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: <Widget>[
                        EditTextFieldWidget(
                          margin: EdgeInsets.all(0),
                          hintText: hintPassword,
                          inputFormatter: [//只允许输入字母 数字
                            WhitelistingTextInputFormatter(RegExp("[a-zA-Z]")),WhitelistingTextInputFormatter.digitsOnly],
                          onTab: () {},
                        ),
                      ],
                    ),
                    Divider(
                        //水平分割线
                        height: 0.0,
                        indent: 0.0,
                        color: const Color(0xffc4c4c4)),
                    SizedBox(
                      width: 10,
                      height: 35,
                    ),
                    Container(
                      height: 40,
                      width: MediaQuery.of(context).size.width,
                      alignment: AlignmentDirectional.center,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(7),

                        ///圆角
                        color: const Color(0xffea4c44),
                      ),
                      child: Text("确认",
                          textScaleFactor: 1.0,
                          textAlign: TextAlign.justify,
                          style: TextStyle(
                              color: const Color(0xffffffff), //字体颜色
                              fontSize: 14, //字体大小
                              height: 1)),
                    ),
                  ],
                ),
              ],
            ),
          )),
    );
  }
}
