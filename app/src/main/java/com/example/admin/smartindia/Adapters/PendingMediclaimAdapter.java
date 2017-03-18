package com.example.admin.smartindia.Adapters;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.smartindia.Activities.PendingMediclaimActivity;
import com.example.admin.smartindia.Models.PendingMediclaimData;
import com.example.admin.smartindia.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Rajeev Yadav on 18/03/2017.
 */
public class PendingMediclaimAdapter extends RecyclerView.Adapter<PendingMediclaimAdapter.ItemViewHolder> {
    private Context context;
    private List<PendingMediclaimData> list;
    private CoordinatorLayout coordinatorLayout;

    public PendingMediclaimAdapter(Context context, List<PendingMediclaimData> list, CoordinatorLayout coordinatorLayout) {
        this.context = context;
        this.list = list;
        this.coordinatorLayout=coordinatorLayout;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.pending_mediclaim_custom_view,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.hName.setText(list.get(position).getHospitalName());
        holder.date.setText(list.get(position).getDate());
        holder.amount.setText(list.get(position).getAmount());
        holder.remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(coordinatorLayout,"Done",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView hName;
        private TextView date;
        private TextView amount;
        private Button remind;
        public ItemViewHolder(View itemView) {
            super(itemView);
            hName= (TextView) itemView.findViewById(R.id.pending_mediclaim_hospital_name);
            date= (TextView) itemView.findViewById(R.id.pending_mediclaim_date);
            amount= (TextView) itemView.findViewById(R.id.pending_mediclaim_Amount);
            remind= (Button) itemView.findViewById(R.id.pending_mediclaim_remind_button);
        }
    }
}
