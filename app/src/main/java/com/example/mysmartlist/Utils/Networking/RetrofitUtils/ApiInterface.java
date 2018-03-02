package com.example.mysmartlist.Utils.Networking.RetrofitUtils;




import com.example.mysmartlist.Models.Product;
import com.example.mysmartlist.Utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


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

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("clients/{client_id}/pin/{product_id}")
    Call<Product> deletePinProduct(@Path("client_id") int client_id,@Path("product_id") int product_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("clients/{client_id}/fav/{product_id}")
    Call<Product> deleteFavouriteProduct(@Path("client_id") int client_id,@Path("product_id") int product_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    //pass json{}
    @POST("products")
    Call<Product> addProductToDatabase(@Body Product  product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("products/search")
    Call<Product> getProductSearchResult(@Body Product  product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("products")
    Call<Product> getProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("new/products")
    Call<Product> getNewProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("popular/products")
    Call<Product> getPopularProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("top/products")
    Call<Product> getTopProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("products/{product_id}")
    Call<Product> getProductByID(@Path("product_id") int product_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("categories/{category_id}")
    Call<Product> getProductByCategory(@Path("category_id") int category_id);


    /***************         Categories           *****************/


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("categories")
    Call<Product> getCategories();

  /*  @GET("categories/{category}")
    Call<Product> getCategoryById(@Path("category_id") int category_id);*/


    /***************         clients           *****************/


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("/clients/{client_id}")
    Call<Product> getClientByID(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/lists")
    Call<Product> getCurrentClientLists(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/lists/old")
    Call<Product> getOldClientLists(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/lists/all")
    Call<Product> getAllClientLists(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/fav/{product_id}")
    Call<Product> AddFavouriteProduct(@Path("client_id") int client_id ,@Path("product_id") int product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/pin/{product_id}")
    Call<Product> AddPinProduct(@Path("client_id") int client_id ,@Path("product_id") int product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    //pass json{}
    @POST("clients")
    Call<Product> createClientAccount(@Body Product  client);


    /***************         List           *****************/

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("lists/{list_id}")
    Call<Product> getListById(@Path("list_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    //pass json{}
    @POST("clients/{client_id}/lists")
    Call<Product> addListToDatabase(@Path("client_id") String client_id ,@Body Product  list);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("lists/{list_id}/products")
    Call<Product> addProductToList(@Path("list_id") String client_id ,@Body Product  list);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("lists/{list_id}/products/{product_id}/increment")
    Call<Product> incrementProductCountInList(@Path("list_id") String client_id ,@Path("product_id") String product_id ,@Body Product  list);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("lists/{list_id}/products/{product_id}/decrement")
    Call<Product> decrementProductCountInList(@Path("list_id") String client_id ,@Path("product_id") String product_id ,@Body Product  list);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("lists/{list_id}/products")
    Call<Product> deleteMultipulProduct(@Path("list_id") int client_id,@Body Product  list);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("lists/{list_id}/products/{product_id}")
    Call<Product> deleteSingleProduct(@Path("list_id") int client_id,@Path("product_id") String product_id );


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("lists/{list_id}/trash")
    Call<Product> MoveListFromCurrentListToOldList(@Path("list_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("lists/{list_id}")
    Call<Product> deleteListPermanently(@Path("list_id") int client_id);

}
