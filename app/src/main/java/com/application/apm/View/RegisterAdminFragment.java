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

public class RegisterAdminFragment extends Fragment {

    public static final String KEY_TO_EMAIL_IN_PREFS="key_to_email";
    public static final String KEY_TO_PASS_IN_PREFS="key_to_pass";

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
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_register_admin,container,false);
        init(v);
        return v;
    }

    private void init(View v) {
        mRegButton=v.findViewById(R.id.reg_reg_button);
        mEmailInput=v.findViewById(R.id.email_reg_editText);
        mPasswordInput=v.findViewById(R.id.password_reg_editText);
        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        if (!mEmailInput.getText().toString().isEmpty() && !mPasswordInput.getText().toString().isEmpty()){
            SharedPreferences sp=getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor mEditor = sp.edit();
            mEditor.putString(KEY_TO_EMAIL_IN_PREFS, mEmailInput.getText().toString());
            mEditor.putString(KEY_TO_PASS_IN_PREFS, mPasswordInput.getText().toString());
            mEditor.commit();
            Toast.makeText(getContext(),getString(R.string.reg_completed),Toast.LENGTH_SHORT).show();
            mActivityCallback.onRegistered(mEmailInput.getText().toString(),mPasswordInput.getText().toString());
            return;
        }
        Toast.makeText(getContext(),getString(R.string.empty_reg),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivityCallback=null;
    }

    public interface CallBack{
        void onRegistered(String email,String pass);
    }
}
