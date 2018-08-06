package com.example.bartek.systemgromadzacyzdjecia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExpertHomeActivity extends AppCompatActivity implements View.OnClickListener
{
    private List<String> list;
    private FirebaseAuth firebaseAuth;
    private Button logoutButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_home);
        firebaseAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        PrepareWelcomeMessage();
        PrepareTexts();
        initRecyclerView();
    }

    private void PrepareWelcomeMessage()
    {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserState US = dataSnapshot.child("Information").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(UserState.class);
                TextView helloTest = findViewById(R.id.HelloMessage);
                helloTest.setText("Welcome " + US.email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void initRecyclerView()
    {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ExpertHomeAdapter adapter = new ExpertHomeAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void PrepareTexts()
    {
        list = new ArrayList<>();
        for (int i=0; i<20;i++)
        {
            list.add("texxxxxt" + i);
        }
    }

    @Override
    public void onClick(View v) {
        {
            if (v == logoutButton)
            {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
            }
        }
    }
}
