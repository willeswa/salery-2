package com.wilies.salery2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wilies.salery2.model.SaleryDatabase;
import com.wilies.salery2.model.Stock;

import java.util.List;

public class SaleryViewModel extends AndroidViewModel {

    private LiveData<List<Stock>> stocks;

    public SaleryViewModel(@NonNull Application application) {
        super(application);
        SaleryDatabase db = SaleryDatabase.getInstance(this.getApplication());
        stocks = db.mStockDao().getStocks();
    }


    public LiveData<List<Stock>> getStocks() {
        return stocks;
    }
}
