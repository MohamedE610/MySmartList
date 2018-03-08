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

import com.example.mysmartlist.Adapters.ListProductsAdapter;
import com.example.mysmartlist.Adapters.ListsAdapter;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.getCurrentClientListsRequest;
import com.example.mysmartlist.Utils.Networking.getListByIdRequest;


public class ListProductsFragment extends Fragment implements Callbacks, ListProductsAdapter.RecyclerViewClickListener {

    public ListProductsFragment() {
        // Required empty public constructor
    }

    List list;
    ListProductsAdapter listProductsAdapter;
    getListByIdRequest  listByIdRequest;
    RecyclerView recyclerView;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_list_products, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_cat);

        int id=getArguments().getInt("list_id");
        if(NetworkState.ConnectionAvailable(getActivity())) {
            listByIdRequest = new getListByIdRequest(id);
            listByIdRequest.setCallbacks(this);
            listByIdRequest.start();
        }

        return view;
    }

    @Override
    public void OnSuccess(Object obj) {
        list=(List) obj;
        listProductsAdapter=new ListProductsAdapter(list,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        listProductsAdapter.setClickListener(this);
        recyclerView.setAdapter(listProductsAdapter);
        listProductsAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Object obj) {

    }

    @Override
    public void ItemClicked(View v, int position) {

    }
}
