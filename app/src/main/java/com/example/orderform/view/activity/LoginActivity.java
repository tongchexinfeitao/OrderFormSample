package com.example.orderform.view.activity;

import android.widget.FrameLayout;

import com.example.orderform.R;
import com.example.orderform.base.BaseActivity;
import com.example.orderform.contract.ILoginContract;
import com.example.orderform.model.bean.LoginBean;
import com.example.orderform.presenter.LoginPresenter;
import com.example.orderform.view.fragment.OrderFormFragment;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.IView {

    @BindView(R.id.fl_orderform)
    FrameLayout mFlOrderform;


    @Override
    protected LoginPresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        OrderFormFragment orderFormFragment = OrderFormFragment.newInstance(1);
        getSupportFragmentManager().beginTransaction()
                .commit();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    //失敗
    @Override
    public void onLoginSuccess(LoginBean orderFormBean) {

    }

    @Override
    public void onLoginFailure(Throwable e) {
    }

}
