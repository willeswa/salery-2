package com.wilies.salery2.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StockDao {
    @Insert
    void insert(Stock stock);

    @Query("SELECT * FROM stocks")
    List<Stock> getStocks();


}
