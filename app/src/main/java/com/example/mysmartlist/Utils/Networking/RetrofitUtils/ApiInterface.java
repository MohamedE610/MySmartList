package com.example.mysmartlist.Utils.Networking.RetrofitUtils;




import com.example.mysmartlist.Models.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by abdallah on 12/18/2017.
 */

public interface ApiInterface {

/*@GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/

    /*@Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET("clients")
    Call<Product> getCompanyClients();*/

    @GET("products")
    Call<Product> getProducts();

    @GET("products")
    Call<Product> getProductByID();

    @GET("products")
    Call<Product> getProductByCategory();

    @GET("products")
    Call<Product> getCategories();

    @GET("products")
    Call<Product> getCategoryById();

    @GET("products")
    Call<Product> getProductSearch();




    /*  @GET("clients")
      Call<Clients> getCompanyClients(@Header("Accept") String acceptVal ,@Header("Content-Type") String contentTypeVal);*/
}
