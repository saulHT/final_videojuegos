package com.example.afinal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.afinal.dao.MovimeintoDao;
import com.example.afinal.entidades.Movimiento;


public abstract class AppDatabase extends RoomDatabase {

    public abstract MovimeintoDao movimeintoDao();

    public static AppDatabase getInstance(Context context){

        return Room.databaseBuilder(context,AppDatabase.class,"vj2022_db")
              .allowMainThreadQueries()
                .build();

    }
}
