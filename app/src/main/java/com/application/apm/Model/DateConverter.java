package com.application.apm.Model;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateConverter {

    @TypeConverter
    public Long fromDate(Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date toDate(Long time){
        return new Date(time);
    }


}
