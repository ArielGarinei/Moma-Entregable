package com.example.arielo.momaentregable.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.view.RecyclerView.FragmentRecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity, new FragmentRecyclerView()).commit();



    }
}
