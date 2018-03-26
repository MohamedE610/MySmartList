package com.example.mysmartlist.Utils.Networking.RetrofitUtils;




import com.example.mysmartlist.Models.Categories.Categories;
import com.example.mysmartlist.Models.CategoriesRespone.CategoriesResponse;
import com.example.mysmartlist.Models.Client.Client;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.Models.PopularProducts.PopularProduct;
import com.example.mysmartlist.Models.Product_1;
import com.example.mysmartlist.Models.Products.ProductData;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.Models.Reports.Reports;
import com.example.mysmartlist.Models.TopProducts.TopProduct;
import com.example.mysmartlist.Utils.Constants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


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
    Call<Product_1> getCompanyClients();*/

    /***************         products           *****************/

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("clients/{client_id}/pin/{product_id}")
    Call<HashMap> deletePinProduct(@Path("client_id") int client_id, @Path("product_id") int product_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("clients/{client_id}/fav/{product_id}")
    Call<HashMap> deleteFavouriteProduct(@Path("client_id") int client_id, @Path("product_id") int product_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    //pass json{}
    @POST("products")
    Call<HashMap> addProductToDatabase(@Body HashMap product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("clients/{client_id}/products/search")
    Call<ArrayList<ProductsByClientID>> getProductSearchResult(@Path("client_id") int client_id , @Body HashMap searchDetails);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("products")
    Call<Products> getProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("products/client/{client_id}")
    Call< ArrayList<ProductsByClientID>> getProductsByClientID(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("new/products")
    Call<Products> getNewProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("popular/products")
    Call<ArrayList<PopularProduct>> getPopularProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("top/products")
    Call<ArrayList<TopProduct>> getTopProducts();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("products/{product_id}")
    Call<ProductData> getProductByID(@Path("product_id") int product_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("categories/{category_id}")
    Call<ProductData> getProductByCategory(@Path("category_id") int category_id);


    /***************         Categories           *****************/


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("categories")
    Call<Categories> getCategories();

  /*  @GET("categories/{category}")
    Call<Product_1> getCategoryById(@Path("category_id") int category_id);*/

    /***************         clients           *****************/

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/lists/{list_id}/copy")
    Call<List> reuseList(@Path("client_id") int client_id,@Path("list_id") int list_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/reports/month/close")
    Call<Reports> calculateClientMonthlyReports(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/reports/week/close")
    Call<Reports> calculateClientWeeklyReports(@Path("client_id") int client_id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}")
    Call<Client> getClientByID(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/fb/{firebaseId}")
    Call<Client> getClientByFirebaseID(@Path("firebaseId") String firebaseId);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/lists")
    Call<ClientLists> getCurrentClientLists(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/reports")
    Call<Reports> getAllClientReports(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/reports/thisWeek")
    Call<Reports> getWeeklyClientReports(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/reports/thisMonth")
    Call<Reports> getMonthlyClientReports(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/reports/thisYear")
    Call<Reports> getYearlyClientReports(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/lists/old")
    Call<ClientLists> getOldClientLists(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/lists/all")
    Call<ClientLists> getAllClientLists(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/fav/{product_id}")
    Call<HashMap> AddFavouriteProduct(@Path("client_id") int client_id , @Path("product_id") int product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/pin/{product_id}")
    Call<HashMap> AddPinProduct(@Path("client_id") int client_id , @Path("product_id") int product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    //pass json{}
    @POST("clients")
    Call<Client> createClientAccount(@Body HashMap client);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @PUT("clients/{client_id}")
    Call<HashMap> updateClientAccount(@Path("client_id") int client_id,@Body HashMap client);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("categories/multiple")
    Call<CategoriesResponse> AddMultipleCategories(@Body HashMap categories);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("products/multiple")
    Call<HashMap> AddMultipleProducts(@Body HashMap products);


    /***************         List           *****************/

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("lists/{list_id}")
    Call<List> getListById(@Path("list_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    //pass json{}
    @POST("clients/{client_id}/lists")
    Call<HashMap> addListToDatabase(@Path("client_id") int client_id , @Body HashMap list);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("lists/{list_id}/products")
    Call<HashMap> addProductToList(@Path("list_id") String list_id , @Body HashMap product);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("lists/{list_id}/products/{product_id}/increment")
    Call<HashMap> incrementProductCountInList(@Path("list_id") String list_id , @Path("product_id") String product_id , @Body HashMap jsonObject);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @POST("lists/{list_id}/products/{product_id}/decrement")
    Call<HashMap> decrementProductCountInList(@Path("list_id") String list_id , @Path("product_id") String product_id , @Body HashMap jsonObject);




    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    //@DELETE("lists/{list_id}/products")
    @HTTP(method = "DELETE", path = "lists/{list_id}/products", hasBody = true)
    Call<HashMap> deleteMultipulProduct(@Path("list_id") int list_id, @Body HashMap jsonObject);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("lists/{list_id}/products/{product_id}")
    Call<HashMap> deleteSingleProduct(@Path("list_id") int list_id, @Path("product_id") String product_id );


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("lists/{list_id}/trash")
    Call<HashMap> MoveListFromCurrentListToOldList(@Path("list_id") int list_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @DELETE("lists/{list_id}")
    Call<HashMap> deleteListPermanently(@Path("list_id") int list_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/favs")
    Call<Products> getFavouriteProducts(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/pins")
    Call<Products> getPinProducts(@Path("client_id") int client_id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Authorization: "+ Constants.accessToken
    })
    @GET("clients/{client_id}/keywords")
    Call<HashMap> getKeywordsSearch(@Path("client_id") int client_id);


}
