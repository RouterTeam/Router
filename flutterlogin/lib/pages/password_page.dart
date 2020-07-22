import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:flutterlogin/constant/constant.dart';
import 'package:flutterlogin/widgets/EditTextFieldWidget.dart';

import '../router.dart';

class PasswordPage extends StatelessWidget {
  String phone='' ;
  PasswordPage(this.phone,{ Key key}): super(key: key);
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
                  title: Text('手机账号登录'),
                  backgroundColor: Colors.white,
                  leading: IconButton(
                    icon: Icon(Icons.chevron_left),
                    onPressed: () => Navigator.of(context).pop(),
                  ),
                ),
                resizeToAvoidBottomPadding: false,
                body: PassWordView(phone))));
  }
}

class PassWordView extends StatefulWidget {
  String phone='' ;
  PassWordView(this.phone,{ Key key}): super(key: key);
  @override
  State<StatefulWidget> createState() {
    return _PasswordView(phone);
  }
}

class _PasswordView extends State<PassWordView> {
  String hintPhone = '手机号';
  String hintCode = '请输入4～20位密码';
  String phone='' ;
  //1:申请变量
  var defaultText= new TextEditingController();
  _PasswordView(this.phone);

  @override
  void initState() {
    super.initState();
    defaultText.text=phone;
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
          color: Colors.white,
          padding: EdgeInsets.fromLTRB(40.0, 30.0, 40.0, 30.0),
          child: SafeArea(
            child: Stack(
              children: <Widget>[
                Column(
                  crossAxisAlignment: CrossAxisAlignment.end,
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: <Widget>[
                    SizedBox(
                      width: 10,
                      height: 100,
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
                          inputFormatter: [WhitelistingTextInputFormatter.digitsOnly],
                          defaultText: defaultText,
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
                      height: 50,
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
                      child: Text("登录",
                          textScaleFactor: 1.0,
                          textAlign: TextAlign.justify,
                          style: TextStyle(
                              color: const Color(0xffffffff), //字体颜色
                              fontSize: 14, //字体大小
                              height: 1)),
                    ),
                    GestureDetector(
                      child: Text(
                        "忘记密码",
                        textAlign: TextAlign.end,
                        style: TextStyle(
                            fontSize: 13,
                            height: 3,
                            color: const Color(0xff5a7cab)),
                      ),
                      onTap: () {
                        Router.push(context, Router.forgetPage,phone);
                      },
                    )
                  ],
                ),
                Positioned(
                  bottom: 0,
                  child: Container(
                    width: MediaQuery.of(context).size.width - 80,
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: <Widget>[
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: <Widget>[
                            Image.asset(
                              Constant.ASSETS_IMG + 'icon_selected1.png',
                              alignment: Alignment.centerLeft,
                              width: 20,
                              height: 16,
                            ),
                            RichText(
                              text: TextSpan(
                                  text: " 登录即表示同意 ",
                                  style: TextStyle(
                                      fontSize: 13, color: Color(0xff999999)),
                                  children: <TextSpan>[
                                    TextSpan(
                                      text: "《用户协议》",
                                      style:
                                          TextStyle(color: Color(0xff5a7cab)),
                                    ),
                                    TextSpan(
                                      text: "《隐私政策》",
                                      style:
                                          TextStyle(color: Color(0xff5a7cab)),
                                    )
                                  ]),
                            )
                          ],
                        )
                      ],
                    ),
                  ),
                )
              ],
            ),
          )),
    );
  }
}
