package com.application.apm.Controller;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.application.apm.View.LoginFragment;
import com.application.apm.R;
import com.application.apm.View.RegisterFragment;

public class MainActivity extends SingleFragmentActivity implements LoginFragment.CallBack, RegisterFragment.CallBack {

    @Override
    public int getResId() {
        return R.layout.activity_single_fragment;
    }

    @Override
    public Fragment getFragment() {
        return LoginFragment.newInstance(null, null);
    }

    @Override
    public void login() {
       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,null);
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    @Override
    public void register() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).commit();
    }

    @Override
    public void onRegistered(String email,String pass) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,LoginFragment.newInstance(email, pass)).commit();
    }
}