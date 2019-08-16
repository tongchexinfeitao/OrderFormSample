package com.example.orderform.view.adapter;

import android.support.annotation.NonNull;
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

/**
 * 内部商品recyclerview的adapter
 */
public class OrderFormChildProductAdaper extends XRecyclerView.Adapter<OrderFormChildProductAdaper.MyChildViewHolder> {

    private List<FormBean.OrderListBean.DetailListBean> mDetailList;

    public OrderFormChildProductAdaper(List<FormBean.OrderListBean.DetailListBean> detailList) {

        this.mDetailList = detailList;
    }

    @NonNull
    @Override
    public MyChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child_order_form_product_layout, viewGroup, false);
        return new MyChildViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChildViewHolder myChildViewHolder, int i) {
        myChildViewHolder.mTvProductPrice.setText(mDetailList.get(i).getCommodityPrice()+"");
    }

    @Override
    public int getItemCount() {
        return mDetailList == null ? 0 : mDetailList.size();
    }

    public class MyChildViewHolder extends XRecyclerView.ViewHolder {
        @BindView(R.id.tv_product_price)
        TextView mTvProductPrice;

        public MyChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
