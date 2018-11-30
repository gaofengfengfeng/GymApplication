package com.example.gymapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymapplication.model.FigureModel;
import com.example.gymapplication.R;
import com.example.gymapplication.model.MyCoachModel;
import com.example.gymapplication.model.SportsModel;
import com.example.gymapplication.util.RecyclerAdapterUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCoachAdapter extends RecyclerView.Adapter<MyCoachAdapter.MyViewHolder>{
    private List<MyCoachModel> mDataset;
    Context mContext;

    public interface OnCoachItemClick{
        void onCoachClick(int position);
    }

    private OnCoachItemClick mListener;
    public void setOnCoachItemClick(OnCoachItemClick listener) {
        mListener = listener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CircleImageView mAvatar;
        public TextView mTxtName;
        public TextView mTxtExtra;
        public MyViewHolder(View v) {
            super(v);
            mAvatar = v.findViewById(R.id.img_avator);
            mTxtName = v.findViewById(R.id.txt_name);
            mTxtExtra = v.findViewById(R.id.txt_extra);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyCoachAdapter(List<MyCoachModel> myDataset) {
        mDataset = myDataset;
    }

    public MyCoachAdapter(Context ctx, List<MyCoachModel> myDataset) {
        mContext = ctx;
        mDataset = myDataset;
    }

    public void updateDataset(List<MyCoachModel> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }

    public MyCoachModel getModelByPos(int position) {
        return mDataset.get(position);
    }
    // Create new views (invoked by the layout manager)
    @Override
    public MyCoachAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_coach, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    int position = RecyclerAdapterUtils.getViewHolder(view).getAdapterPosition();
                    mListener.onCoachClick(position);
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
        MyCoachModel info = mDataset.get(position);
        holder.mTxtName.setText(info.name);
        holder.mTxtExtra.setText(info.intro);
        holder.mAvatar.setImageResource(info.avatar);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

