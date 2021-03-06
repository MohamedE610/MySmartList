package com.example.mysmartlist.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.MySharedPreferences;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    static FragmentActivity context;
    public static void addPinFragment(){
        context.getSupportFragmentManager().beginTransaction().
                replace(R.id.pin_fragment_container,new PinPruductsFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MySharedPreferences.setUpMySharedPreferences(getContext());
        String notNowStr= MySharedPreferences.getUserSetting("notNow");
        context = getActivity();

        if (notNowStr!=null&&notNowStr.equals("0")) {
            addPinFragment();

            ProductsFragment productsFragment= new ProductsFragment();
            productsFragment.setArguments(getArguments());
            getActivity().getSupportFragmentManager().beginTransaction().
                    add(R.id.products_fragment_container,productsFragment).commit();
        }else {
            getActivity().getSupportFragmentManager().beginTransaction().
                    add(R.id.products_fragment_container, new ProductsFragment()).commit();
        }

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
