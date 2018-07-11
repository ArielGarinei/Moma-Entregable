package com.example.arielo.momaentregable.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.view.RecyclerView.FragmentRecyclerView;
import com.example.arielo.momaentregable.view.fragments.DetalleFragmentViewPager;
import com.example.arielo.momaentregable.view.fragments.FragmentChat;
import com.example.arielo.momaentregable.view.fragments.LogeoFragment;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import static com.example.arielo.momaentregable.view.fragments.DetalleFragmentViewPager.POSICION;

public class MainActivity extends AppCompatActivity implements FragmentRecyclerView.NotificadorDeActivityRVA{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser usuarioActual;
    private ImageView imageViewFotoUsuario;
    private TextView textViewNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        usuarioActual = firebaseAuth.getCurrentUser();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity, new LogeoFragment()).commit();
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new ListenerMenu());

        View viewHeader = navigationView.getHeaderView(0);
        textViewNombreUsuario = viewHeader.findViewById(R.id.textViewNombreUsuario_NavigationViewHeader);
        imageViewFotoUsuario = viewHeader.findViewById(R.id.imageViewFotoUsuario_NavigationViewHeader);
        if (usuarioActual != null) {
            textViewNombreUsuario.setText(usuarioActual.getDisplayName());
            Glide.with(this).load(usuarioActual.getPhotoUrl() + "/picture?type=large").into(imageViewFotoUsuario);
        }

    }

    @Override
    public void notificadorRVA(int posicon) {
        DetalleFragmentViewPager detalleFragmentViewPager = new DetalleFragmentViewPager();
        Bundle bundle = new Bundle();
        bundle.putInt(POSICION,posicon);
        detalleFragmentViewPager.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity,detalleFragmentViewPager).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity,new DetalleFragment()).commit();

    }

    private class ListenerMenu implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.itemInicio:
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity,new FragmentRecyclerView()).commit();
                    break;
                case R.id.itemCerrarSesion:
                    FirebaseAuth.getInstance().signOut();
                    LoginManager.getInstance().logOut();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity, new LogeoFragment()).commit();

                    break;
                case R.id.itemConfiguracion:

                    //Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    //startActivity(intent);
                   getSupportFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity, new FragmentChat()).commit();
                   Toast.makeText(MainActivity.this, "EDU CAT", Toast.LENGTH_LONG).show();
            }
            drawerLayout.closeDrawers();
            return true;
        }
    }
}

