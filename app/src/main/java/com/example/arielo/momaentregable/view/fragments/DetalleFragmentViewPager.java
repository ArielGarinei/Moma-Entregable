package com.example.arielo.momaentregable.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arielo.momaentregable.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragmentViewPager extends Fragment {


    public DetalleFragmentViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_fragment_view_pager, container, false);
        return view;
    }

}
