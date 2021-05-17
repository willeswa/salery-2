package com.wilies.salery2.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface    StockDao {
    @Insert
    void insert(Stock stock);

    @Query("SELECT * FROM stocks")
    LiveData<List<Stock>> getStocks();

    @Query("SELECT COUNT(stock_id) FROM stocks")
    LiveData<Integer> getStocksCount();


}
