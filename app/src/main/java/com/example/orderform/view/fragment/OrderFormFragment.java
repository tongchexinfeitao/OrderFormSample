package com.example.orderform.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orderform.R;
import com.example.orderform.model.api.ILoginApi;
import com.example.orderform.model.bean.FormBean;
import com.example.orderform.utils.RetrofitManager;
import com.example.orderform.view.adapter.OrderFormAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OrderFormFragment extends Fragment {

    public static  OrderFormFragment newInstance(int status) {
        OrderFormFragment orderFormFragment = new OrderFormFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        orderFormFragment.setArguments(bundle);
        return orderFormFragment;
    }


    @BindView(R.id.xrv_order_form)
    XRecyclerView mXrvOrderForm;
    Unbinder unbinder;
    private OrderFormAdapter mOrderFormAdapter;
    List<FormBean.OrderListBean> mOrderList = new ArrayList<>();

    //要查询的对应的订单
    int status = 0;
    int page = 1;
    private Observer<FormBean> mObserver;
    boolean isLoadmore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.frgment_order_form_layout, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        status = getArguments().getInt("status");

        mObserver = new Observer<FormBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FormBean formBean) {
                if (isLoadmore) {
                    mXrvOrderForm.loadMoreComplete();
                } else {
                    mXrvOrderForm.refreshComplete();
                }
                Log.e("TAG", "onNext" + formBean.toString());
                mOrderFormAdapter.setData(formBean.getOrderList(), isLoadmore);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError" + e.toString());

            }

            @Override
            public void onComplete() {

            }
        };

        mXrvOrderForm.setLayoutManager(new LinearLayoutManager(getContext()));
        mOrderFormAdapter = new OrderFormAdapter(mOrderList);
        mXrvOrderForm.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isLoadmore = false;
                RetrofitManager.getInstance()
                        .create(ILoginApi.class)
                        .queryAllOrderForm(8112, "15658556002418112", status, page, 5)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mObserver);
            }

            @Override
            public void onLoadMore() {
                isLoadmore = true;
                page++;
                RetrofitManager.getInstance()
                        .create(ILoginApi.class)
                        .queryAllOrderForm(8112, "15658556002418112", 0, page, 5)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mObserver);
            }
        });
        mXrvOrderForm.setAdapter(mOrderFormAdapter);


        RetrofitManager.getInstance()
                .create(ILoginApi.class)
                .queryAllOrderForm(8112, "15658556002418112", 0, page, 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
