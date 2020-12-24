package com.application.apm.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.apm.R;

public class LoginFragment extends Fragment {

    private static final String KEY_TO_EMAIL="email";
    private static final String KEY_TO_PASS="pass";


    private Button mLoginButton;
    private Button mRegButton;
    private EditText mEmailInput;
    private EditText mPasswordInput;

    private CallBack mActivityCallback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivityCallback= (CallBack) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=LayoutInflater.from(getContext()).inflate(R.layout.fragment_login,container,false);
        init(v);
        return v;
    }

    private void init(View v) {
        mLoginButton=v.findViewById(R.id.login_log_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(mEmailInput.getText().toString(),mPasswordInput.getText().toString());
            }
        });
        mRegButton=v.findViewById(R.id.reg_log_button);
        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityCallback.register();
            }
        });

        mEmailInput=v.findViewById(R.id.email_log_editText);
        mPasswordInput=v.findViewById(R.id.password_log_editText);
        String email=getArguments().getString(KEY_TO_EMAIL);
        String pass=getArguments().getString(KEY_TO_PASS);
        if (email!=null) mEmailInput.setText(email);
        if (pass!=null) mPasswordInput.setText(pass);
    }

    private void checkLogin(String login,String pass) {
        SharedPreferences sp =getActivity().getPreferences(Context.MODE_PRIVATE);
        String loginAtRegistration = sp.getString(RegisterFragment.KEY_TO_EMAIL_IN_PREFS," ");
        String passAtRegistration = sp.getString(RegisterFragment.KEY_TO_PASS_IN_PREFS," ");
        if (login.equals(loginAtRegistration) && pass.equals(passAtRegistration)){
            mActivityCallback.login();
        }else
        Toast.makeText(getContext(),getString(R.string.wrong_login),Toast.LENGTH_SHORT).show();
    }


    public static Fragment newInstance(String email,String pass){
        Fragment fragment=new LoginFragment();
        Bundle args=new Bundle();
        args.putString(KEY_TO_EMAIL,email);
        args.putString(KEY_TO_PASS,pass);
        fragment.setArguments(args);
        return fragment;
    }

    public interface CallBack{
        void login();
        void register();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivityCallback=null;
    }
}
