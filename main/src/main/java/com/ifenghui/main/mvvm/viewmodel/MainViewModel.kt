package com.ifenghui.main.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel
import com.ifenghui.main.mvvm.model.MainModel

class MainViewModel(@NonNull application: Application, model: MainModel): BaseViewModel<MainModel>(application,model) {
}