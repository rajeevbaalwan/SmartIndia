package com.example.admin.smartindia.Adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
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
    private TextView medicineTextview;
    private RelativeLayout medicineTextviewContainer;
    private RecyclerView recyclerView;

    public HistoryAdapter(List<UserMedicalHistoryData> list, Context context, RelativeLayout
            medicineTextviewContainer, TextView medicineTextview, RecyclerView recyclerView) {
        this.list = list;
        this.context = context;
        this.medicineTextview=medicineTextview;
        this.medicineTextviewContainer=medicineTextviewContainer;
        this.recyclerView=recyclerView;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.history_custom_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.hospitalName.setText(list.get(position).getName());
        holder.doctorName.setText(list.get(position).getDoctor());
        holder.issue.setText(list.get(position).getIssue());
        holder.date.setText(list.get(position).getDate());
        holder.medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicineTextview.setText(list.get(position).getMedicine());
                medicineTextviewContainer.setVisibility(View.VISIBLE);
                medicineTextview.animate()
                        .translationY(-recyclerView.getHeight()/2)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                recyclerView.animate()
                                        .alpha(0.1f)
                                        .setDuration(500);
                            }
                        });
            }
        });

        medicineTextviewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicineTextview.animate()
                        .translationY(Resources.getSystem()
                        .getDisplayMetrics().heightPixels/2)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                recyclerView.animate()
                                        .alpha(1.0f)
                                        .setDuration(500);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                medicineTextviewContainer.setVisibility(View.GONE);
                            }
                        });
            }
        });
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
