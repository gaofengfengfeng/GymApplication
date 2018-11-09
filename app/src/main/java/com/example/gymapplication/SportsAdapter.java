package com.example.gymapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.MyViewHolder>{
    private List<SportsModel> mDataset;
    Context mContext;

    public interface OnSportItemClick{
        void onItemClick(int position);
    }

    private OnSportItemClick mListener;
    public void setOnSportItemClick(OnSportItemClick listener) {
        mListener = listener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mIcon;
        public TextView mTxtName;
        public MyViewHolder(View v) {
            super(v);
            mIcon = v.findViewById(R.id.sportImage);
            mTxtName = v.findViewById(R.id.sportName);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SportsAdapter(Context ctx, List<SportsModel> myDataset) {
        mContext = ctx;
        mDataset = myDataset;
    }

    public void updateDataset(List<SportsModel> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }

    public SportsModel getModelByPos(int position) {
        return mDataset.get(position);
    }
    // Create new views (invoked by the layout manager)
    @Override
    public SportsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sports, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    int position = RecyclerAdapterUtils.getViewHolder(view).getAdapterPosition();
                    mListener.onItemClick(position);
                }
            }
        });
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        SportsModel info = mDataset.get(position);
        holder.mTxtName.setText(info.name);
        holder.mIcon.setImageResource(info.icon);
        holder.mIcon.setBackgroundColor(mContext.getResources().getColor(info.bg));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

