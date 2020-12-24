package com.application.apm.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.application.apm.Controller.ListAdapter;
import com.application.apm.Model.ModelAble;
import com.application.apm.Model.Payment;
import com.application.apm.Model.UserDataBase;
import com.application.apm.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private List<Payment> payments;
    private UserDataBase dataBase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment,container,false);

        payments = new ArrayList<>();
        //TODO поменять на синглтон
        dataBase = Room.databaseBuilder(getActivity(), UserDataBase.class, "DataBase.db")
                .allowMainThreadQueries()
                .build();
        //payments = dataBase.getModelDao().getPaymentById();
        List<ModelAble> modelAbles = new ArrayList<>();
        modelAbles.addAll(payments);
        recyclerView = v.findViewById(R.id.list_payment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter(modelAbles,ListAdapter.PaymentList.PAYMENT_LIST_HOLDER_TYPE,getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }
}
