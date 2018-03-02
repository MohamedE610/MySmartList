package com.example.mysmartlist.Fragments;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Adapters.FavouriteProductAdapter;
import com.example.mysmartlist.Models.Product_1;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.FetchDataFromServer.FetchProductByCategoryData;
import com.example.mysmartlist.Utils.JsonParsingUtils;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavouriteFragment extends Fragment implements Callbacks, FavouriteProductAdapter.RecyclerViewClickListener {

    ArrayList<Product_1> products =new ArrayList<>();
    FavouriteProductAdapter productAdapter;
    FetchProductByCategoryData fetchProductsData;
    RecyclerView recyclerView;
    private View view;

    public FavouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_favourite);

        fetchProductsData=new FetchProductByCategoryData("5");
        fetchProductsData.setNetworkResponse(this);
        fetchProductsData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        return view;
    }

    @Override
    public void OnSuccess(Object obj) {
        String json=(String)obj;
        products= JsonParsingUtils.getAllProducts(json);
        productAdapter=new FavouriteProductAdapter(products,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
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
