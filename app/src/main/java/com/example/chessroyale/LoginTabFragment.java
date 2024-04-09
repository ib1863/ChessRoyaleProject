package com.example.chessroyale;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;



public class LoginTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, containter, savedInstanceState);

        return inflater.inflate(R.layout.fragment_login_tab, containter, false);
    }




}
