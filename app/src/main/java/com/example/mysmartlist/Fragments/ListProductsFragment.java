package com.example.mysmartlist.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysmartlist.Adapters.ListProductsAdapter;
import com.example.mysmartlist.Adapters.ListsAdapter;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.Models.List.List;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.MoveListFromCurrentListToOldListRequest;
import com.example.mysmartlist.Utils.Networking.deleteMultipleProductRequest;
import com.example.mysmartlist.Utils.Networking.getCurrentClientListsRequest;
import com.example.mysmartlist.Utils.Networking.getListByIdRequest;

import java.util.ArrayList;
import java.util.HashMap;


public class ListProductsFragment extends Fragment implements Callbacks, ListProductsAdapter.RecyclerViewClickListener ,ListProductsAdapter.ISendCheckedList, TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private int list_id;
    deleteMultipleProductRequest deleteMultipleProduct;
    MoveListFromCurrentListToOldListRequest moveListFromCurrentListToOldList;

    public ListProductsFragment() {
        // Required empty public constructor
    }

    List list;
    ListProductsAdapter listProductsAdapter;
    getListByIdRequest  listByIdRequest;
    RecyclerView recyclerView;
    LinearLayout editLinear;
    boolean isEditable=false;
    TextView addToReportsView;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_list_products, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_cat);
        addToReportsView=(TextView) view.findViewById(R.id.btn_add_reports);

         list_id=getArguments().getInt("list_id");
        if(NetworkState.ConnectionAvailable(getActivity())) {
            listByIdRequest = new getListByIdRequest(list_id);
            listByIdRequest.setCallbacks(this);
            listByIdRequest.start();
        }

        tabLayout = (TabLayout) view.findViewById(R.id.bottom_tablayout);
        editLinear = (LinearLayout) view.findViewById(R.id.linear_edit);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setVisibility(View.GONE);

        editLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabLayout.setVisibility(View.VISIBLE);
                isEditable=true;

                if(isEditable)
                    editLinear.setVisibility(View.INVISIBLE);
                else
                    editLinear.setVisibility(View.VISIBLE);

                DisplayData(object,isEditable,list_id);

            }
        });


        addToReportsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveListFromCurrentListToOldList=new MoveListFromCurrentListToOldListRequest(list_id);
                moveListFromCurrentListToOldList.setCallbacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        Toast.makeText(getActivity(), "العملية تمت بنجاح", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void OnFailure(Object obj) {

                    }
                });
                moveListFromCurrentListToOldList.start();

                addToReportsView.setVisibility(View.GONE);
            }
        });


        return view;
    }

    Object object;
    void DisplayData(Object obj,boolean isEditable, int id){
        object=obj;
        list=(List) obj;
        listProductsAdapter=new ListProductsAdapter(list,getActivity(),isEditable,id);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        listProductsAdapter.setClickListener(this);
        listProductsAdapter.setISendCheckedListLisnter(this);
        recyclerView.setAdapter(listProductsAdapter);
        listProductsAdapter.notifyDataSetChanged();
    }
    @Override
    public void OnSuccess(Object obj) {
        editLinear.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        isEditable=false;
      DisplayData(obj,isEditable,list_id);
    }

    @Override
    public void OnFailure(Object obj) {

    }

    @Override
    public void ItemClicked(View v, int position) {

    }

    ArrayList<Integer> checkedList=new ArrayList<>();
    @Override
    public void send(ArrayList<Integer> list) {
        checkedList=list;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int tabPosition=tab.getPosition();

        switch (tabPosition){
            case 0:cancelMethod();
            break;

            case 1:shareProducts();
                break;

            case 2:removeProducts();
                break;
        }

    }

    private void cancelMethod() {
        editLinear.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        isEditable=false;
        DisplayData(object,isEditable,list_id);
    }

    private void shareProducts() {


        String str=list.data.name;
        for (int i = 0; i <list.data.products.size() ; i++) {
            str+="\n";
            str+=list.data.products.get(i).name;
        }

       Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        //String shareBody = "Here is the share content body";
        String shareBody = str;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

        editLinear.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        isEditable=false;
        DisplayData(object,isEditable,list_id);
    }

    private void removeProducts() {
        HashMap<String,ArrayList> hashMap=new HashMap<>();
        hashMap.put("products",checkedList);
        deleteMultipleProduct=new deleteMultipleProductRequest(list_id,hashMap);
        deleteMultipleProduct.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Toast.makeText(getActivity(), "العملية تمت بنجاح", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnFailure(Object obj) {

            }
        });

        deleteMultipleProduct.start();

        editLinear.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        isEditable=false;

        DisplayData(object,isEditable,list_id);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
