package com.example.gymapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gymapplication.R;
import com.example.gymapplication.model.SportsModel;
import com.example.gymapplication.model.VideoModel;
import com.example.gymapplication.util.RecyclerAdapterUtils;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder>{
    private List<VideoModel> mDataset;
    Context mContext;

    public interface OnVideoItemClick{
        void onItemClick(int position);
    }

    private OnVideoItemClick mListener;
    public void setOnVideoItemClick(OnVideoItemClick listener) {
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
            mIcon = v.findViewById(R.id.videoImage);
            mTxtName = v.findViewById(R.id.videoName);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public VideoAdapter(Context ctx, List<VideoModel> myDataset) {
        mContext = ctx;
        mDataset = myDataset;
    }

    public void updateDataset(List<VideoModel> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }

    public VideoModel getModelByPos(int position) {
        return mDataset.get(position);
    }
    // Create new views (invoked by the layout manager)
    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
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
        VideoModel info = mDataset.get(position);
        holder.mTxtName.setText(info.name);
        holder.mIcon.setImageResource(info.icon);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

