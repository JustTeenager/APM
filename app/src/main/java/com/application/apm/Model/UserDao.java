package com.application.apm.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;


@Dao
public interface UserDao {

        @Query("SELECT * FROM Users")
        List<User> getUsers();

        @Query("SELECT * FROM Users WHERE ID LIKE:id")
        User getUserById(String id);

        @Query("SELECT * FROM Users WHERE Name LIKE:name")
        User getUserByName(String name);

        @Query("SELECT * FROM Users WHERE SecondName LIKE:secondName")
        List<User> getUserBySecondName(String secondName);

        @Query("SELECT * FROM Users WHERE Age LIKE:age")
        User getUserBySecondName(int age);


        @Insert
        Long insertUser(User user);

        @Delete
        void deleteUser(User user);

        @Update()
        void updateUser(User user);
}

