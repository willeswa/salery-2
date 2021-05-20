package com.wilies.salery2.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputLayout;
import com.wilies.salery2.R;
import com.wilies.salery2.SaleryExecutor;
import com.wilies.salery2.model.InventoryAdapter;
import com.wilies.salery2.model.SaleryDatabase;
import com.wilies.salery2.model.Stock;
import com.wilies.salery2.viewmodel.AddTaskViewModel;

public class AddNewProduct extends AppCompatActivity {
    private static final String TAG = AddNewProduct.class.getSimpleName();
    private static final int DEFAULT_STOCK_ID = 0;
    private Slider unitSlider;
    private TextInputLayout brandLayout;
    private TextInputLayout priceEditText;
    private Button submit;
    private SaleryDatabase mDB;

    private int units = 0;
    private String brand;
    private float price = 0;
    private Toast mToast;
    private int mTaskId;
    private InventoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        loadVariables();

        Intent intent = getIntent();

        mTaskId = intent.getIntExtra(InventoryAdapter.STOCK_ID_EXTRA, DEFAULT_STOCK_ID);

        if(mTaskId == DEFAULT_STOCK_ID){

            submit.setOnClickListener(view -> {
                getUserInput();

                createEntryInDatabase(units, price, brand);
            });
        } else {
            final AddTaskViewModel viewModel = new AddTaskViewModel(mDB, mTaskId);
            viewModel.getStock().observe(this, new Observer<Stock>(){

                @Override
                public void onChanged(Stock stock) {
                    viewModel.getStock().removeObserver(this);
                    populateForm(stock);

                    submit.setText(R.string.updated_button_text);

                    submit.setOnClickListener(view -> {
                        getUserInput();
                        stock.setBrandName(brand);
                        stock.setCostPerUnit(price);
                        stock.setStockAtHand(units);
                        SaleryExecutor.getInstance().getDiskIO().execute(new Runnable() {
                            @Override
                            public void run() {

                                mDB.mStockDao().update(stock);
                            }
                        });
                        Toast toas = Toast.makeText(AddNewProduct.this.getApplicationContext(), "Updated", Toast.LENGTH_LONG);
                        toas.show();
                        finish();
                    });
                }


            });

        }




        unitSlider.addOnChangeListener((slider, value, from )-> {
           units = (int) Float.parseFloat(String.valueOf(value));
        });



    }

    private void getUserInput() {

        brand = brandLayout.getEditText().getText().toString();
        String priceString = priceEditText.getEditText().getText().toString();
        price = Float.parseFloat((priceString.isEmpty() ? "0" : priceString));
    }

    private void createEntryInDatabase(int units, float price, String brand) {
        if(units == 0 || brand == null || price == 0.0){
            sayToUser(getString(R.string.invalid_input));
        } else {
            Stock newStock = new Stock(units, price, brand);
            SaleryExecutor.getInstance().getDiskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDB.mStockDao().insert(newStock);
                    // sayToUser(newStock.getBrandName() + getString(R.string.success));
                    finish();
                }
            });

        }
    }

    private void populateForm(Stock stock) {
        unitSlider.setValue(stock.getStockAtHand());
        brandLayout.getEditText().setText(stock.getBrandName());
        priceEditText.getEditText().setText(String.valueOf(stock.getCostPerUnit()));
    }

    private void loadVariables() {
        mDB = SaleryDatabase.getInstance(this);

        unitSlider = (Slider) findViewById(R.id.units);
        brandLayout = (TextInputLayout) findViewById(R.id.brand_name);
        priceEditText = (TextInputLayout) findViewById(R.id.unit_price);
        submit = (Button) findViewById(R.id.button);

    }

    void sayToUser(String message){
        mToast = Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG);
        mToast.show();
    }


}