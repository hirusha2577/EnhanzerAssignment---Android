package com.example.enhanzerassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enhanzerassignment.MainActivity;
import com.example.enhanzerassignment.Model.Customer;
import com.example.enhanzerassignment.R;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyHolder> {

    Context context;
    List<Customer> customerList;

    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final String id = customerList.get(position).getId();
        String name = customerList.get(position).getName();
        String address = customerList.get(position).getAddress();
        String state = customerList.get(position).getState();

        holder.name.setText(name);
        holder.address.setText(address);

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
      TextView name,address;
      public MyHolder(@NonNull View itemView) {
          super(itemView);
          name = itemView.findViewById(R.id.name);
          address = itemView.findViewById(R.id.address);

      }
  }
}
