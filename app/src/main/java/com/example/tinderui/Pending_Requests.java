package com.example.tinderui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Pending_Requests extends AppCompatActivity {
    RecyclerView rcv;
    ArrayAdapterPendingRequests adapter;
    RecyclerView.LayoutManager mgr;
    ArrayList<String> emp_id;
    ArrayList<String> emp_name;
    ArrayList<String> emp_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending__requests);
        rcv=findViewById(R.id.rcv);
        emp_id=new ArrayList<>();
        emp_email=new ArrayList<>();
        emp_name=new ArrayList<>();
        emp_email.add("raghavpartani1@gmail.com");
        emp_id.add("18100BTCMCI02967");
        emp_name.add("Raghav Partani");
        emp_email.add("raghavpartani1@gmail.com");
        emp_id.add("18100BTCMCI02967");
        emp_name.add("Raghav Partani");
        emp_email.add("raghavpartani1@gmail.com");
        emp_id.add("18100BTCMCI02967");
        emp_name.add("Raghav Partani");
        mgr = new LinearLayoutManager(this);

        rcv.setLayoutManager(mgr);
        adapter=new ArrayAdapterPendingRequests(Pending_Requests.this,emp_id,emp_name,emp_email);
        rcv.setAdapter(adapter);
    }
}