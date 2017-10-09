package com.ung.foodfacts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Ung on 09/10/2017.
 */

public class ProductSearchFragment extends Fragment {

    Button mScan, mHistory;
    EditText mBarcodeNumber;
    ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_search, container, false);

        mScan = (Button) view.findViewById(R.id.button_scan);
        mHistory = (Button) view.findViewById(R.id.button_history);
        mBarcodeNumber = (EditText) view.findViewById(R.id.edit_barcode_number);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_loading);
        mScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                if (mBarcodeNumber.getText().length() > 0) {
                    NetworkManager.getInstance().getProduct(mBarcodeNumber.getText().toString(), new NetworkManager.ResultHandler<ProductModel>() {
                        @Override
                        public void success(ProductModel result) {
                            if (result.getProduct() == null) {
                                mProgressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), R.string.no_product, Toast.LENGTH_LONG).show();
                            } else {
                                ProductCacheHelper.saveProductCache(result, getContext());
                                mProgressBar.setVisibility(View.GONE);
                                ProductInformationFragment productInformationFragment = ProductInformationFragment.newInstance(result);
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment, productInformationFragment, ProductInformationFragment.class.getSimpleName())
                                        .addToBackStack(ProductInformationFragment.class.getSimpleName())
                                        .commit();
                            }
                        }

                        @Override
                        public void error(String error) {
                            mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), R.string.search_failed, Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), R.string.barcode_empty, Toast.LENGTH_LONG).show();
                }
            }
        });
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductHistoryFragment productHistoryFragment = new ProductHistoryFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, productHistoryFragment, ProductInformationFragment.class.getSimpleName())
                        .addToBackStack(ProductHistoryFragment.class.getSimpleName())
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
