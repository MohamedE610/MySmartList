package com.example.mysmartlist.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Activities.ListProductsActivity;
import com.example.mysmartlist.Adapters.ListsAdapter;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.getCurrentClientListsRequest;


public class ListsFragment extends Fragment implements Callbacks, ListsAdapter.RecyclerViewClickListener {

   //ArrayList<Category_1> categories=new ArrayList<>();
    ClientLists  clientLists;
    ListsAdapter listsAdapter;
    getCurrentClientListsRequest currentClientListsRequest;
    RecyclerView recyclerView;
    public ListsFragment() {
        // Required empty public constructor
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_cat);

        if(NetworkState.ConnectionAvailable(getActivity())) {
            MySharedPreferences.setUpMySharedPreferences(getActivity());
            int id=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
            currentClientListsRequest = new getCurrentClientListsRequest(id);
            currentClientListsRequest.setCallbacks(this);
            currentClientListsRequest.start();
        }

        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        //String json=(String)obj;
        //categories= JsonParsingUtils.getAllCategories(json);
        clientLists=(ClientLists) obj;
        listsAdapter=new ListsAdapter(clientLists,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        listsAdapter.setClickListener(this);
        recyclerView.setAdapter(listsAdapter);
        listsAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Object error) {

    }

    @Override
    public void ItemClicked(View v, int position) {
        Intent intent=new Intent(getActivity(),ListProductsActivity.class);
        intent.putExtra("list_id",clientLists.data.get(position).id);
        getActivity().startActivity(intent);
    }
}
