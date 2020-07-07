package com.colin.shelf.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import com.colin.shelf.mvvm.model.ShelfModel
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

class ShelfViewModel (@NonNull application: Application, model:ShelfModel): BaseViewModel<ShelfModel>(application,model) {
}