import 'dart:async';

import 'package:flutter/widgets.dart';

class CountDownWidget extends StatefulWidget {
  final onCountDownFinishCallBack;
  final chargeCodePhone;

  CountDownWidget(
      {Key key, this.chargeCodePhone, @required this.onCountDownFinishCallBack})
      : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _CountDownWidget(
        chargeCodePhone: chargeCodePhone,
        onCountDownFinishCallBack: onCountDownFinishCallBack);
  }
}

class _CountDownWidget extends State<CountDownWidget> {
  final onCountDownFinishCallBack;
  final chargeCodePhone;
  var _seconds = 6;
  Timer _timer;
  bool isGetting = false;

  _CountDownWidget(
      {this.chargeCodePhone, @required this.onCountDownFinishCallBack})
      : super();

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: Container(
        height: 30,
        width: 95,
        alignment: AlignmentDirectional.center,
        decoration: BoxDecoration(

            ///圆角
            borderRadius: BorderRadius.circular(7),

            ///边框颜色、宽
            border: Border.all(
                color: isGetting
                    ? const Color(0x5fea4c44)
                    : const Color(0xffea4c44),
                width: 1)),
        child: Text(isGetting ? '$_seconds' + 's重新获取' : "发送验证码",
            textScaleFactor: 1.0,
            textAlign: TextAlign.justify,
            style: TextStyle(
                color: isGetting
                    ? const Color(0x5fea4c44)
                    : const Color(0xffea4c44), //字体颜色
                fontSize: 14, //字体大小
                height: 1)),
      ),
      onTap: () {
        if (isGetting) return;
        if (widget.chargeCodePhone()) {
          widget.onCountDownFinishCallBack(false);
          return;
        }
        _startTimer();
      },
    );
  }

  /// 启动倒计时的计时器。
  void _startTimer() {
    _seconds = 59;
    _timer = Timer.periodic(Duration(seconds: 1), (timer) {
      setState(() {});
      if (_seconds <= 1) {
        widget.onCountDownFinishCallBack(true);
        _cancelTimer();
        return;
      }
      _seconds--;
      print('$_seconds' + "ddd");
    });
    isGetting = true;
  }

  /// 取消倒计时的计时器。
  void _cancelTimer() {
    isGetting = false;
    _timer?.cancel();
  }
}
