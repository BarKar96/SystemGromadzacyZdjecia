package com.example.bartek.systemgromadzacyzdjecia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private CheckBox expertCheckbox;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = firebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), PhotoActivity.class));
        }

        expertCheckbox = findViewById(R.id.expertCheckboxRegister);
        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void registerUser(final boolean expert)
    {
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }




        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            UpdateUserStart(expert, email, FirebaseAuth.getInstance().getCurrentUser().getUid());
                            finish();
                            if (expert)
                            {
                                //startActivity(new Intent(getApplicationContext(), PhotoActivity.class));
                            }
                            else
                            {
                                startActivity(new Intent(getApplicationContext(), PhotoActivity.class));
                            }


                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Could not register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void UpdateUserStart(boolean userExpertState, String email, String uid)
    {
        String fixedEmail = email.replaceAll(Pattern.quote("."),"");
        UserState US = new UserState(userExpertState, email);
        DatabaseReference mDatabase = databaseReference.getRoot();
        Map<String, Object> postValues = US.toMap();
        //String key = mDatabase.child("posts").push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Information/" + uid, postValues);
        mDatabase.updateChildren(childUpdates);
    }

    @Override
    public void onClick(View v)
    {
        if (v == buttonRegister)
        {
            registerUser(expertCheckbox.isChecked());
        }
        if (v == textViewSignIn)
        {
            startActivity(new Intent(this,LoginActivity.class));
        }
    }


}
