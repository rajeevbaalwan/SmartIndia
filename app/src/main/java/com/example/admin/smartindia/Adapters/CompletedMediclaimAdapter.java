package com.example.admin.smartindia.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.smartindia.Models.CompletedMediclaimData;
import com.example.admin.smartindia.R;

import java.util.List;

/**
 * Created by admin on 18/03/2017.
 */
public class CompletedMediclaimAdapter extends RecyclerView.Adapter<CompletedMediclaimAdapter.ItemViewHolder> {
    private Context context;
    private List<CompletedMediclaimData> list;

    public CompletedMediclaimAdapter(Context context, List<CompletedMediclaimData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.completed_mediclaim_custom_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.hName.setText(list.get(position).getHospitalName());
        holder.date.setText(list.get(position).getDate());
        holder.amount.setText(list.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView hName;
        private TextView date;
        private TextView amount;
        public ItemViewHolder(View itemView) {
            super(itemView);
            hName= (TextView) itemView.findViewById(R.id.completed_mediclaim_hospital_name);
            date= (TextView) itemView.findViewById(R.id.completed_mediclaim_date);
            amount= (TextView) itemView.findViewById(R.id.completed_mediclaim_Amount);
        }
    }
}
