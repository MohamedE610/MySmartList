package com.example.mysmartlist.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.Activities.ReportDetailsActivity;
import com.example.mysmartlist.Adapters.CategoryAdapter;
import com.example.mysmartlist.Adapters.ReportsAdapter;
import com.example.mysmartlist.Models.Categories.Categories;
import com.example.mysmartlist.Models.Reports.Reports;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.FetchCategoriesData;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getAllClientReportsRequest;

public class ReportsFragment extends Fragment implements Callbacks, ReportsAdapter.RecyclerViewClickListener {


    Reports reports;
    ReportsAdapter reportsAdapter;
    getAllClientReportsRequest  clientReportsRequest;
    RecyclerView recyclerView;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_cat);

        if(NetworkState.ConnectionAvailable(getActivity())) {
            MySharedPreferences.setUpMySharedPreferences(getActivity());
            int uid=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
            clientReportsRequest = new getAllClientReportsRequest(uid);
            clientReportsRequest.setCallbacks(this);
            clientReportsRequest.start();
        }


        return view;
    }

    @Override
    public void OnSuccess(Object obj) {
        reports=(Reports)obj;
        reportsAdapter=new ReportsAdapter(reports,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        reportsAdapter.setClickListener(this);
        recyclerView.setAdapter(reportsAdapter);
        reportsAdapter.notifyDataSetChanged();

    }

    @Override
    public void OnFailure(Object obj) {

    }

    @Override
    public void ItemClicked(View v, int position) {

        Intent intent=new Intent(getActivity(), ReportDetailsActivity.class);
        intent.putExtra("report",reports.data.get(position));
        startActivity(intent);
    }
}
