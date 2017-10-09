package com.ung.foodfacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ung on 09/10/2017.
 */

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    List<ProductModel> productList;
    int mLayout;
    Context mContext;

    public ProductRecyclerViewAdapter(List<ProductModel> productList, int mLayout, Context context) {
        this.productList = productList;
        this.mLayout = mLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        return new ViewHolder(v, mContext);
    }

    @Override
    public void onBindViewHolder(final ProductRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = productList.get(position);
        holder.productName.setText(holder.mItem.getProduct().getProduct_name());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ProductModel mItem;
        private TextView productName;
        private RelativeLayout itemContainer;

        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name_recycler_view);
            itemContainer = (RelativeLayout) itemView.findViewById(R.id.item_container);
            itemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) view.getContext()).displayProduct(mItem);
                }
            });
        }
    }
}
