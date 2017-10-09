package com.ung.foodfacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Ung on 09/10/2017.
 */

public class ProductCacheHelper {

    public static String PREF_PRODUCT_CACHE = "PRODUCT_CACHE";

    public static void saveProductCache(ProductModel productModel, Context context) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEditor = mPreferences.edit();
        ArrayList<ProductModel> previousProductsCached = new ArrayList<>();
        if (loadProductsCache(context) != null) {
            previousProductsCached = loadProductsCache(context);
            for (int i = 0; i < previousProductsCached.size(); i++) {
                if (productModel.getProduct().getProduct_name().equals(previousProductsCached.get(i).getProduct().getProduct_name())) {
                    return; //stop saving the product because it is already saved in cache
                }
            }
        }
        previousProductsCached.add(productModel);
        String serialized = serializeProductToJson(previousProductsCached);
        mEditor.putString(PREF_PRODUCT_CACHE, serialized);
        mEditor.apply();
    }

    public static ArrayList<ProductModel> loadProductsCache(Context context) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return deserializeProductFromJson(mPreferences.getString(PREF_PRODUCT_CACHE, ""));
    }

    private static String serializeProductToJson(ArrayList<ProductModel> devices) {
        Gson gson = new Gson();
        String j = gson.toJson(devices);
        return j;
    }

    private static ArrayList<ProductModel> deserializeProductFromJson(String jsonString) {
        Gson gson = new Gson();
        try {
            ArrayList<ProductModel> myClass = gson.fromJson(jsonString, new TypeToken<ArrayList<ProductModel>>() {
            }.getType());
            return myClass;
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}
