package com.example.mysmartlist.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Adapters.ProductAdapter;
import com.example.mysmartlist.Adapters.ProductAdapter1;
import com.example.mysmartlist.Models.List.Product;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.FetchProductsData;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getMarketProductsByClientIDRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getProductsByClientIDRequest;

import java.util.ArrayList;


public class ProductsFragment extends Fragment implements Callbacks, ProductAdapter.RecyclerViewClickListener {

    //ArrayList<Product_1> products =new ArrayList<>();
    ArrayList<ProductsByClientID> products;
    ProductAdapter productAdapter;
    //FetchProductsData fetchProductsData;
    getProductsByClientIDRequest productsByClientIDRequest;
    FetchProductsData fetchProductsData;
    RecyclerView recyclerView;

    public ProductsFragment() {
        // Required empty public constructor
    }

    String marketStr;

    View view;
    String notNowStr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_products);


        MySharedPreferences.setUpMySharedPreferences(getContext());
        notNowStr = MySharedPreferences.getUserSetting("notNow");

        if (notNowStr != null && notNowStr.equals("0")) {
            marketStr=getArguments().getString("market");
            if (NetworkState.ConnectionAvailable(getActivity())) {
                if(marketStr.equals("1")){
                    getAllProducts();
                }else if(marketStr.equals("2")){
                    getAlDanobProducts();
                }else if(marketStr.equals("3")){
                    getBandaProducts();
                }
            }

        } else {
            if (NetworkState.ConnectionAvailable(getActivity())) {
                fetchProductsData = new FetchProductsData();
                fetchProductsData.setCallbacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                       Products products = (Products) obj;
                       ProductAdapter1 productAdapter1 = new ProductAdapter1(products, getActivity());
                       recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

                       productAdapter1.setClickListener(new ProductAdapter1.RecyclerViewClickListener() {
                            @Override
                            public void ItemClicked(View v, int position) {

                            }
                        });

                        recyclerView.setAdapter(productAdapter1);
                        productAdapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void OnFailure(Object obj) {

                    }
                });

                fetchProductsData.start();
            }
        }


        return view;
    }

    private void getBandaProducts() {
        MySharedPreferences.setUpMySharedPreferences(getActivity());
        int id = Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        getMarketProductsByClientIDRequest marketProductsByClientIDRequest=new getMarketProductsByClientIDRequest(2,id);
        marketProductsByClientIDRequest.setCallbacks(this);
        marketProductsByClientIDRequest.start();
    }

    private void getAlDanobProducts() {
        MySharedPreferences.setUpMySharedPreferences(getActivity());
        int id = Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        getMarketProductsByClientIDRequest marketProductsByClientIDRequest=new getMarketProductsByClientIDRequest(1,id);
        marketProductsByClientIDRequest.setCallbacks(this);
        marketProductsByClientIDRequest.start();
    }

    private void getAllProducts() {
        MySharedPreferences.setUpMySharedPreferences(getActivity());
        int id = Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        productsByClientIDRequest = new getProductsByClientIDRequest(id);
        productsByClientIDRequest.setCallbacks(this);
        productsByClientIDRequest.start();
    }

    @Override
    public void OnSuccess(Object obj) {
        //String json=(String)obj;
        //products= JsonParsingUtils.getAllProducts(json);
        products = (ArrayList<ProductsByClientID>) obj;
        productAdapter = new ProductAdapter(products, getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        productAdapter.setClickListener(this);
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Object error) {

    }

    @Override
    public void ItemClicked(View v, int position) {

    }
}
