package com.example.orderform.contract;

import com.example.orderform.base.IBaseView;
import com.example.orderform.model.bean.LoginBean;

/**
 * 契约类，方便统一管理相关接口
 */
public interface ILoginContract {
    /**
     * view层
     */
    interface IView<T> extends IBaseView {
        void onLoginSuccess(LoginBean loginBean);

        void onLoginFailure(Throwable e);
    }

    /**
     * presenter层
     */
    interface IPresenter {
        void login(int status);
    }

    /**
     * model层
     */
    interface IModel {
        void login(int status, IModelCallback callback);

        /**
         * model层中的接口回调
         */
        interface IModelCallback {
            void onLoginSuccess(LoginBean loginBean);

            void onLoginFailure(Throwable e);
        }
    }
}
