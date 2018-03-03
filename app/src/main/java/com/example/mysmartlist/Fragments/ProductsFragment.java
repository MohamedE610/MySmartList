package com.example.mysmartlist.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Adapters.ProductAdapter;
import com.example.mysmartlist.Models.Product_1;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.JsonParsingUtils;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.FetchProductsData;

import java.util.ArrayList;


public class ProductsFragment extends Fragment implements Callbacks, ProductAdapter.RecyclerViewClickListener {

    //ArrayList<Product_1> products =new ArrayList<>();
    Products products;
    ProductAdapter productAdapter;
    //FetchProductsData fetchProductsData;
    FetchProductsData fetchProductsData;
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
            fetchProductsData = new FetchProductsData();
            fetchProductsData.setCallbacks(this);
            fetchProductsData.start();
        }

        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        //String json=(String)obj;
        //products= JsonParsingUtils.getAllProducts(json);
        products=(Products)obj;
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
