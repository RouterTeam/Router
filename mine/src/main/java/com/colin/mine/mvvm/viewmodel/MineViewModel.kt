package com.colin.mine.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import com.colin.mine.mvvm.model.MineModel
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

class MineViewModel (@NonNull application: Application, model:MineModel): BaseViewModel<MineModel>(application,model) {
}