package com.example.mysmartlist.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Activities.CategoryProductsActivity;
import com.example.mysmartlist.Adapters.CategoryAdapter;
import com.example.mysmartlist.Models.Categories.Categories;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.FetchCategoriesData;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getMarketCategoriesRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getMarketProductsByClientIDRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getProductsByClientIDRequest;


public class CategoriesFragment extends Fragment implements Callbacks, CategoryAdapter.RecyclerViewClickListener {

   //ArrayList<Category_1> categories=new ArrayList<>();
   Categories categories;
   CategoryAdapter categoryAdapter;
   FetchCategoriesData fetchCategoriesData;
   RecyclerView recyclerView;
    private String marketStr;

    public CategoriesFragment() {
        // Required empty public constructor

    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_cat);

        Bundle bundle=getArguments();
        if(bundle!=null) {
            marketStr = bundle.getString("market");
        }else {
            marketStr="1";
        }

        if(marketStr==null)
            marketStr="1";


        if(NetworkState.ConnectionAvailable(getActivity())) {
            if(marketStr.equals("1")){
                getAllCategories();
            }else if(marketStr.equals("2")){
                getAlDanobCategories();
            }else if(marketStr.equals("3")){
                getBandaCategories();
            }else{
                getAllCategories();
            }
        }

        return view;
    }

    private void getBandaCategories() {
        getMarketCategoriesRequest marketCategoriesRequest=new getMarketCategoriesRequest(2);
        marketCategoriesRequest.setCallbacks(this);
        marketCategoriesRequest.start();

    }

    private void getAlDanobCategories() {
        getMarketCategoriesRequest marketCategoriesRequest=new getMarketCategoriesRequest(1);
        marketCategoriesRequest.setCallbacks(this);
        marketCategoriesRequest.start();
    }

    private void getAllCategories() {
        fetchCategoriesData = new FetchCategoriesData();
        fetchCategoriesData.setCallbacks(this);
        fetchCategoriesData.start();
    }


    @Override
    public void OnSuccess(Object obj) {
        //String json=(String)obj;
        //categories= JsonParsingUtils.getAllCategories(json);
        categories=(Categories)obj;
        categoryAdapter=new CategoryAdapter(categories,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        categoryAdapter.setClickListener(this);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Object error) {

    }

    @Override
    public void ItemClicked(View v, int position) {
        Intent intent=new Intent(getContext(), CategoryProductsActivity.class);
        intent.putExtra("cat_id",categories.data.get(position).id);
        intent.putExtra("cat_name",categories.data.get(position).name);
        getActivity().startActivity(intent);
    }
}
