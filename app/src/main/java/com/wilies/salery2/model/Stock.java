package com.wilies.salery2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stocks", primaryKeys = {"brandName"})
public class Stock {
    @PrimaryKey(autoGenerate = true)
    private int stockItemId;

    @ColumnInfo(name = "stock_at_hand")
    private int stockAtHand;

    @ColumnInfo(name = "initial_stock_count")
    private String initialStockCount;

    @ColumnInfo(name = "cost_per_unit")
    private float costPerUnit;

    @ColumnInfo(name = "brand_name")
    private String brandName;


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

    public String getInitialStockCount() {
        return initialStockCount;
    }

    public void setInitialStockCount(String initialStockCount) {
        this.initialStockCount = initialStockCount;
    }

    public void saleItems(int items){
        this.stockAtHand = this.stockAtHand - items;
    }

    public void addStockItems(int items){
        this.stockAtHand = this.stockAtHand + items;
    }
}
