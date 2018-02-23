package com.example.mysmartlist.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysmartlist.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.pin_fragment_container,new PinPruductsFragment()).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.products_fragment_container,new ProductsFragment()).commit();

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
