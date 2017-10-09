package com.ung.foodfacts;

/**
 * Created by Ung on 09/10/2017.
 */

public class Product {
    private String product_name;
    private String image_small_url;
    private String ingredients_text_fr;
    private Nutriments nutriments;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage_small_url() {
        return image_small_url;
    }

    public void setImage_small_url(String image_small_url) {
        this.image_small_url = image_small_url;
    }

    public String getIngredients_text_fr() {
        return ingredients_text_fr;
    }

    public void setIngredients_text_fr(String ingredients_text_fr) {
        this.ingredients_text_fr = ingredients_text_fr;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }
}
