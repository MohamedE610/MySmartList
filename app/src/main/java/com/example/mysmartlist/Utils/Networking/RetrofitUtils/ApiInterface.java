package com.example.mysmartlist.Utils.Networking.RetrofitUtils;




import com.example.mysmartlist.Models.Product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    /***************         products           *****************/


    @DELETE("clients/{client_id}/pin/{product_id}")
    Call<Product> deletePinProduct(@Path("client_id") int client_id,@Path("product_id") int product_id);

    @DELETE("clients/{client_id}/fav/{product_id}")
    Call<Product> deleteFavouriteProduct(@Path("client_id") int client_id,@Path("product_id") int product_id);

    //pass json{}
    @POST("products")
    Call<Product> addProductToDatabase(@Body Product  product);

    @POST("products/search")
    Call<Product> getProductSearchResult(@Body Product  product);

    @GET("products")
    Call<Product> getProducts();

    @GET("new/products")
    Call<Product> getNewProducts();

    @GET("popular/products")
    Call<Product> getPopularProducts();

    @GET("top/products")
    Call<Product> getTopProducts();

    @GET("products/{product_id}")
    Call<Product> getProductByID(@Path("product_id") int product_id);

    @GET("categories/{category_id}")
    Call<Product> getProductByCategory(@Path("category_id") int category_id);


    /***************         Categories           *****************/

    @GET("categories")
    Call<Product> getCategories();

  /*  @GET("categories/{category}")
    Call<Product> getCategoryById(@Path("category_id") int category_id);*/


    /***************         clients           *****************/

    @GET("/clients/{client_id}")
    Call<Product> getClientByID(@Path("client_id") int client_id);

    @GET("clients/{client_id}/lists")
    Call<Product> getCurrentClientLists(@Path("client_id") int client_id);

    @GET("clients/{client_id}/lists/old")
    Call<Product> getOldClientLists(@Path("client_id") int client_id);

    @GET("clients/{client_id}/lists/all")
    Call<Product> getAllClientLists(@Path("client_id") int client_id);

    @GET("clients/{client_id}/fav/{product_id}")
    Call<Product> AddFavouriteProduct(@Path("client_id") int client_id ,@Path("product_id") int product);

    @GET("clients/{client_id}/pin/{product_id}")
    Call<Product> AddPinProduct(@Path("client_id") int client_id ,@Path("product_id") int product);

    //pass json{}
    @POST("clients")
    Call<Product> createClientAccount(@Body Product  client);


    /***************         List           *****************/

    @GET("lists/{list_id}")
    Call<Product> getListById(@Path("list_id") int client_id);

    //pass json{}
    @POST("clients/{client_id}/lists")
    Call<Product> addListToDatabase(@Path("client_id") String client_id ,@Body Product  list);

    @POST("lists/{list_id}/products")
    Call<Product> addProductToList(@Path("list_id") String client_id ,@Body Product  list);

    @POST("lists/{list_id}/products/{product_id}/increment")
    Call<Product> incrementProductCountInList(@Path("list_id") String client_id ,@Path("product_id") String product_id ,@Body Product  list);

    @POST("lists/{list_id}/products/{product_id}/decrement")
    Call<Product> decrementProductCountInList(@Path("list_id") String client_id ,@Path("product_id") String product_id ,@Body Product  list);

    @DELETE("lists/{list_id}/products")
    Call<Product> deleteMultipulProduct(@Path("list_id") int client_id,@Body Product  list);

    @DELETE("lists/{list_id}/products/{product_id}")
    Call<Product> deleteSingleProduct(@Path("list_id") int client_id,@Path("product_id") String product_id );

    @DELETE("lists/{list_id}/trash")
    Call<Product> MoveListFromCurrentListToOldList(@Path("list_id") int client_id);

    @DELETE("lists/{list_id}")
    Call<Product> deleteListPermanently(@Path("list_id") int client_id);

}
