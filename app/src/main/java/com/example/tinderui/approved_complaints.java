package com.example.tinderui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tinderui.adapters.ArrayAdapterApprovedComplaints;
import com.example.tinderui.internetcheck.InternetCheck;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class approved_complaints extends AppCompatActivity {
    RecyclerView rcv;
    ArrayAdapterApprovedComplaints adapter;
    RecyclerView.LayoutManager mgr;
    ArrayList<String> complaint;
    ArrayList<String> subject;
    ArrayList<String> emp_name;
    ArrayList<String> upvote;
    ArrayList<String> complaint_id;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_complaints);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rcv = findViewById(R.id.rcv);
        complaint = new ArrayList<>();
        subject = new ArrayList<>();
        upvote = new ArrayList<>();
        emp_name = new ArrayList<>();
        complaint_id = new ArrayList<>();


        InternetCheck internetCheck=new InternetCheck();
        boolean b=internetCheck.checkConnection(approved_complaints.this);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        final String company = sh.getString("company", "");

        if(b) {
            String url = "https://complainbox2000.000webhostapp.com/approved.php";
            pd = new ProgressDialog(this, R.style.MyAlertDialogStyle);
            pd.setTitle("Connecting Server");
            pd.setMessage("loading...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.dismiss();
                    if (response.trim().equals("")) {
                        Toast.makeText(approved_complaints.this, "There are no approved complaints to display", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Toast.makeText(approved_complaints.this, "hii" + response, Toast.LENGTH_SHORT).show();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name1 = null;
                                String complaint_id1 = null;
                                String subject1 = null;
                                String description1 = null;
                                String upvote1 = null;
                                subject1 = jsonObject.getString("subject");
                                name1 = jsonObject.getString("name");
                                description1 = jsonObject.getString("description");
                                complaint_id1 = jsonObject.getString("complaint_id");
                                upvote1 = jsonObject.getString("upvote");
                                complaint.add(description1);
                                upvote.add(upvote1 + " upvote");
                                subject.add(subject1);
                                complaint_id.add(complaint_id1);
                                emp_name.add("Raised by " + name1);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pd.dismiss();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("company", company);
                    return map;
                }
            };
            RequestQueue mque = Volley.newRequestQueue(getApplicationContext());
            mque.add(stringRequest);
            mgr = new LinearLayoutManager(this);

            rcv.setLayoutManager(mgr);
            adapter = new ArrayAdapterApprovedComplaints(this, complaint, emp_name, upvote, subject, complaint_id);
            rcv.setAdapter(adapter);

        }
        else {
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}