package com.application.apm.Controller;

import androidx.fragment.app.Fragment;

import com.application.apm.R;

public class ListActivity extends SingleFragmentActivity {

    @Override
    public int getResId() {
        return R.layout.single_activity_fragment;
    }

    @Override
    public Fragment getFragment() {
        return null;
    }
}