package com.wilies.salery2.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wilies.salery2.R;

import java.util.List;
import java.util.zip.Inflater;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {
    private List<Stock> stockList;
    private LayoutInflater mLayoutInflater;
    private int totalInventory = 0;

    public InventoryAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.main_list_item, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        holder.getTitleTextView().setText(stock.getBrandName());
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
        notifyDataSetChanged();
    }

    public int getTotalInventory() {
        for(Stock stock: stockList){
            totalInventory += stock.getStockAtHand();
        }
        return totalInventory;
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.product_brand);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }
    }
}
