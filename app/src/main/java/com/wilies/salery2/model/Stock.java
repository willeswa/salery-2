package com.wilies.salery2.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "stocks")
public class Stock {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stock_id")
    private int stockItemId;

    @ColumnInfo(name = "stock_at_hand")
    private int stockAtHand;

    @ColumnInfo(name = "initial_stock_count")
    private int initialStockCount;

    @ColumnInfo(name = "cost_per_unit")
    private float costPerUnit;

    @NonNull
    @ColumnInfo(name = "brand_name")
    private String brandName;


    public Stock(int initialStockCount, float costPerUnit, String brandName) {
        this.initialStockCount = initialStockCount;
        this.brandName = brandName;
        this.stockAtHand = initialStockCount;
        this.costPerUnit = costPerUnit;
    };


    public void sellItem(){
        stockAtHand = -- stockAtHand;
    }

    public float getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(float costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getInitialStockCount() {
        return initialStockCount;
    }

    public void setInitialStockCount(int initialStockCount) {
        this.initialStockCount = initialStockCount;
    }

    public void saleItems(int items){
        this.stockAtHand = this.stockAtHand - items;
    }

    public void addStockItems(int items){
        this.stockAtHand = this.stockAtHand + items;
    }

    public int getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(int stockItemId) {
        this.stockItemId = stockItemId;
    }

    public int getStockAtHand(){
        return this.stockAtHand;
    }


    public void setStockAtHand(int stockAtHand){
        this.stockAtHand = stockAtHand;
    }
}
