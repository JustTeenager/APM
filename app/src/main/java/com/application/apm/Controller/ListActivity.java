package com.application.apm.Controller;

import androidx.fragment.app.Fragment;

import com.application.apm.R;
import com.application.apm.View.AddUserFragment;
import com.application.apm.View.ListUserFragment;

public class ListActivity extends SingleFragmentActivity implements ListUserFragment.UsersCallback, AddUserFragment.AddUserCallback {

    @Override
    public int getResId() {
        return R.layout.activity_single_fragment;
    }

    @Override
    public Fragment getFragment() {
        return new ListUserFragment();
    }

    @Override
    public void addUser() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddUserFragment()).commit();
    }

    @Override
    public void openListUser() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListUserFragment()).commit();
    }
}