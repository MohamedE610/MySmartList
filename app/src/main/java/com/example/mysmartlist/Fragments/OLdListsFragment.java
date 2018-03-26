package com.example.mysmartlist.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mysmartlist.Adapters.ListsAdapter;
import com.example.mysmartlist.Adapters.OLdListsAdapter;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.NetworkState;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.getOldClientListsRequest;


public class OLdListsFragment extends Fragment implements Callbacks, OLdListsAdapter.RecyclerViewClickListener, View.OnClickListener {

   //ArrayList<Category_1> categories=new ArrayList<>();
    ClientLists  clientLists;
    OLdListsAdapter listsAdapter;
    getOldClientListsRequest oldClientListsRequest;
    RecyclerView recyclerView;
    public OLdListsFragment() {
        // Required empty public constructor
    }


    Button btnCancel,btnRemove,btnReuse;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_old_list, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_cat);

        btnCancel=(Button) view.findViewById(R.id.btn_cancel);
        btnRemove=(Button) view.findViewById(R.id.btn_remove);
        btnReuse=(Button) view.findViewById(R.id.btn_reuse);

        btnReuse.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


        if(NetworkState.ConnectionAvailable(getActivity())) {
            MySharedPreferences.setUpMySharedPreferences(getActivity());
            int id=Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
            oldClientListsRequest = new getOldClientListsRequest(id);
            oldClientListsRequest.setCallbacks(this);
            oldClientListsRequest.start();
        }

        return view;
    }


    @Override
    public void OnSuccess(Object obj) {
        //String json=(String)obj;
        //categories= JsonParsingUtils.getAllCategories(json);
        clientLists=(ClientLists) obj;
        listsAdapter=new OLdListsAdapter(clientLists,getActivity());
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
        /*Intent intent=new Intent(getActivity(),ListProductsActivity.class);
        intent.putExtra("list_id",clientLists.data.get(position).id);
        getActivity().startActivity(intent);*/
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btn_cancel:
                cancelMethod();
                break;
            case R.id.btn_remove:
                removeMethod();
                break;
            case R.id.btn_reuse:
                reuseMethod();
                break;
        }
    }

    private void reuseMethod() {

    }

    private void removeMethod() {

    }

    private void cancelMethod() {

    }
}
