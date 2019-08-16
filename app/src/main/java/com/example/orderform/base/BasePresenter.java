package com.example.orderform.base;

import android.content.Context;

import com.example.orderform.app.App;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends IBaseView> {

    private WeakReference<V> mWeakReference;

    protected void attachView(V v) {
        mWeakReference = new WeakReference<>(v);
    }

    protected void detachView() {
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    /**
     * 防止出现 presenter 层 view 调用空指针
     * 每次调用业务请求的时候都要先调用方法检查是否与 View 绑定
     * 只有返回ture才进行回调
     */
    protected boolean isViewAttached() {
        if (mWeakReference == null || mWeakReference.get() == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取连接的view
     * 每只有 isViewAttached 返回ture的时候，才能调用他
     */
    protected V getView() {
        return mWeakReference.get();
    }

    /**
     * context为上下文，上下文为null，则返回应用上下文
     */
    protected Context context() {
        if (isViewAttached() && getView().context() != null) {
            return getView().context();
        }
        return App.getAppContext();
    }
}
