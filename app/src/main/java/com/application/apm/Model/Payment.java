package com.application.apm.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Payments")
public class Payment implements ModelAble {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "ID")
    private int id;
    @ColumnInfo(name = "Sum")
    private int sum;
    @ColumnInfo(name = "Date")
    private Date mDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
