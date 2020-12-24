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
import androidx.room.RoomDatabase;

import com.application.apm.Controller.ListAdapter;
import com.application.apm.Model.ModelAble;
import com.application.apm.Model.User;
import com.application.apm.Model.UserDataBase;
import com.application.apm.R;

import java.util.ArrayList;
import java.util.List;

public class ListUserFragment extends Fragment{

    private RecyclerView listUser;
    private List<User> users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_user, container, false);
        users = new ArrayList<>();
        users = Room.databaseBuilder(getActivity(), UserDataBase.class, "DataBase.db")
                .allowMainThreadQueries()
                .build().getModelDao().getUsers();
        List<ModelAble> modelAbles = new ArrayList<>();
        modelAbles.addAll(users);
        listUser = v.findViewById(R.id.list_user);
        listUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        listUser.setAdapter(new ListAdapter(modelAbles, ListAdapter.UsersList.USER_LIST_HOLDER_TYPE, getActivity()));
        return v;
    }
}
