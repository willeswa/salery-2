package com.wilies.salery2.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wilies.salery2.ItemTouchAdapter;
import com.wilies.salery2.R;
import com.wilies.salery2.SaleryExecutor;
import com.wilies.salery2.views.ItemClickHelper;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> implements ItemTouchAdapter {
    public static final String STOCK_ID_EXTRA = "stockId";
    private List<Stock> stockList;
    private LayoutInflater mLayoutInflater;
    private final ItemClickHelper itemClickHelper;
    private final Context mContext;

    private int totalInventory = 0;

    public InventoryAdapter(Context context, ItemClickHelper itemClickHelper){
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.itemClickHelper = itemClickHelper;
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

    public  Stock getStock(int i){
        return stockList.get(i);
    }

    @Override
    public void onItemDismiss(int position) {
        SaleryExecutor.getInstance().getDiskIO().execute(new Runnable() {
            SaleryDatabase db = SaleryDatabase.getInstance(mContext.getApplicationContext());
            @Override
            public void run() {
                db.mStockDao().delete(stockList.get(position));
            }
        });
        stockList.remove(position);
        notifyItemRemoved(position);

    }


    public class InventoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleTextView;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.product_brand);
            itemView.setOnClickListener(this);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        @Override
        public void onClick(View view) {
            int clickedId = getLayoutPosition();
            Stock stock = stockList.get(clickedId);
            itemClickHelper.onItemClick(stock.getStockItemId());
            notifyDataSetChanged();
        }
    }
}
