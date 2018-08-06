package com.example.bartek.systemgromadzacyzdjecia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ExpertHomeActivity extends AppCompatActivity implements View.OnClickListener
{
    private List<String> list;
    private FirebaseAuth firebaseAuth;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_home);
        firebaseAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);
        PrepareTexts();
        initRecyclerView();
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
