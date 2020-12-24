package com.application.apm.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ModelDao {

        @Query("SELECT * FROM Users")
        List<User> getUsers();

        @Query("SELECT * FROM Users WHERE ID LIKE:id")
        User getUserById(int id);

        @Query("SELECT * FROM Users WHERE Name LIKE:name")
        User getUserByName(String name);

        @Query("SELECT * FROM Users WHERE SecondName LIKE:secondName")
        User getUserBySecondName(String secondName);

        @Query("SELECT * FROM Users WHERE Age LIKE:age")
        User getUserBySecondName(int age);

        @Query("SELECT * FROM Payments WHERE Sum LIKE:sum")
        Payment getPaymentBySum(int sum);

        @Query("SELECT * FROM Payments WHERE ID LIKE:id")
        Payment getPaymentById(int id);


        @Insert
        Long insertUser(User user);

        @Delete
        void deleteUser(User user);

        @Update
        void updateUser(User user);

        @Insert
        Long insertPayment(Payment payment);

        @Delete
        void deletePayment(Payment payment);

        @Update
        void updatePayment(Payment payment);
}

