package com.example.mysmartlist.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.mysmartlist.Activities.ScannerActivity;
import com.example.mysmartlist.Adapters.ProductAdapter;
import com.example.mysmartlist.Models.ProductsByClientID.ProductsByClientID;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.FetchProductSearchData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment implements Callbacks, ProductAdapter.RecyclerViewClickListener {


    Spinner filter;
    Button btnSearch;
    ImageView imgBarcode;
    EditText editTextSearch;

    //ArrayList<Product_1> products =new ArrayList<>();
    ArrayList<ProductsByClientID>  products;
    ProductAdapter productAdapter;
    FetchProductSearchData fetchProductsData;
    RecyclerView recyclerView;
    View view;
    private String searchKey;
    private String orderKey;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_search);
        btnSearch=(Button) view.findViewById(R.id.btn_search);
        editTextSearch=(EditText) view.findViewById(R.id.text_search);
        imgBarcode=(ImageView) view.findViewById(R.id.img_barcode);


        filter=(Spinner)view.findViewById(R.id.spinner);
        createFilterSpinner();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(editTextSearch.getText())) {
                    searchKey = editTextSearch.getText().toString();
                    if(NetworkState.ConnectionAvailable(getActivity())) {
                        MySharedPreferences.setUpMySharedPreferences(getContext());
                        int uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("keyword",searchKey);
                        hashMap.put("order",orderKey);
                        fetchProductsData = new FetchProductSearchData(hashMap,uid);
                        fetchProductsData.setCallbacks(SearchFragment.this);
                        fetchProductsData.start();
                    }
                }
            }
        });

        imgBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ScannerActivity.class));
            }
        });

        return view;
    }

    String[] SpinnerFilterData= {"price","name"};
    private void createFilterSpinner(){
        ArrayAdapter<String> SpinnerAdapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,SpinnerFilterData);
        filter.setAdapter(SpinnerAdapter);
        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderKey=SpinnerFilterData[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void OnSuccess(Object obj) {
        //String json=(String)obj;
        //products= JsonParsingUtils.getAllProducts(json);
        products=( ArrayList<ProductsByClientID>)obj;
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
