package com.example.admin.smartindia.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.smartindia.Models.UserMedicalHistoryData;
import com.example.admin.smartindia.R;

import java.util.List;

/**
 * Created by admin on 18/03/2017.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemViewHolder> {
    private List<UserMedicalHistoryData> list;
    private Context context;

    public HistoryAdapter(List<UserMedicalHistoryData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.history_custom_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.hospitalName.setText(list.get(position).getName());
        holder.doctorName.setText(list.get(position).getDoctor());
        holder.issue.setText(list.get(position).getIssue());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView hospitalName;
        private  TextView doctorName;
        private TextView issue;
        private Button medicine;
        private TextView date;

        public ItemViewHolder(View itemView) {
            super(itemView);
            hospitalName= (TextView) itemView.findViewById(R.id.hospital_name);
            doctorName= (TextView) itemView.findViewById(R.id.doctor_name);
            issue= (TextView) itemView.findViewById(R.id.issue);
            date= (TextView) itemView.findViewById(R.id.date);
            medicine= (Button) itemView.findViewById(R.id.history_medicine_button);
        }
    }

    public  void changeList(List<UserMedicalHistoryData> list){
        this.list=list;
        this.notifyDataSetChanged();
    }
}
