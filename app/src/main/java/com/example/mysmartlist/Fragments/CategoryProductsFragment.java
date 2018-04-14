package com.example.mysmartlist.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mysmartlist.Adapters.CategoryProductAdapter;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.FetchProductByCategoryData;


public class CategoryProductsFragment extends Fragment implements Callbacks, CategoryProductAdapter.RecyclerViewClickListener {


    Products products;
    CategoryProductAdapter categoryProductAdapter;
    RecyclerView recyclerView;
    FetchProductByCategoryData fetchProductByCategoryData;
    int category_id;

    public CategoryProductsFragment() {
        // Required empty public constructor
    }



    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_category_products, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_cat);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        Bundle bundle=getArguments();
        if(bundle!=null) {
            category_id = bundle.getInt("cat_id");
        }

        if(NetworkState.ConnectionAvailable(getActivity())){
            fetchProductByCategoryData=new FetchProductByCategoryData(category_id);
            fetchProductByCategoryData.setCallbacks(this);
            fetchProductByCategoryData.start();
        }else
            Toast.makeText(getContext(), " الرجاء تشغيل الانترنت", Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void OnSuccess(Object obj) {

        products=(Products)obj  ;
        categoryProductAdapter=new CategoryProductAdapter(products,getContext());
        categoryProductAdapter.setClickListener(this);
        recyclerView.setAdapter(categoryProductAdapter);
        categoryProductAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Object obj) {

    }

    @Override
    public void ItemClicked(View v, int position) {

    }
}
