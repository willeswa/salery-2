package com.wilies.salery2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wilies.salery2.model.SaleryDatabase;
import com.wilies.salery2.model.Stock;

public class AddTaskViewModel extends ViewModel {

    final private LiveData<Stock> stock;

    public AddTaskViewModel(SaleryDatabase db, int taskId){
        this.stock = db.mStockDao().getStockWithID(taskId);
    }


    public LiveData<Stock> getStock() {
        return stock;
    }
}
