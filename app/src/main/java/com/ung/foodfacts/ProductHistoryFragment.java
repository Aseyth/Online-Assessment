package com.ung.foodfacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.view.View.GONE;

/**
 * Created by Ung on 09/10/2017.
 */

public class ProductHistoryFragment extends Fragment {

    RecyclerView mProductRecyclerView;
    TextView emptyPlaceholder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_history, container, false);

        mProductRecyclerView = (RecyclerView) view.findViewById(R.id.product_recycler_view);
        emptyPlaceholder = (TextView) view.findViewById(R.id.empty_placeholder);

        if (ProductCacheHelper.loadProductsCache(getContext()) == null) {
            emptyPlaceholder.setVisibility(View.VISIBLE);
        } else {
            emptyPlaceholder.setVisibility(GONE);
            ProductRecyclerViewAdapter mAdapter = new ProductRecyclerViewAdapter(ProductCacheHelper.loadProductsCache(getContext()), R.layout.item_product, getContext());
            mProductRecyclerView.setAdapter(mAdapter);
            mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }
}
