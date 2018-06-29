package com.example.arielo.momaentregable.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.Artist;
import com.example.arielo.momaentregable.view.RecyclerView.FragmentRecyclerView;
import com.example.arielo.momaentregable.view.fragments.DetalleFragmentViewPager;
import com.example.arielo.momaentregable.view.fragments.LogeoFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements FragmentRecyclerView.NotificadorDeActivityRVA{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity, new LogeoFragment()).commit();

    }

    @Override
    public void notificadorRVA(int posicon) {
        DetalleFragmentViewPager detalleFragmentViewPager = new DetalleFragmentViewPager();
        Bundle bundle = new Bundle();
        bundle.putInt("posicon",posicon);
        detalleFragmentViewPager.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity,detalleFragmentViewPager ).commit();

    }
}

