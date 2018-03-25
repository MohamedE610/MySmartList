package com.example.mysmartlist.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Adapters.ProductAdapter;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getProductsByClientIDRequest;

import java.util.ArrayList;


public class ProductsFragment extends Fragment implements Callbacks, ProductAdapter.RecyclerViewClickListener {

    //ArrayList<Product_1> products =new ArrayList<>();
    ArrayList<ProductsByClientID> products;
    ProductAdapter productAdapter;
    //FetchProductsData fetchProductsData;
    getProductsByClientIDRequest productsByClientIDRequest;
    RecyclerView recyclerView;

    public ProductsFragment() {
        // Required empty public constructor
    }



    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_products);

        if(NetworkState.ConnectionAvailable(getActivity())) {
            MySharedPreferences.setUpMySharedPreferences(getActivity());
            int id= Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
            productsByClientIDRequest = new getProductsByClientIDRequest(id);
            productsByClientIDRequest.setCallbacks(this);
            productsByClientIDRequest.start();
        }

        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        //String json=(String)obj;
        //products= JsonParsingUtils.getAllProducts(json);
        products=( ArrayList<ProductsByClientID> )obj;
        productAdapter=new ProductAdapter(products,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
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
