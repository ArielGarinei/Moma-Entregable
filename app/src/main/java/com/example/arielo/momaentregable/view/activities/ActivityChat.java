package com.example.arielo.momaentregable.view.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.controller.UserController;
import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.model.pojo.MessageSend;
import com.example.arielo.momaentregable.model.pojo.MessageReceive;
import com.example.arielo.momaentregable.model.pojo.User;
import com.example.arielo.momaentregable.view.adapter.RecyclerViewAdapterMensajes;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ActivityChat extends AppCompatActivity {

    private ImageView imageViewUserPhoto;
    private TextView textViewNameUser;
    private RecyclerView recyclerViewMessagesChat;
    private EditText editTextMessage;
    private FloatingActionButton floatingActionButtonSend;
    private ImageButton imageButtonSendImage;

    private RecyclerViewAdapterMensajes recyclerViewAdapterMensajes;
    private List<User> userList;


    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private FirebaseStorage storage;


    private static final int SEND_PHOTO = 1;
    private static final int CHANGE_USER_PHOTO = 2;
    private String PHOTO_USER;
    private String NAME_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        imageViewUserPhoto = findViewById(R.id.imageViewUserPhoto);
        textViewNameUser = findViewById(R.id.textViewNameUser);
        recyclerViewMessagesChat = findViewById(R.id.recyclerViewMessagesChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        floatingActionButtonSend = findViewById(R.id.floatingActionButtonSend);
        imageButtonSendImage = findViewById(R.id.imageButtonSendImage);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        NAME_USER = firebaseUser.getDisplayName();
        textViewNameUser.setText(NAME_USER);
        Glide.with(ActivityChat.this).load(firebaseUser.getPhotoUrl() + "/picture?type=large").into(imageViewUserPhoto);



        database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("chat/"+firebaseUser.getUid()); rompre en la linea 159 (addChildEventListener)
        databaseReference = database.getReference("chat");
        storage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();



        recyclerViewAdapterMensajes = new RecyclerViewAdapterMensajes(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewMessagesChat.setLayoutManager(linearLayoutManager);
        recyclerViewMessagesChat.setAdapter(recyclerViewAdapterMensajes);

        floatingActionButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new MessageSend(editTextMessage.getText().toString(), NAME_USER, PHOTO_USER,"1", firebaseUser.getUid(), ServerValue.TIMESTAMP));
                editTextMessage.setText("");
            }
        });

        imageButtonSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Selecciona una foto"), SEND_PHOTO);
            }
        });

        imageViewUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Selecciona una foto"), CHANGE_USER_PHOTO);
            }
        });

        recyclerViewAdapterMensajes.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
                                                    @Override
                                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                        MessageReceive mensajeRecibir = dataSnapshot.getValue(MessageReceive.class);
                                                        recyclerViewAdapterMensajes.addMensaje(mensajeRecibir);
                                                    }

                                                    @Override
                                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                                    }

                                                    @Override
                                                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                                                    }

                                                    @Override
                                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });

                verifyStoragePermissions(this);

    }

    private void setScrollbar(){
        recyclerViewMessagesChat.scrollToPosition(recyclerViewAdapterMensajes.getItemCount()-1);
    }

    public static boolean verifyStoragePermissions(Activity activity) {
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int REQUEST_EXTERNAL_STORAGE = 1;
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StorageReference storageReference;
        if(requestCode == SEND_PHOTO && resultCode == RESULT_OK){
            Uri uri = data.getData();
            storageReference = storage.getReference("imagenes_chat");
            final StorageReference fotoReferencia = storageReference.child(uri.getLastPathSegment());
            fotoReferencia.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri = taskSnapshot.getDownloadUrl();
                    MessageSend m = new MessageSend(NAME_USER +" te ha enviado una foto",uri.toString(), NAME_USER, PHOTO_USER,"2", firebaseUser.getUid(),ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        }else if(requestCode == CHANGE_USER_PHOTO && resultCode == RESULT_OK){
            Uri uri = data.getData();
            storageReference = storage.getReference("foto_perfil");
            final StorageReference fotoReferencia = storageReference.child(uri.getLastPathSegment());
            fotoReferencia.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri uri = taskSnapshot.getDownloadUrl();
                    PHOTO_USER = uri.toString();
                    MessageSend messageSend = new MessageSend(NAME_USER +" ha actualizado su foto de perfil",uri.toString(), NAME_USER, PHOTO_USER,"2", firebaseUser.getUid(),ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(messageSend);
                    Glide.with(ActivityChat.this).load(uri.toString()).into(imageViewUserPhoto);
                    uploadFotoUsuario(imageViewUserPhoto);
                }
            });
        }
    }

   @Override
    protected void onResume() {
        super.onResume();
       if(firebaseUser!=null){
           floatingActionButtonSend.setEnabled(false);
           DatabaseReference reference = database.getReference("Usuarios/"+firebaseUser.getUid());
           reference.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   NAME_USER = firebaseUser.getDisplayName();
                   textViewNameUser.setText(NAME_USER);
                   Glide.with(ActivityChat.this).load(firebaseUser.getPhotoUrl() + "/picture?type=large").into(imageViewUserPhoto);


              /*     User user = dataSnapshot.getValue(User.class);
                   if (user.getName() == null){
                       NAME_USER = firebaseUser.getDisplayName();
                       textViewNameUser.setText(NAME_USER);
                       Glide.with(ActivityChat.this).load(firebaseUser.getPhotoUrl() + "/picture?type=large").into(imageViewUserPhoto);

                   }else {
                       NAME_USER = user.getName();
                       textViewNameUser.setText(NAME_USER);
                       downloadImagenFirebaseUIFotoUsuario(imageViewUserPhoto);
                   }
                   textViewNameUser.setText(NAME_USER);*/
                   floatingActionButtonSend.setEnabled(true);

               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });
    }else {
           returnLogin();
       }


   }
    private void returnLogin(){
        startActivity(new Intent(ActivityChat.this, ActivityMainLogIn.class));
        finish();
    }

    public void uploadFotoUsuario(ImageView imageView) {
        StorageReference storageReference = storage.getReference();
        storageReference.child("fotosPerfilUsuarios").child(firebaseUser.getUid());
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageReference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(ActivityChat.this, "Upload Failure", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                Toast.makeText(ActivityChat.this, "Upload Succes", Toast.LENGTH_SHORT).show();

                // ...
            }
        });
    }
    public void downloadImagenFirebaseUIFotoUsuario(ImageView imageView){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("fotosPerfilUsuarios").child(firebaseUser.getUid());
        Glide.with(ActivityChat.this).using(new FirebaseImageLoader()).load(storageReference).into(imageView);

    }
}
