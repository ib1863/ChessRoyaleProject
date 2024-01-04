package com.example.chessroyale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonReg, buttonSignIn;
    FirebaseAuth mAuth;

    ProgressBar progressBar;

    CheckBox stayConnected;

    SharedPreferences settings;

    public static FirebaseAuth refAuth =FirebaseAuth.getInstance();


    @Override
    public void onStart() {
        super.onStart();
        settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        Boolean isChecked = settings.getBoolean("stayConnect", false);
        if(isChecked && refAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(getApplicationContext(), GameScreen.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPassword = findViewById(R.id.textPass);
        editTextEmail = findViewById(R.id.textUserName);
        mAuth = FirebaseAuth.getInstance();
        buttonReg = findViewById(R.id.register);
        buttonSignIn = findViewById(R.id.signIn);
        progressBar = findViewById(R.id.progressBar);
        stayConnected = findViewById(R.id.checkBox);






        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()) {
                                    if(stayConnected.isChecked()){
                                        SharedPreferences.Editor editor = settings.edit();
                                        editor.putBoolean("stayConnect", stayConnected.isChecked());
                                        editor.commit();
                                    }
                                    Toast.makeText(MainActivity.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    editTextEmail.setText("");
                                    editTextPassword.setText("");
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    if(stayConnected.isChecked()){
                                        SharedPreferences.Editor editor = settings.edit();
                                        editor.putBoolean("stayConnect", stayConnected.isChecked());
                                        editor.commit();
                                    }
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    editTextEmail.setText("");
                                    editTextPassword.setText("");
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


    }

}