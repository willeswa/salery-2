package com.wilies.salery2.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.wilies.salery2.R;
import com.wilies.salery2.SaleryExecutor;
import com.wilies.salery2.model.SaleryDatabase;
import com.wilies.salery2.model.Stock;

public class AddNewProduct extends AppCompatActivity {
    private static final String TAG = AddNewProduct.class.getSimpleName();
    private Slider unitSlider;
    private TextInputLayout brandLayout;
    private TextInputLayout priceEditText;
    private Button submit;
    private SaleryDatabase mDB;

    private int units = 0;
    private String brand;
    private float price = 0;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        mDB = SaleryDatabase.getInstance(this);

        unitSlider = (Slider) findViewById(R.id.units);
        brandLayout = (TextInputLayout) findViewById(R.id.brand_name);
        priceEditText = (TextInputLayout) findViewById(R.id.unit_price);
        submit = (Button) findViewById(R.id.button);


        submit.setOnClickListener(view -> {
            brand = brandLayout.getEditText().getText().toString();
            String priceString = priceEditText.getEditText().getText().toString();
            price = Float.parseFloat((priceString.isEmpty() ? "0" : priceString));
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
        });


        unitSlider.addOnChangeListener((slider, value, from )-> {
           units = (int) Float.parseFloat(String.valueOf(value));
        });



    }

    void sayToUser(String message){
        mToast = Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG);
        mToast.show();
    }


}