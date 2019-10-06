package com.ivin.examples.mvp.base

/**
 * @author ivin
 * @data 2019-10-05
 * @email xiekun265@gmail.com
 * @remark Presenter基础借口
 */
interface IBasePresenter {

    /**
     * 绑定View
     */
    fun attachView(view: IBaseView)

    /**
     * 解绑View
     */
    fun detachView()
}