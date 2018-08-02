package com.example.bartek.systemgromadzacyzdjecia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class PhotoActivity extends AppCompatActivity implements View.OnClickListener
{

    private FirebaseAuth firebaseAuth;
    private Button buttonLogout,buttonAddPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        firebaseAuth =  FirebaseAuth.getInstance();



        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();



        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonAddPhoto = (Button) findViewById(R.id.buttonAddPhoto);

        buttonLogout.setOnClickListener(this);
        buttonAddPhoto.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if (v == buttonAddPhoto)
        {
            finish();
            startActivity(new Intent(this,UserActivity.class));
        }
    }
}
