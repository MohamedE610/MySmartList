package com.example.mysmartlist.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Adapters.PinProductAdapter;
import com.example.mysmartlist.Adapters.ProductAdapter;
import com.example.mysmartlist.Models.Product;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.FetchDataFromServer.FetchCategoriesData;
import com.example.mysmartlist.Utils.FetchDataFromServer.FetchProductByCategoryData;
import com.example.mysmartlist.Utils.FetchDataFromServer.FetchProductsData;
import com.example.mysmartlist.Utils.JsonParsingUtils;

import java.util.ArrayList;


public class PinPruductsFragment extends Fragment implements Callbacks, PinProductAdapter.RecyclerViewClickListener {

    ArrayList<Product> products =new ArrayList<>();
    PinProductAdapter productAdapter;
    FetchProductByCategoryData fetchProductsData;
    RecyclerView recyclerView;
    private View view;

    public PinPruductsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_pin_pruducts, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_pin);

        fetchProductsData=new FetchProductByCategoryData("4");
        fetchProductsData.setNetworkResponse(this);
        fetchProductsData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        String json=(String)obj;
        products= JsonParsingUtils.getAllProducts(json);
        productAdapter=new PinProductAdapter(products,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
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
