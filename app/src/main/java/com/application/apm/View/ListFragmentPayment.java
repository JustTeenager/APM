package com.application.apm.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.apm.Controller.ListAdapter;
import com.application.apm.Model.ModelAble;
import com.application.apm.Model.Payment;
import com.application.apm.Model.RoomDBSingleton;
import com.application.apm.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentPayment extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private List<Payment> payments;
    private int adapterNum;



    private static final String KEY_TO_TYPE_OF_PAYMENT_FILTER="key_to_type_of_payment_filter";
    public static final int USER_PAYMENT_FILTER=-1;
    private static final String USER_ID_KEY="user_id_key";
    public static final int ALL_PAYMENT_FILTER=-3;

    private static final int CHANGE_PAY_VALUE_REQUEST_CODE=5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment,container,false);
        init(v);
        return v;
    }

    private void init(View v) {
        adapterNum=-1;
        payments = new ArrayList<>();
        switch (getArguments().getInt(KEY_TO_TYPE_OF_PAYMENT_FILTER)){
            case USER_PAYMENT_FILTER:{
                payments= RoomDBSingleton.getInstance(getContext()).getPaymentDao().getPaymentById(getArguments().getString(USER_ID_KEY));
            }break;
            case ALL_PAYMENT_FILTER:{
                payments=RoomDBSingleton.getInstance(getContext()).getPaymentDao().getPayments();
            }break;
        }
        //payments = RoomDBSingleton.getInstance(getActivity()).getPaymentDao().getPaymentById();
        List<ModelAble> modelAbles = new ArrayList<>();
        modelAbles.addAll(payments);
        recyclerView = v.findViewById(R.id.list_payment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter(modelAbles,ListAdapter.PaymentList.PAYMENT_LIST_HOLDER_TYPE,getActivity());
        recyclerView.setAdapter(adapter);
    }

    public static ListFragmentPayment getInstance(int code, String id){
        ListFragmentPayment fragment=new ListFragmentPayment();
        Bundle args=new Bundle();
        args.putInt(KEY_TO_TYPE_OF_PAYMENT_FILTER,code);
        args.putString(USER_ID_KEY,id);
        fragment.setArguments(args);
        return fragment;
    }

    public void showChangePaymentValueDialog(int sum,int adapterNumber){
        adapterNum=adapterNumber;
        AddPaymentDialog dialog=AddPaymentDialog.newInstance(sum);
        dialog.setTargetFragment(this,CHANGE_PAY_VALUE_REQUEST_CODE);
        dialog.show(getActivity().getSupportFragmentManager(),null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode== Activity.RESULT_OK){
            if (requestCode==CHANGE_PAY_VALUE_REQUEST_CODE){
                ((Payment) adapter.getAdapterList().get(adapterNum)).setSum(data.getIntExtra(AddPaymentDialog.KEY_TO_PAYMENT_VALUE,-1));
                adapter.notifyItemChanged(adapterNum);
                RoomDBSingleton.getInstance(getContext()).getPaymentDao().updatePayment(payments.get(adapterNum));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public interface Callback{
        void onPayValueChangePressed(int sum,int adapterNumber);
    }
}
