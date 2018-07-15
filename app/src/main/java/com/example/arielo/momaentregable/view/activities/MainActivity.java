package com.example.arielo.momaentregable.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
public class MainActivity extends AppCompatActivity{


    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;
    private TextView textViewLogeoTrucho;
    private EditText textViewCorreo, textViewContraseña;
    private Button buttonLogin, buttonRegistro;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCorreo = findViewById(R.id.idCorreoLogin);
        textViewContraseña = findViewById(R.id.idContraseñaLogin);
        buttonLogin = findViewById(R.id.idLoginLogin);
        buttonRegistro = findViewById(R.id.idRegistroLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        loginButtonFacebook = findViewById(R.id.login_buttonFacebook);
        callbackManager = CallbackManager.Factory.create();
        conectarConFacebook();
        textViewLogeoTrucho = findViewById(R.id.logeoTrucho);
        textViewLogeoTrucho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarActividadRecycler();
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = textViewCorreo.getText().toString();
                if(isValidEmail(correo) && validarContraseña()){
                    String contraseña = textViewContraseña.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(MainActivity.this, "Bien venido a...\n"+"  MOMA", Toast.LENGTH_SHORT).show();
                                        iniciarActividadRecycler();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(MainActivity.this, "Error, credenciales incorrectas.", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña NO validos.\n"+"La contraseña debe ser mayor a seis(6) Digitos" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityRegistro.class));
            }
        });

    }


    public void conectarConFacebook() {
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
                Toast.makeText(MainActivity.this, "Logeo Cancelado", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(MainActivity.this, "Logeo Erroneo", Toast.LENGTH_SHORT).show();
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
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
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
        contraseña = textViewContraseña.getText().toString();
        if(contraseña.length()>=6 && contraseña.length()<=16){
            return true;
        }else return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser!=null){
            Toast.makeText(this, "Usuario logeado.", Toast.LENGTH_SHORT).show();
            iniciarActividadRecycler();
        }
    }

    private void iniciarActividadRecycler(){
        startActivity(new Intent(MainActivity.this,ActivityRecycler.class));
    }
}

