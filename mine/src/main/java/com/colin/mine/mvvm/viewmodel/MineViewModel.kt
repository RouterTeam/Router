package com.colin.mine.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.colin.mine.R
import com.colin.mine.mvvm.model.MineModel
import com.ifenghui.apilibrary.api.entity.MineResult
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

class MineViewModel(@NonNull application: Application, model: MineModel) :
    BaseViewModel<MineModel>(application, model) {
    var mMineDetails: MutableLiveData<MineResult> = MutableLiveData()

    fun getMineData() {
        var result = MineResult()
        result.headerResult = R.mipmap.header
        mMineDetails.value=result
    }
}