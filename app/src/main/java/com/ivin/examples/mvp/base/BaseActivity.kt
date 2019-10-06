package com.ivin.examples.mvp.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author xiekun
 * @data 2019-10-06
 * @email xiekun265@gmail.com
 * @remark 基础Activity,其他Activity继承
 */
abstract class BaseActivity<P: BasePresenter<out IBaseModel, out IBaseView>>: AppCompatActivity(), IBaseView, BaseUI.IBaseUI {

    private var baseUI: BaseUI = BaseUI(this)

    var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutRes())
        presenter = createPresenter()
        presenter?.attachView(this)
        baseUI.init(this)
        init()
    }

    /**
     * 设置Activity的布局文件xml
     */
    protected abstract fun setLayoutRes(): Int

    /**
     * 初始化
     */
    protected abstract fun init()

    /**
     * 创建Presenter
     */
    protected abstract fun createPresenter() : P

    /**
     * 设置View点击事件
     */
    protected fun setOnViewClick(vararg views: View) {
        baseUI.setOnViewClick(*views)
    }

    /**
     * BaseUI回调点击事件处理
     */
    override fun onViewClick(viewId: Int) {

    }

    /**
     * MVP的网络处理错误回调
     */
    override fun onApiError(error: String?) {

    }

    /**
     * 显示Loading提示框
     */
    protected fun showProgress() {
        if (isFinishing.not() && baseUI.isShowing().not()) {
            baseUI.showLoading()
        }
    }

    /**
     * 隐藏Loading提示框
     */
    protected fun hideProgress() {
        if (baseUI.isShowing()) {
            baseUI.hideLoading()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        baseUI.destroy()
        presenter?.detachView()
        presenter?.dispose()
        presenter = null
    }
}