package com.ivin.examples.mvp

import com.ivin.examples.mvp.base.BaseActivity
import com.ivin.examples.mvp.mvp.MainContract
import com.ivin.examples.mvp.mvp.MainPresenter

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {


    /**
     * 设置xml
     */
    override fun setLayoutRes(): Int {
       return R.layout.activity_main
    }

    /**
     * 创建Presenter
     */
    override fun createPresenter(): MainPresenter {
       return MainPresenter(this)
    }

    /**
     * 初始化
     */
    override fun init() {

    }
}
