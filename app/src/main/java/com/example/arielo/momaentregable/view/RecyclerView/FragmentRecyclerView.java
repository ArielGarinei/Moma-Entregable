package com.example.arielo.momaentregable.view.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.controller.PinturaController;
import com.example.arielo.momaentregable.model.pojo.Paint;
import com.example.arielo.momaentregable.view.adapter.RecyclerViewAdapter;

import java.util.List;

public class FragmentRecyclerView extends Fragment implements RecyclerViewAdapter.EscuchadorDePinturas {
    private List<Paint> paintList;
    private RecyclerView recyclerView;
    private NotificadorDeActivityRVA notificadorDeActivityRVA;


    public FragmentRecyclerView() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notificadorDeActivityRVA = (NotificadorDeActivityRVA) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        cargarPinturas();
        return view;
    }

    public void  cargarPinturas(){
        PinturaController pinturaController = new PinturaController(getContext());
        pinturaController.obtenerPaints(new ResultListener<List<Paint>>() {
        @Override
        public void finish(List<Paint> resultado) {
            paintList = resultado;
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(paintList,FragmentRecyclerView.this);
            recyclerView.setAdapter(recyclerViewAdapter);

        }
    });
    }

    @Override
    public void seleccionaronLaPintura(int posicion) {
        notificadorDeActivityRVA.notificadorRVA(posicion);
    }
    public interface NotificadorDeActivityRVA{
        void notificadorRVA(int posicon);
    }
}



/* public void leerSimple(final Context context){
        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referencePrimerMensaje = mDatabase.child("artists").child("0");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Artist artist = dataSnapshot.getValue(Artist.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        referencePrimerMensaje.addListenerForSingleValueEvent(valueEventListener);
    }*/