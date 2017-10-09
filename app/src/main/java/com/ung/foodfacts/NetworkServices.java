package com.ung.foodfacts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ung on 09/10/2017.
 */

public class NetworkServices {

    public interface NetworkRequest {

        @GET("{path}")
        Call<ProductModel> getProduct(
                @Path("path") String myPath
        );
    }
}
