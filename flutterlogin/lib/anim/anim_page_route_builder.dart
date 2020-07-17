
import 'package:flutter/widgets.dart';
/// 自定义页面切换动画 - 渐变切换
class CustomPageRouteBuilder extends PageRouteBuilder{
  // 跳转的页面
  final Widget widget;
  CustomPageRouteBuilder(this.widget):super(
      transitionDuration:Duration(seconds: 1),
      pageBuilder:(BuildContext context, Animation<double> animation, Animation<double> secondaryAnimation){
        return widget;
      },
      transitionsBuilder:(BuildContext context, Animation<double> animation, Animation<double> secondaryAnimation, Widget child){
        return FadeTransition(child:child,
            opacity: Tween(begin: 0.0,end: 1.0)
                .animate(CurvedAnimation(parent: animation, curve: Curves.fastOutSlowIn))
        );
      }
  );
}
/// 自定义页面切换动画 - 缩放切换
class ScalePageRouteBuilder extends PageRouteBuilder{
  // 跳转的页面
  final Widget widget;
  ScalePageRouteBuilder(this.widget):super(
      transitionDuration:Duration(seconds: 1),
      pageBuilder:(BuildContext context, Animation<double> animation, Animation<double> secondaryAnimation){
        return widget;
      },
      transitionsBuilder:(BuildContext context, Animation<double> animation, Animation<double> secondaryAnimation, Widget child){
        return ScaleTransition(child:child,
            scale: Tween(begin: 0.0,end: 1.0)
                .animate(CurvedAnimation(parent: animation, curve: Curves.fastOutSlowIn))
        );
      }
  );
}
/// 自定义页面切换动画 - 旋转+缩放切换
class RotationScalePageRouteBuilder extends PageRouteBuilder {
  // 跳转的页面
  final Widget widget;

  RotationScalePageRouteBuilder(this.widget) :super(
      transitionDuration: Duration(seconds: 1),
      pageBuilder: (BuildContext context, Animation<double> animation,
          Animation<double> secondaryAnimation) {
        return widget;
      },
      transitionsBuilder: (BuildContext context, Animation<double> animation,
          Animation<double> secondaryAnimation, Widget child) {
        return RotationTransition(
          turns: Tween(begin: 0.0, end: 1.0)
              .animate(
              CurvedAnimation(parent: animation, curve: Curves.fastOutSlowIn)),
          child: ScaleTransition(scale: Tween(begin: 0.0, end: 1.0)
              .animate(
            CurvedAnimation(parent: animation, curve: Curves.fastOutSlowIn),),
            child: child,),
        );
      }
  );
}

/// 自定义页面切换动画 - 平移切换
class SlidePageRouteBuilder extends PageRouteBuilder {
  // 跳转的页面
  final Widget widget;

  SlidePageRouteBuilder(this.widget) :super(
      transitionDuration: Duration(milliseconds: 380),
      pageBuilder: (BuildContext context, Animation<double> animation,
          Animation<double> secondaryAnimation) {
        return widget;
      },
      transitionsBuilder: (BuildContext context, Animation<double> animation,
          Animation<double> secondaryAnimation, Widget child) {
        return SlideTransition(
          position: Tween<Offset>(
              begin: Offset(1.0, 0.0), end: Offset(0.0, 0.0))
              .animate(
              CurvedAnimation(parent: animation, curve: Curves.fastOutSlowIn)),
          child: child,);
      }
  );
}
