package com.example.orderform.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orderform.R;
import com.example.orderform.model.bean.FormBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFormAdapter extends XRecyclerView.Adapter<OrderFormAdapter.MyViewHolder> {

    private List<FormBean.OrderListBean> mOrderList;

    public OrderFormAdapter(List<FormBean.OrderListBean> orderList) {
        this.mOrderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_form_layout, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mTvOrderId.setText(mOrderList.get(i).getOrderId());
        myViewHolder.mRvOrderProduct.setLayoutManager(new LinearLayoutManager(myViewHolder.itemView.getContext()));

        //给子recyclerview赋值
        myViewHolder.orderFormChildProductAdaper = new OrderFormChildProductAdaper(mOrderList.get(i).getDetailList());
        myViewHolder.mRvOrderProduct.setAdapter(myViewHolder.orderFormChildProductAdaper);
    }

    @Override
    public int getItemCount() {
        return mOrderList == null ? 0 : mOrderList.size();
    }

    public void setData(List<FormBean.OrderListBean> formBeans, boolean isLoadmore) {
        if (!isLoadmore) {
            mOrderList.clear();
            mOrderList.addAll(formBeans);
            notifyDataSetChanged();
        } else {
            mOrderList.addAll(formBeans);
            notifyDataSetChanged();
        }
    }


    static class MyViewHolder extends XRecyclerView.ViewHolder {
        @BindView(R.id.tv_order_id)
        TextView mTvOrderId;

        //内部的recyclerview
        @BindView(R.id.rv_order_product)
        RecyclerView mRvOrderProduct;
        //内部recyclerview的adapter
        OrderFormChildProductAdaper orderFormChildProductAdaper;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
