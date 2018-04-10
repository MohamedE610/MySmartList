package com.example.mysmartlist.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysmartlist.Activities.OldListsActivity;
import com.example.mysmartlist.Models.ClientLists.ClientLists;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.Callbacks;
import com.example.mysmartlist.Utils.MySharedPreferences;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.ReuseListRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.addListRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.addProductToListRequest;
import com.example.mysmartlist.Utils.Networking.RestApiRequests.deleteListPermanentlyRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abdallah on 2/21/2018.
 */

public class OLdListsAdapter extends RecyclerView.Adapter<OLdListsAdapter.MyViewHolder> {

    ClientLists  clientLists;
    Context context;
    int LastPosition = -1;
    RecyclerViewClickListener ClickListener;


    public OLdListsAdapter() {
    }

    public OLdListsAdapter(ClientLists  clientLists, Context context) {
        this.clientLists = clientLists;
        this.context = context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.ClickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_old_list_item, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        String detailsStr=clientLists.data.get(position).name;
        holder.textView.setText(detailsStr);

        //String urlStr = Constants.BasicUrlImg+categories.data.get(position).image;
        String urlStr ="asd";
        Picasso.with(context).load(urlStr).into(holder.img);
        
        
        holder.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(holder.imgMenu,position);
            }
        });

        setAnimation(holder.cardView, position);
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {

        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        if (clientLists == null || clientLists.data==null)
            return 0;
        return clientLists.data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        ImageView imgMenu;
        TextView textView;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.img);
            imgMenu = (ImageView) itemView.findViewById(R.id.img_menu);
            textView = (TextView) itemView.findViewById(R.id.text_details);
            cardView = (CardView) itemView.findViewById(R.id.card);

        }

        @Override
        public void onClick(View view) {
            if (ClickListener != null)
                ClickListener.ItemClicked(view, getAdapterPosition());
        }

        public void clearAnimation() {
            cardView.clearAnimation();
        }
    }

    public interface RecyclerViewClickListener {

        public void ItemClicked(View v, int position);
    }

    private void setAnimation(View viewToAnimate, int position) {

        if (position > LastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            LastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }


    public void showPopup(View v, final int postion) {
        final PopupMenu popup = new PopupMenu(context, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cancel:
                        popup.dismiss();
                        return true;
                    case R.id.action_reuse:
                        reuseMethod(postion);
                        return true;
                    case R.id.action_remove:
                        removeMethod(postion);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    private void reuseMethod(int postion) {
        int list_id=0;
        MySharedPreferences.setUpMySharedPreferences(context);
        int uid =Integer.valueOf(MySharedPreferences.getUserSetting("uid"));
        list_id=clientLists.data.get(postion).id;

        ReuseListRequest reuseListRequest=new ReuseListRequest(uid, list_id);
        reuseListRequest.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Toast.makeText(context, "العمليه تمت بنجاح", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(context, "لقد حدث خطاء الرجاء اعاده المحاوله", Toast.LENGTH_SHORT).show();
            }
        });

        reuseListRequest.start();
    }

    private void removeMethod(int postion) {
        int listID=clientLists.data.get(postion).id;
        deleteListPermanentlyRequest deleteListPermanently=new deleteListPermanentlyRequest(listID);
        deleteListPermanently.setCallbacks(new Callbacks() {
            @Override
            public void OnSuccess(Object obj) {
                Toast.makeText(context, "العمليه تمت بنجاح", Toast.LENGTH_SHORT).show();
                OldListsActivity.addOldListsFragment();
            }

            @Override
            public void OnFailure(Object obj) {
                Toast.makeText(context, "لقد حدث خطاء الرجاء اعاده المحاوله", Toast.LENGTH_SHORT).show();
            }
        });

        deleteListPermanently.start();
    }


}

