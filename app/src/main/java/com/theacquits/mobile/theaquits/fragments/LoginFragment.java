package com.theacquits.mobile.theaquits.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.theacquits.mobile.theaquits.HomeActivity;
import com.theacquits.mobile.theaquits.LaunchActivity;
import com.theacquits.mobile.theaquits.R;
import com.theacquits.mobile.theaquits.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.sign_up)
    Button sign_up;

    @BindView(R.id.login)
    Button login;

    View view;

    @BindView(R.id.forgot_pass)
    protected TextView textView;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), SignUpActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),HomeActivity.class);
                view.getContext().startActivity(intent);
            }
        });





        return view;
    }

}
