package com.example.admin.smartindia.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.smartindia.Models.UserCurrentMedicalData;
import com.example.admin.smartindia.R;

import java.util.List;

/**
 * Created by admin on 17/03/2017.
 */
public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.ItemViewHolder> {
    private Context context;
    private List<UserCurrentMedicalData> list;

    public LandingAdapter(Context context, List<UserCurrentMedicalData> list) {
        this.context=context;
        this.list =list;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.landing_custom_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.time.setText(list.get(position).getTime());
        holder.medName.setText(list.get(position).getMed());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView medName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            time= (TextView) itemView.findViewById(R.id.current_medicine_time);
            medName= (TextView) itemView.findViewById(R.id.current_medicine_name);
        }
    }
}
