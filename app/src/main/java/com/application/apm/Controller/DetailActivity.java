package com.application.apm.Controller;

import androidx.fragment.app.Fragment;

import com.application.apm.R;

public class DetailActivity extends SingleFragmentActivity {
    @Override
    public int getResId() {
        return R.layout.activity_single_fragment;
    }

    @Override
    public Fragment getFragment() {
        return null;
    }
}
