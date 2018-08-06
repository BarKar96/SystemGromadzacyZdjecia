package com.example.bartek.systemgromadzacyzdjecia;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExpertReviewActivity extends AppCompatActivity {

    private TextView reviewActivity;
    private Button back;
    private Button publish;
    private DatabaseReference mDatabaseRef;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_review);
        reviewActivity = findViewById(R.id.review_text);
        back = findViewById(R.id.button_back);
        publish = findViewById(R.id.button_publish);
        imageView = findViewById(R.id.image_view_review);
        imageView.setImageURI(Uri.parse(ExpertBrowseActivity.imgURI));
        reviewActivity.setMovementMethod(new ScrollingMovementMethod());
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("images");

    }

    public void buttonHandler(View target) {
        if (target == back)
        {
            finish();
            startActivity(new Intent(this, ExpertBrowseActivity.class));
        }
        if (target == publish)
        {
            PublishReview();
        }
    }

    private void PublishReview() {
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    if (upload.getName().equals(ExpertBrowseActivity.imgName) && upload.getEmail().equals(ExpertHomeActivity.chosenEmail)) {
                        DatabaseReference singleVal =  postSnapshot.getRef().child("review");
                        String x = reviewActivity.getText().toString();
                        singleVal.setValue(x);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
