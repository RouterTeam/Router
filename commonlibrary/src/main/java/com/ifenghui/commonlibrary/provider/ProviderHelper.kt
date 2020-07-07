package com.ifenghui.commonlibrary.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.ifenghui.commonlibrary.application.Constance

object ProviderHelper {
    var mProvider: IMainProvider? = null
    private fun getProvider(providerFlag: String) {
        try {
            var c: Class<*>? = null
            when (providerFlag) {
                Constance.HOME_FRAGMENT_FLAG -> {
                    c = Class.forName(Constance.HOME_PROVER_PATH)
                }
                Constance.MAGAZINE_FRAGMENT_FLAG -> {
                    c = Class.forName(Constance.MAGAZINE_PROVER_PATH)
                }
                Constance.SHELF_FRAGMENT_FLAG -> {
                    c = Class.forName(Constance.SHELF_PROVER_PATH)
                }
                Constance.MINE_FRAGMENT_FLAG -> {
                    c = Class.forName(Constance.MINE_PROVER_PATH)
                }
                Constance.LOGIN_FRAGMENT_FLAG -> {
                    c = Class.forName(Constance.LOGIN_PROVER_PATH)
                }
            }
            mProvider = c?.newInstance() as IMainProvider
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
    }

    /**
     * 获取fragment实例
     */
    fun getFragment(providerFlag: String): Fragment? {
        getProvider(providerFlag)
        return mProvider?.getMainFragment()
    }

    /**
     * 跳转页面
     */
    fun startAct(providerFlag: String, context: Context?, flag: String?, vararg objects: Any?){
        getProvider(providerFlag)
        mProvider?.distribution(context,flag,objects)
    }
}