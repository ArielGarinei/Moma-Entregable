package com.example.arielo.momaentregable.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.view.activities.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogeoFragment extends Fragment {
    CallbackManager callbackManager;
    LoginButton loginButton;


    public LogeoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logeo, container, false);
        loginButton = view.findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        conectarConFacebook();



        return view;
    }


    public void conectarConFacebook(){
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getContext(), "Logeo Exitoso", Toast.LENGTH_SHORT).show();


            }
            @Override
            public void onCancel() {
                // App code
                Toast.makeText(ActivityLogin.this, "Logeo Cancelado", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(ActivityLogin.this, "Logeo Erroneo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       // devolucuionDeFacebbok();
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void Token(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn){
            Intent intent = new Intent(getContext(),MainActivity.class);
            startActivity(intent);
    }


}
