package com.application.apm.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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
    private UserDataBase dataBase;
    private UsersCallback usersCallback;
    private ListAdapter listAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        usersCallback = (UsersCallback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_user, container, false);
        users = new ArrayList<>();
        //TODO поменять на синглтон
        dataBase = Room.databaseBuilder(getActivity(), UserDataBase.class, "DataBase.db")
                .allowMainThreadQueries()
                .build();
        users = dataBase.getModelDao().getUsers();
        List<ModelAble> modelAbles = new ArrayList<>();
        modelAbles.addAll(users);
        listUser = v.findViewById(R.id.list_user);
        listUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        listAdapter = new ListAdapter(modelAbles, ListAdapter.UsersList.USER_LIST_HOLDER_TYPE, getActivity());
        listUser.setAdapter(listAdapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
        listUser.setAdapter(listAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        usersCallback = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.user_list_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search_item);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                updateItems(query);
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_user_item:{
                usersCallback.addUser();
            }
            case R.id.menu_cancel_item:{
                updateItems(null);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateItems(String query){
        users.clear();
        if (query!=null) {
            users = dataBase.getModelDao().getUserBySecondName(query);
        }
        else {
            users = dataBase.getModelDao().getUsers();
        }
        List<ModelAble> modelAbles = new ArrayList<>();
        modelAbles.addAll(users);
        Log.d("users", String.valueOf(users.size()));
        listAdapter = new ListAdapter(modelAbles, ListAdapter.UsersList.USER_LIST_HOLDER_TYPE, getActivity());
        listUser.setAdapter(listAdapter);
    }


    public interface UsersCallback{
        void addUser();
    }
}
