package com.ivin.examples.mvp.mvp

import com.ivin.examples.mvp.base.BasePresenter

/**
 * @author xiekun
 * @data 2019-10-06
 * @email xiekun265@gmail.com
 * @remark TODO 描述
 */
class MainPresenter(view: MainContract.View): BasePresenter<MainModel, MainContract.View>(), MainContract.Presenter {

    override fun createModel(): MainModel {
        return MainModel()
    }
}