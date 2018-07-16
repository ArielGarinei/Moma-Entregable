package com.example.arielo.momaentregable.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.view.fragments.DetalleFragmentViewPager;

public class ActivityDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        DetalleFragmentViewPager detalleFragmentViewPager = new DetalleFragmentViewPager();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        detalleFragmentViewPager.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosActivityDetalle,detalleFragmentViewPager).commit();
    }
}
