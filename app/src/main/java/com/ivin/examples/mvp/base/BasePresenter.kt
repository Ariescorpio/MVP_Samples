package com.ivin.examples.mvp.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * @author xiekun
 * @data 2019-10-05
 * @email xiekun265@gmail.com
 * @remark 基础Presenter
 */
abstract class BasePresenter<M: IBaseModel, V: IBaseView> : IBasePresenter {

    private var model: M? = null
    private lateinit var  proxyView: V
    private var weakReference: WeakReference<V>? = null
    private var disposables = CompositeDisposable()

    /**
     * 绑定View
     */
    override fun attachView(view: IBaseView) {
        weakReference = WeakReference(view as V)
        proxyView = Proxy.newProxyInstance(view.javaClass.classLoader, view.javaClass.interfaces, ViewHandler(
            weakReference!!.get()!!
        )) as V
    }

    /**
     * 解绑View
     */
    override fun detachView() {
        if (isViewAttached()) {
            model = null
            weakReference?.clear()
            weakReference = null
        }
    }

    /**
     * 是否与View建议链接
     */
    protected fun isViewAttached(): Boolean {
        return null != weakReference?.get()
    }

    /**
     * 创建Model
     */
    abstract fun createModel(): M

    /**
     * 将网络请求添加到 RxJava2 生命周期
     */
    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    /**
     * 消费所有网络事件
     */
    fun dispose() {
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
        disposables.clear()
    }


    private inner class ViewHandler(view: IBaseView) : InvocationHandler {

        var mvpView = view

        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
            if (isViewAttached()) {
                method?.let {
                    return it.invoke(mvpView, args)
                }
            }
            return Any()
        }
    }

}