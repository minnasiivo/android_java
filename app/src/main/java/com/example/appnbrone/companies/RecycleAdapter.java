package com.example.appnbrone.companies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appnbrone.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.GridItemViewHolder> {
    private Context context;
    private List<Detail> mItemList;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    /**
     * Called when a grid adapter has been called
     *
     * @param context   The context of main activity
     * @param mItemList Detail List of recyclerview grid that contains data
     */
    public RecycleAdapter(Context context, List<Detail> mItemList) {
        this.context = context;
        this.mItemList = mItemList;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new GridItemViewHolder(itemView, this);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link GridItemViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     *RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link GridItemViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(GridItemViewHolder, int)} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        Detail items = mItemList.get(position);
        //holder.mTitle.setText(items.getName());
        holder.mTitle.setText("pööööööööööööööö");
        holder.mPosition.setText("" + items.getPosition());
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    /**
     * Called when a grid item has been called
     *
     * @param onItemClickListener The view that was clicked
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(GridItemViewHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getBindingAdapterPosition(), itemHolder.getItemId());
        }
    }

    public class GridItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle, mPosition;
        public RecycleAdapter mAdapter;

        public GridItemViewHolder(View itemView, RecycleAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            mTitle = (TextView) itemView.findViewById(R.id.item_title);
            mPosition = (TextView) itemView.findViewById(R.id.item_position);
            itemView.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }
    }
}
