package com.example.arielo.momaentregable.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arielo.momaentregable.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class ActivityMainLogIn extends AppCompatActivity{


    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;
    private EditText textViewEmail, textViewPassword;
    private Button buttonLogin, buttonSignIn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPassword = findViewById(R.id.textViewPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignIn = findViewById(R.id.buttonSignIn);

        firebaseAuth = FirebaseAuth.getInstance();

        loginButtonFacebook = findViewById(R.id.loginButtonFacebook);
        callbackManager = CallbackManager.Factory.create();
        FacebookSignIn();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = textViewEmail.getText().toString();
                if(isValidEmail(correo) && validarContraseña()){
                    String contraseña = textViewPassword.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(ActivityMainLogIn.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(ActivityMainLogIn.this, "Wellcome To...\n"+"  MOMA", Toast.LENGTH_SHORT).show();
                                        iniciarActividadRecycler();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(ActivityMainLogIn.this, "Error, Email or Passwor incorrect.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }else{
                    Toast.makeText(ActivityMainLogIn.this, "User or Password Invalids.\n"+"\n" + "The password must be greater than six (6) Digits" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityMainLogIn.this,ActivitySignIn.class));
            }
        });

    }


    public void FacebookSignIn() {
        loginButtonFacebook.setReadPermissions("email");

        // Callback registration
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                handleFacebookAccessToken(loginResult.getAccessToken());
                iniciarActividadRecycler();
            }
            @Override
            public void onCancel() {
                // App code
                Toast.makeText(ActivityMainLogIn.this, "Log In Cancel", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(ActivityMainLogIn.this, "Log In Error", Toast.LENGTH_SHORT).show();
            }
        });
        Token();
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
            iniciarActividadRecycler();
        }
    }


    private void handleFacebookAccessToken(AccessToken token) {
        final FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(ActivityMainLogIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                        } else {

                        }
                    }
                });
    }


    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validarContraseña(){
        String contraseña;
        contraseña = textViewPassword.getText().toString();
        if(contraseña.length()>=6 && contraseña.length()<=16){
            return true;
        }else return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(this, "User logeado.", Toast.LENGTH_SHORT).show();
            iniciarActividadRecycler();
        }
    }

    private void iniciarActividadRecycler(){
        startActivity(new Intent(ActivityMainLogIn.this,ActivityRecycler.class));
    }
}

