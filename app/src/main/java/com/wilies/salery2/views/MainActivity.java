package com.wilies.salery2.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wilies.salery2.R;
import com.wilies.salery2.model.InventoryAdapter;
import com.wilies.salery2.model.SaleryDatabase;
import com.wilies.salery2.model.Stock;
import com.wilies.salery2.viewmodel.SaleryViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private FloatingActionButton mFab;
    private SaleryDatabase mDB;
    private InventoryAdapter mAdapter;
    private LiveData<List<Stock>> mStocks;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private LiveData<Integer> mStockCount;
    private TextView totalProductsTV;
    private TextView totalInventory;
    private SaleryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.main_recycler);

        mRecyclerView.setLayoutManager(mLayoutManager);
        totalProductsTV = findViewById(R.id.total_products_value);
        totalInventory = findViewById(R.id.stock_at_hand_value);

        //Loads all the variables in the scope of onCreate
        prepareOnCreate();

        //Displays the list of inventory to the UI
        showInventoryList();

        mFab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddNewProduct.class);
            startActivity(intent);
        });
    }

    private void prepareOnCreate() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mDB = SaleryDatabase.getInstance(this);
        mAdapter = new InventoryAdapter(this);
    }


    private void showInventoryList() {
        mViewModel = new SaleryViewModel(getApplication());


        mViewModel.getStocks().observe(this, new Observer<List<Stock>>() {
            @Override
            public void onChanged(List<Stock> stocks) {
                mAdapter.setStockList(stocks);
                mRecyclerView.setAdapter(mAdapter);
                totalProductsTV.setText(String.valueOf(mAdapter.getItemCount()));
                totalInventory.setText(String.valueOf(mAdapter.getTotalInventory()));
            }
        });




    }


}