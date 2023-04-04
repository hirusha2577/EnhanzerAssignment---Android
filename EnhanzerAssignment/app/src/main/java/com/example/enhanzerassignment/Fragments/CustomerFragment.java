package com.example.enhanzerassignment.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enhanzerassignment.Adapter.CustomerAdapter;
import com.example.enhanzerassignment.AddCustomerDialog;
import com.example.enhanzerassignment.DBHandler;
import com.example.enhanzerassignment.MainActivity;
import com.example.enhanzerassignment.Model.Customer;
import com.example.enhanzerassignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CustomerFragment extends Fragment {

    private RelativeLayout plan, delivered, activity;
    private TextView plan_text, delivered_text, activity_text;
    private RecyclerView recyclerView;
    CustomerAdapter customerAdapter;
    List<Customer> customerList;


    public CustomerFragment() {
        // Customer fragment constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);


        plan = view.findViewById(R.id.plan);
        delivered = view.findViewById(R.id.delivered);
        activity = view.findViewById(R.id.activity);
        plan_text = view.findViewById(R.id.plan_text);
        delivered_text = view.findViewById(R.id.delivered_text);
        activity_text = view.findViewById(R.id.activity_text);

        recyclerView = view.findViewById(R.id.recyclerView);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        plan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                plan.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tab_left));
                plan_text.setTextColor(R.color.baseColor);

                activity.setBackgroundResource(R.color.baseColor);
                activity_text.setTextColor(Color.WHITE);
                delivered.setBackgroundResource(R.color.baseColor);
                delivered_text.setTextColor(Color.WHITE);
            }
        });

        delivered.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                delivered.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tab_mid));
                delivered_text.setTextColor(R.color.baseColor);

                activity.setBackgroundResource(R.color.baseColor);
                activity_text.setTextColor(Color.WHITE);
                plan.setBackgroundResource(R.color.baseColor);
                plan_text.setTextColor(Color.WHITE);
            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                activity.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tab_rigth));
                activity_text.setTextColor(R.color.baseColor);

                delivered.setBackgroundResource(R.color.baseColor);
                delivered_text.setTextColor(Color.WHITE);
                plan.setBackgroundResource(R.color.baseColor);
                plan_text.setTextColor(Color.WHITE);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);

        customerList = new ArrayList<>();
        loadCustomers();

        return view;
    }

    public void loadCustomers() {
        DBHandler dbHandler = new DBHandler(getContext());
        Cursor cursor = dbHandler.readAllInfo();

        List<Customer> customerList;
        customerList = new ArrayList<Customer>();

        while(cursor.moveToNext()){
            customerList.add(new Customer(
                    cursor.getString(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("address"))
            ));
        }
        cursor.close();
        customerAdapter = new CustomerAdapter(getActivity(), customerList);
        recyclerView.setAdapter(customerAdapter);

    }

    public void openDialog() {
        AddCustomerDialog addCustomerDialog = new AddCustomerDialog();
        addCustomerDialog.show(getChildFragmentManager(), "Add Customer Dialog");
    }




}