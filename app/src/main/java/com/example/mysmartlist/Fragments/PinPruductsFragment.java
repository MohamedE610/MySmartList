package com.example.mysmartlist.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Adapters.PinProductAdapter;
import com.example.mysmartlist.Models.Products.Products;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.getPinProductsRequest;


public class PinPruductsFragment extends Fragment implements Callbacks, PinProductAdapter.RecyclerViewClickListener {

    //ArrayList<Product_1> products =new ArrayList<>();
    Products products;
    PinProductAdapter productAdapter;
    //FetchProductByCategoryData fetchProductsData;
    getPinProductsRequest pinProductsRequest;
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

        if(NetworkState.ConnectionAvailable(getActivity())){
            MySharedPreferences.setUpMySharedPreferences(getContext());
            int uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
            pinProductsRequest=new getPinProductsRequest(uid);
            pinProductsRequest.setCallbacks(this);
            pinProductsRequest.start();
        }

        //fetchProductsData=new FetchProductByCategoryData("4");
        //fetchProductsData.setNetworkResponse(this);
        //fetchProductsData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        products=(Products)obj;
        //products= JsonParsingUtils.getAllProducts(json);
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
