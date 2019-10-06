package com.ivin.examples.mvp.mvp

import com.ivin.examples.mvp.base.IBaseModel
import com.ivin.examples.mvp.base.IBasePresenter
import com.ivin.examples.mvp.base.IBaseView

/**
 * @author xiekun
 * @data 2019-10-06
 * @email xiekun265@gmail.com
 * @remark TODO 描述
 */
class MainContract {

    interface Model: IBaseModel

    interface View: IBaseView

    interface Presenter: IBasePresenter

}