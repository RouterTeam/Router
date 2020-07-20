package com.ifenghui.commonlibrary.application

class Constance {
    companion object {
        //路由方式定义的路径
        const val FRAGMENT_URL_MAG_PAGE: String = "/magazine/MagazineFragment"
        const val FRAGMENT_URL_SHELF_PAGE: String = "/shelf/ShelfFragment"
        const val FRAGMENT_URL_MINE_PAGE: String = "/mine/MineFragment"
        const val FRAGMENT_URL_HOME_PAGE: String = "/home/HomeFragment"

        //反射方式 class路径
        const val HOME_PROVER_PATH="com.ifenghui.home.provider.HomeProvider"
        const val MAGAZINE_PROVER_PATH="com.colin.magazine.provider.MagazineProvider"
        const val SHELF_PROVER_PATH="com.colin.shelf.provider.ShelfProvider"
        const val MINE_PROVER_PATH="com.colin.mine.provider.MineProvider"
        const val LOGIN_PROVER_PATH="com.colin.login.provider.LoginProvider"

        //对应标识
        const val HOME_FRAGMENT_FLAG="home_fragment_flag"
        const val MAGAZINE_FRAGMENT_FLAG="magazine_fragment_flag"
        const val SHELF_FRAGMENT_FLAG="shelf_fragment_flag"
        const val MINE_FRAGMENT_FLAG="mine_fragment_flag"
        const val LOGIN_FRAGMENT_FLAG="login_fragment_flag"



        //eventbus post标示
        const val REFRESH_USER_CODE=1000001
    }
}