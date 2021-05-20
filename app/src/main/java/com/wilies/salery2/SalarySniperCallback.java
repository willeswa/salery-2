package com.wilies.salery2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.wilies.salery2.model.InventoryAdapter;

public class SalarySniperCallback extends ItemTouchHelper.Callback {

    private final InventoryAdapter mAdapter;

    public SalarySniperCallback(InventoryAdapter adapter){
        this.mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getLayoutPosition());
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
