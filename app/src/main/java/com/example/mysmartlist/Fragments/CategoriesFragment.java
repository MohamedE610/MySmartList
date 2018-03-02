package com.example.mysmartlist.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Adapters.CategoryAdapter;
import com.example.mysmartlist.Models.Category_1;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.FetchDataFromServer.FetchCategoriesData;
import com.example.mysmartlist.Utils.JsonParsingUtils;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment implements Callbacks, CategoryAdapter.RecyclerViewClickListener {

   ArrayList<Category_1> categories=new ArrayList<>();
   CategoryAdapter categoryAdapter;
   FetchCategoriesData fetchCategoriesData;
   RecyclerView recyclerView;
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

        fetchCategoriesData=new FetchCategoriesData();
        fetchCategoriesData.setNetworkResponse(this);
        fetchCategoriesData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        String json=(String)obj;
        categories= JsonParsingUtils.getAllCategories(json);
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

    }
}
