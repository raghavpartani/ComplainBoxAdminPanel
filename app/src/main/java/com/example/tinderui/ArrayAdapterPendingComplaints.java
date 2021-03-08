package com.example.tinderui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArrayAdapterPendingComplaints extends RecyclerView.Adapter<ArrayAdapterPendingComplaints.Myholder> {
    Context context;
    ArrayList<String> arrayList_upvotes;
    ArrayList<String> arrayList_emp_name;
    ArrayList<String> arrayList_complaint;
    public ArrayAdapterPendingComplaints(Context context, ArrayList<String> complaint, ArrayList<String> emp_name, ArrayList<String> upvotes) {
        this.context=context;
        this.arrayList_complaint=complaint;
        this.arrayList_upvotes=upvotes;
        this.arrayList_emp_name=emp_name;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.pending_complaint_view,parent,false);
        ArrayAdapterPendingComplaints.Myholder holder = new ArrayAdapterPendingComplaints.Myholder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.complaint.setText(arrayList_complaint.get(position));
        holder.emp_name.setText(arrayList_emp_name.get(position));
        holder.upvotes.setText(arrayList_upvotes.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList_complaint.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView complaint;TextView upvotes;TextView emp_name;
        Button accept,reject;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            complaint=itemView.findViewById(R.id.complaint);
            upvotes=itemView.findViewById(R.id.upvote);
            emp_name=itemView.findViewById(R.id.raised_by);
            accept=itemView.findViewById(R.id.accept);
            reject=itemView.findViewById(R.id.reject);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accept.setText("Approved");
                    reject.setVisibility(View.INVISIBLE);
                    accept.setLayoutParams(new LinearLayout.LayoutParams(accept.getWidth()+accept.getWidth()+6, accept.getHeight()));
                    accept.setEnabled(false);
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accept.setVisibility(View.INVISIBLE);
                    reject.setText("Rejected");
                    reject.setLayoutParams(new LinearLayout.LayoutParams(reject.getWidth()+reject.getWidth()+6, reject.getHeight()));
                    reject.setEnabled(false);

                }
            });
        }
    }
}
