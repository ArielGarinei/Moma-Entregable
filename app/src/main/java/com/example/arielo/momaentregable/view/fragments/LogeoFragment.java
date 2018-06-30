package com.example.arielo.momaentregable.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.view.RecyclerView.FragmentRecyclerView;
import com.example.arielo.momaentregable.view.activities.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogeoFragment extends Fragment {
    CallbackManager callbackManager;
    LoginButton loginButton;
    View view;
    TextView textViewLogeoTrucho;


    public LogeoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_logeo, container, false);
        loginButton = view.findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        conectarConFacebook();
        textViewLogeoTrucho = view.findViewById(R.id.logeoTrucho);
        textViewLogeoTrucho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarRecicler();
            }
        });
        return view;
    }
    public void conectarConFacebook() {
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                handleFacebookAccessToken(loginResult.getAccessToken());
                cargarRecicler();
            }
            @Override
            public void onCancel() {
                // App code
                Toast.makeText(view.getContext(), "Logeo Cancelado", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(view.getContext(), "Logeo Erroneo", Toast.LENGTH_SHORT).show();
            }
        });
        //Token();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Token() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            cargarRecicler();
        }
    }

    private void cargarRecicler(){
        getFragmentManager().beginTransaction().replace(R.id.contenedorDeFragmentosMainActivity, new FragmentRecyclerView()).commit();
    }
    private void handleFacebookAccessToken(AccessToken token) {
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                        }
                    }
                });
    }

}
