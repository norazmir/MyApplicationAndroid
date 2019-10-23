package com.example.myapplicationandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationandroid.Item.DataItem;
import com.example.myapplicationandroid.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private ArrayList<DataItem> dataList;

    public static class DataViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public TextView textView1;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView1);

        }
    }

    public DataAdapter(ArrayList<DataItem> dataItemsList){
        dataList = dataItemsList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        DataViewHolder dataViewHolder = new DataViewHolder(v);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DataItem currentItem = dataList.get(position);

        holder.imageView.setImageResource(currentItem.getImageResources());
        holder.textView.setText(currentItem.getText1());
        holder.textView1.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
