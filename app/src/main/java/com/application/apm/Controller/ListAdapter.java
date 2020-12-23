package com.application.apm.Controller;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.apm.Model.ModelAble;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private ArrayList<ModelAble> mAdapterList;

    public ListAdapter(ArrayList<ModelAble> mAdapterList){
        this.mAdapterList=mAdapterList;
    }


    //TODO Сделать лейауты холдерам,разобраться с ViewType
    @NonNull
    @Override
    public ListAdapter.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mAdapterList.size();
    }

    abstract static class ListHolder extends RecyclerView.ViewHolder {

        protected ModelAble model;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
        }

        protected abstract void bind(ModelAble model);
    }

    static class UsersList extends ListHolder{

        public UsersList(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(ModelAble model) {
            this.model=model;
        }
    }

    static class PaymentList extends ListHolder{

        public PaymentList(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(ModelAble model) {
            this.model=model;
        }
    }
}
