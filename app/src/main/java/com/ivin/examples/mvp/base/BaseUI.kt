package com.ivin.examples.mvp.base

import android.content.Context
import android.view.View
import com.ivin.examples.mvp.utils.LoadingDialog
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * @author xiekun
 * @data 2019-10-05
 * @email xiekun265@gmail.com
 * @remark UI基础处理
 */
class BaseUI(var iBaseUI: IBaseUI) {

    private lateinit var loading: LoadingDialog

    private var disposables = CompositeDisposable()

    interface IBaseUI {
        fun onViewClick(viewId: Int)
    }

    fun init(context: Context) {
        loading = LoadingDialog(context)
    }

    /**
     * 显示“正在加载”提示框
     */
    fun showLoading() {
        loading.show()
    }

    /**
     * 隐藏“正在加载”提示框
     */
    fun hideLoading() {
        loading.dismiss()
    }

    /**
     * LoadingDialog是否显示
     */
    fun isShowing(): Boolean {
        return loading.isShowing
    }

    /**
     * View点击事件处理，防止重复点击
     */
    fun setOnViewClick(vararg views: View) {
        for (view in views) {
            disposables.add(
                view.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ iBaseUI.onViewClick(view.id) }, { it.printStackTrace() })
            )
        }

    }

    fun destroy() {
        loading.let {
            loading.dismiss()
        }
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
        disposables.clear()
    }

}