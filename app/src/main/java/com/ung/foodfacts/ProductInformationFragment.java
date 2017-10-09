package com.ung.foodfacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Ung on 09/10/2017.
 */

public class ProductInformationFragment extends Fragment {

    static ProductModel product;
    ImageView productPicture;
    TextView productName, productIngredients, productEnergyValue, getProductEnergyValueServing;

    public static ProductInformationFragment newInstance(ProductModel productModel) {

        Bundle args = new Bundle();

        ProductInformationFragment fragment = new ProductInformationFragment();
        fragment.setArguments(args);
        product = productModel;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_info, container, false);

        productPicture = (ImageView) view.findViewById(R.id.iv_product_picture);
        productName = (TextView) view.findViewById(R.id.tv_product_name);
        productIngredients = (TextView) view.findViewById(R.id.tv_product_ingredients);
        productEnergyValue = (TextView) view.findViewById(R.id.tv_product_energy_value);
        getProductEnergyValueServing = (TextView) view.findViewById(R.id.tv_product_energy_value_serving);

        if (product != null) {
            if (product.getProduct() != null) {
                if (product.getProduct().getImage_small_url().length() > 0) {
                    Glide.with(getContext())
                            .load(product.getProduct().getImage_small_url())
                            .placeholder(R.drawable.placeholder)
                            .crossFade()
                            .into(productPicture);
                }
                if (product.getProduct().getProduct_name().length() > 0) {
                    productName.setText(product.getProduct().getProduct_name());
                } else {
                    productName.setText(R.string.name_not_available);
                }
                if (product.getProduct().getIngredients_text_fr().length() > 0) {
                    productIngredients.setText(product.getProduct().getIngredients_text_fr());
                } else {
                    productIngredients.setText(R.string.ingredient_na);
                }
                if (product.getProduct().getNutriments().getEnergy_value().length() > 0) {
                    productEnergyValue.setText(product.getProduct().getNutriments().getEnergy_value() + " kj");
                } else {
                    productEnergyValue.setText(R.string.energy_value_na);
                }
                if (product.getProduct().getNutriments().getEnergy_serving().length() > 0) {
                    getProductEnergyValueServing.setText(product.getProduct().getNutriments().getEnergy_serving() + " kj");
                } else {
                    getProductEnergyValueServing.setText(R.string.energy_serving_na);
                }
            }
        }
        return view;
    }
}
