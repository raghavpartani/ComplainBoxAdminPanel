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

public class ArrayAdapterPendingRequests extends RecyclerView.Adapter<ArrayAdapterPendingRequests.MyHolder> {
    Context context;
    ArrayList<String> arrayList_emp_id;
    ArrayList<String> arrayList_emp_name;

    ArrayList<String> arrayList_emp_email;
    public ArrayAdapterPendingRequests(Context context, ArrayList<String> emp_id, ArrayList<String> emp_name,ArrayList<String> emp_email) {
        this.context=context;
        this.arrayList_emp_email=emp_email;
        this.arrayList_emp_id=emp_id;
        this.arrayList_emp_name=emp_name;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.pending_request_view,parent,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.emp_id.setText(arrayList_emp_id.get(position));
        holder.emp_name.setText(arrayList_emp_name.get(position));
        holder.emp_email.setText(arrayList_emp_email.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList_emp_email.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView emp_id;TextView emp_email;TextView emp_name;
        Button result;
        Button accept,reject;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            emp_id=itemView.findViewById(R.id.emp_id);
            emp_email=itemView.findViewById(R.id.emp_email);
            emp_name=itemView.findViewById(R.id.emp_name);


            accept=itemView.findViewById(R.id.accept);
            reject=itemView.findViewById(R.id.reject);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Accept"+emp_id.getText().toString(), Toast.LENGTH_SHORT).show();
                    accept.setText("Accepted");
                    reject.setVisibility(View.INVISIBLE);
                    accept.setLayoutParams(new LinearLayout.LayoutParams(accept.getWidth()+accept.getWidth()+6, accept.getHeight()));
                    accept.setEnabled(false);
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Reject"+emp_id.getText().toString(), Toast.LENGTH_SHORT).show();
                    accept.setVisibility(View.INVISIBLE);
                    reject.setText("Rejected");
                    reject.setLayoutParams(new LinearLayout.LayoutParams(reject.getWidth()+reject.getWidth()+6, reject.getHeight()));
                    reject.setEnabled(false);

               }
            });
        }
    }
}
