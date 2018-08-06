package com.example.bartek.systemgromadzacyzdjecia;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class PhotoReview extends AppCompatActivity implements View.OnClickListener {

    private Button btnBack;
    private TextView textViewReview;
    private TextView textViewName;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_review);

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewReview = (TextView) findViewById(R.id.textViewReview);
        img = (ImageView) findViewById(R.id.image_view_review);
        btnBack = (Button) findViewById(R.id.button_back);
        btnBack.setOnClickListener(this);
        textViewName.setText(PhotoActivity.upload.getName());
        Picasso.get().load(PhotoActivity.upload.getImageUrl()).into(img);
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack)
        {
            finish();
            startActivity(new Intent(this, PhotoActivity.class));
        }

    }

}
