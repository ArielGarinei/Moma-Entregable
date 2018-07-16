package com.example.arielo.momaentregable.view.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivitySignUp extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextPassword, editTextPasswordRepeat;
    private Button buttonSignUp;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextName = findViewById(R.id.editTextRegistroName);
        editTextEmail = findViewById(R.id.editTextRegistroEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordRepeat = findViewById(R.id.editTextRegistroPasswordRepeat);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextEmail.getText().toString();
                final String name = editTextName.getText().toString();
                if(isValidEmail(email) && validatePassword() && validateName(name)){
                    String contraseña = editTextPassword.getText().toString();
                    firebaseAuth.createUserWithEmailAndPassword(email, contraseña)
                            .addOnCompleteListener(ActivitySignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(ActivitySignUp.this, "Succesful register.", Toast.LENGTH_SHORT).show();
                                        User user = new User();
                                        user.setEmail(email);
                                        user.setName(name);
                                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                                        DatabaseReference reference = firebaseDatabase.getReference("Usuarios/"+currentUser.getUid());
                                        reference.setValue(user);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(ActivitySignUp.this, "Error, email or Passwor incorrect.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(ActivitySignUp.this, "User or Password Invalids.\n" + "The password must be greater than six (6) Digits", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validatePassword(){
        String password,passwordRepeat;
        password = editTextPassword.getText().toString();
        passwordRepeat = editTextPasswordRepeat.getText().toString();
        if(password.equals(passwordRepeat)){
            if(password.length()>=6 && password.length()<=16){
                return true;
            }else return false;
        }else return false;
    }

    public boolean validateName(String name){
        return !name.isEmpty();
    }

}