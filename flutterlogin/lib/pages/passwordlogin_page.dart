import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class PasswordLoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        color: Colors.white,
        theme: ThemeData(primaryColor: Colors.white),
        home: WillPopScope(
            child: Scaffold(
          resizeToAvoidBottomPadding: false,
          body: Text("ddddd"),
          appBar: AppBar(
            centerTitle: true,
            title: Text('登录'),
            backgroundColor: Colors.white,
          ),
        )));
//        home: Scaffold(
//          resizeToAvoidBottomPadding: false,
//          body:  Text("ddddd"),
//          appBar: AppBar(
//            centerTitle:true,
//            title: Text('登录'),
//            backgroundColor: Colors.white,
//          ),
//        ));
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: RaisedButton(
            child: Text('打开第二页'),
            onPressed: () => Navigator.of(context).push(
                  MaterialPageRoute(builder: (context) => SecondPage()),
                )),
      ),
    );
  }
}

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () => _showMessage(context, "信息", "返回键被点击，将要返回第一页"),
      child: Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: Icon(Icons.chevron_left),
            onPressed: () => Navigator.of(context).pop(),
          ),
          title: Text('第二页'),
        ),
        body: Center(
          child: Text('这是第二页'),
        ),
      ),
    );
  }
}

Future<void> _showMessage(BuildContext context, String title, String message) {
  return showDialog<void>(
    context: context,

    barrierDismissible: false, // user must tap button!
    builder: (BuildContext context) {
      return AlertDialog(
        title: Text(title),
        content: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              Text(message),
            ],
          ),
        ),
        actions: <Widget>[
          FlatButton(
            child: Text('OK'),
            onPressed: () {
              Navigator.of(context).pop();
              Navigator.of(context).pop();
            },
          ),
        ],
      );
    },
  );
}
