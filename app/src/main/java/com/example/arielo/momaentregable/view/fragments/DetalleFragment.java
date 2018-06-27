package com.example.arielo.momaentregable.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arielo.momaentregable.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {
    View view;
    ImageView imageViewPintura;
    TextView textViewNombrePintura;
    TextView textViewNombreArtista;
    TextView textViewInfluencias;

    public DetalleFragment() {
        // Required empty public constructor
        imageViewPintura = view.findViewById(R.id.imageViewPintura);
        textViewNombrePintura = view.findViewById(R.id.textViewNombreDeLaPintura);
        textViewNombreArtista= view.findViewById(R.id.textViewNombreDelArtista);
        textViewInfluencias= view.findViewById(R.id.textViewInfluencias);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalle, container, false);

        return view;
    }

}
