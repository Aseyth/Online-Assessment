package com.ung.foodfacts;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayProductSearch();
    }

    public void displayProductSearch() {
        ProductSearchFragment productSearchFragment = new ProductSearchFragment();
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, productSearchFragment);
        trans.commit();
    }

    public void displayProduct(ProductModel product) {
        ProductInformationFragment productInformationFragment = ProductInformationFragment.newInstance(product);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, productInformationFragment, ProductInformationFragment.class.getSimpleName())
                .addToBackStack(ProductInformationFragment.class.getSimpleName())
                .commit();
    }
}
