package com.example.enhanzerassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enhanzerassignment.Adapter.CustomerAdapter;
import com.example.enhanzerassignment.Fragments.CartFragment;
import com.example.enhanzerassignment.Fragments.CustomerFragment;
import com.example.enhanzerassignment.Fragments.HomeFragment;
import com.example.enhanzerassignment.Fragments.LocationFragment;
import com.example.enhanzerassignment.Model.Customer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    private ImageView toolbar_icon;
    private TextView toolbar_title, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        toolbar_icon = findViewById(R.id.toolbar_icon);
        toolbar_title = findViewById(R.id.toolbar_title);
        count = findViewById(R.id.count);

        bottomNavigationView = findViewById(R.id.bottom_nav);


        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new CustomerFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_customers);
        toolbar_title.setText("Customers");
        toolbar_icon.setImageResource(R.drawable.ic_people_w);
        count.setText(Integer.toString(customerCount()));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        toolbar_title.setText("Home");
                        toolbar_icon.setImageResource(R.drawable.ic_home);
                        count.setVisibility(View.GONE);
                        break;
                    case R.id.nav_customers:
                        fragment = new CustomerFragment();
                        toolbar_title.setText("Customers");
                        toolbar_icon.setImageResource(R.drawable.ic_people_w);
                        count.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_location:
                        fragment = new LocationFragment();
                        toolbar_title.setText("Map");
                        toolbar_icon.setImageResource(R.drawable.ic_location);
                        count.setVisibility(View.GONE);
                        break;
                    case R.id.nav_cart:
                        fragment = new CartFragment();
                        toolbar_title.setText("Cart");
                        toolbar_icon.setImageResource(R.drawable.ic_cart);
                        count.setVisibility(View.GONE);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.top_qr:
                Toast.makeText(this, "QR", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.top_search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;

        }
        return false;
    }

    public int customerCount() {
        DBHandler dbHandler = new DBHandler(this);
        Cursor cursor = dbHandler.readAllInfo();
        int count = 0;
        while (cursor.moveToNext()) {
            count = count + 1;
        }
        return count;
    }


}