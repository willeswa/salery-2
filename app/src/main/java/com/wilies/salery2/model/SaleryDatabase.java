package com.wilies.salery2.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Stock.class}, version = 1, exportSchema = false)
public abstract class SaleryDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "saler_db";
    private static SaleryDatabase saleryDatabase;

    public static SaleryDatabase getInstance(Context context){
        if(saleryDatabase == null){
            saleryDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    SaleryDatabase.class, SaleryDatabase.DATABASE_NAME)
            .build();
        }

        return saleryDatabase;
    }

    public abstract StockDao mStockDao();
}
