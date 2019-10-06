package com.ivin.examples.mvp.base

/**
 * @author ivin
 * @data 2019-10-05
 * @email xiekun265@gmail.com
 * @remark View基础借口
 */
interface IBaseView {


    /**
     * 网络请求数据错误
     */
    fun onApiError(error: String?)
}