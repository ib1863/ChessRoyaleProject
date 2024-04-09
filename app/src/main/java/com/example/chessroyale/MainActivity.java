package com.example.chessroyale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ProgressBar progressBar;
    CheckBox stayConnected;
    SharedPreferences settings;
    public static FirebaseAuth refAuth =FirebaseAuth.getInstance();


    @Override
    public void onStart(){
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
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // Attach the adapter to the ViewPager2
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), getLifecycle()));

        // Link the TabLayout to the ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Customize tab labels if needed
        }).attach();

        // Access buttons in fragments
        LoginTabFragment loginFragment = new LoginTabFragment();
        SignupTabFragment signupFragment = new SignupTabFragment();

        EditText emailEditText = loginFragment.getView().findViewById(R.id.login_email);
        EditText passEditText = loginFragment.getView().findViewById(R.id.login_password);
        stayConnected = loginFragment.getView().findViewById(R.id.checkBox);
        progressBar = loginFragment.getView().findViewById(R.id.progressBar);
        // Find buttons in LoginTabFragment
        Button loginButton = loginFragment.getView().findViewById(R.id.login_button);
        // Set onClickListener or perform other operations with loginButton
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(emailEditText.getText());
                password = String.valueOf(passEditText.getText());

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
                                    emailEditText.setText("");
                                    passEditText.setText("");
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });




        // Find buttons in SignupTabFragment
        Button signupButton = signupFragment.getView().findViewById(R.id.signup_button);
        // Set onClickListener or perform other operations with signupButton
    }

    private static class TabAdapter extends FragmentStateAdapter {

        public TabAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new LoginTabFragment();
                case 1:
                    return new SignupTabFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 2; // Number of tabs
        }
    }
}
