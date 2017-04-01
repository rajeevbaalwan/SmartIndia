package com.example.admin.smartindia.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.smartindia.Models.EmergencyHospitalData;
import com.example.admin.smartindia.R;

import java.util.List;

/**
 * Created by admin on 01/04/2017.
 */
public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.ItemViewHolder> {
    private List<EmergencyHospitalData> list;
    private Context context;

    public EmergencyAdapter(List<EmergencyHospitalData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public EmergencyAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.emergency_activity_custom_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(EmergencyAdapter.ItemViewHolder holder, int position) {

        holder.hospitalName.setText(list.get(position).getHospitalName());
        holder.hospitalAddress.setText(list.get(position).getHospitalAddress());
        holder.emergencySpecialist.setText(list.get(position).getEmergencySpecialist());
        holder.emergencyHospitalOpenHours.setText(list.get(position).getEmergencyHospitalOpenHours());
        holder.emergencyHospitalHelpilne.setText(list.get(position).getEmergencyHospitalHelpilne());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView hospitalName;
        private TextView hospitalAddress;
        private TextView emergencySpecialist;
        private TextView emergencyHospitalOpenHours;
        private TextView emergencyHospitalHelpilne;

        public ItemViewHolder(View itemView) {
            super(itemView);
            hospitalName= (TextView) itemView.findViewById(R.id.emergency_hospial_name);
            hospitalAddress= (TextView) itemView.findViewById(R.id.emergency_hospial_address);
            emergencySpecialist= (TextView) itemView.findViewById(R.id.emergency_hospial_specialist);
            emergencyHospitalOpenHours= (TextView) itemView.findViewById(R.id.emergency_hospial_open_hours);
            emergencyHospitalHelpilne= (TextView) itemView.findViewById(R.id.emergency_hospial_helpline);
        }
    }
}
