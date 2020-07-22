import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class EditTextFieldWidget extends StatelessWidget {
  final ValueChanged<String> onSubmitted;
  final ValueChanged<String> onChange;
  final VoidCallback onTab;
  final List<TextInputFormatter>  inputFormatter;
  final String hintText;
  final String labelText;
  ///给文本框赋值
  //1:申请变量
  var defaultText= new TextEditingController();
  final EdgeInsetsGeometry margin;

  EditTextFieldWidget(
      {Key key,this.labelText,this.defaultText, this.hintText,this.inputFormatter, this.onSubmitted,this.onChange, this.onTab, this.margin})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child:  Container(
        margin: margin == null ? EdgeInsets.all(0.0) : margin,
        width: MediaQuery.of(context).size.width,
        alignment: AlignmentDirectional.center,
        height: 50.0,
//        decoration: BoxDecoration(
//            color: Color.fromARGB(0, 237, 236, 237),
//            borderRadius: BorderRadius.circular(24.0)),
        child: TextField(
          onSubmitted: onSubmitted,
          inputFormatters:inputFormatter,
          onTap: onTab,
          onChanged: onChange,
          controller: defaultText,
          cursorColor: Color.fromARGB(255, 192, 191, 191),
          decoration: InputDecoration(
//            contentPadding: const EdgeInsets.only(top: 8.0),
            border: InputBorder.none,
            labelText: labelText,
            hintText: hintText,
            hintStyle: TextStyle(
                fontSize: 16, color: const Color(0xffc4c4c4)),
//            prefixIcon: Icon(
//              Icons.search,
//              size: 25,
//              color: Color.fromARGB(255, 128, 128, 128),
//            )
          ),
          style: TextStyle(fontSize: 16,color: const Color(0xff323232)),
        ),
      )
    );

  }
}
