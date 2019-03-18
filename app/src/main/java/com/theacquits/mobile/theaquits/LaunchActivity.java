package com.theacquits.mobile.theaquits;

import android.app.Fragment;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.theacquits.mobile.theaquits.fragments.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaunchActivity extends AppCompatActivity {

    Thread splashTread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initViews();


    }

    private void initFragment(){
        FragmentManager fragment = getSupportFragmentManager();
        LoginFragment loginFragment = new LoginFragment();
        fragment.beginTransaction().replace(R.id.main_frame, loginFragment).commit();

    }

    private void initViews(){
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    //Splash screen pause time
                    while (waited < 5000) {
                        sleep(100);
                        waited += 100;
                    }
                   initFragment();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {

                }

            }
        };
        splashTread.start();

    }
}
