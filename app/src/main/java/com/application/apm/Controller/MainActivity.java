package com.application.apm.Controller;

import androidx.fragment.app.Fragment;

import com.application.apm.View.LoginFragment;
import com.application.apm.R;
import com.application.apm.View.RegisterFragment;

public class MainActivity extends SingleFragmentActivity implements LoginFragment.CallBack, RegisterFragment.CallBack {

    @Override
    public int getResId() {
        return R.layout.single_activity_fragment;
    }

    @Override
    public Fragment getFragment() {
        return LoginFragment.newInstance(null, null);
    }

    @Override
    public void login() {
       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,null);
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