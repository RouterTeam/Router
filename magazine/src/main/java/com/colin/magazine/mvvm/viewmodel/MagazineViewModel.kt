package com.colin.magazine.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import com.colin.magazine.mvvm.model.MagazineModel
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

class MagazineViewModel (@NonNull application: Application, model:MagazineModel): BaseViewModel<MagazineModel>(application,model) {
}