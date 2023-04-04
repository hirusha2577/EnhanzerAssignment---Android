package com.example.enhanzerassignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import com.example.enhanzerassignment.Fragments.CustomerFragment;

public class AddCustomerDialog extends AppCompatDialogFragment {

    private EditText name, address;
    String text_name, text_address;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_customer_dialog, null);

        name = view.findViewById(R.id.name);
        address = view.findViewById(R.id.address);

        builder.setView(view)
                .setTitle("Add Customer")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text_name = name.getText().toString();
                        text_address = address.getText().toString();

                        DBHandler dbHandler = new DBHandler(getContext());
                        if (text_name.isEmpty() || text_address.isEmpty()) {
                            Toast.makeText(getContext(), "Enter Name and Address", Toast.LENGTH_LONG).show();
                        } else {

                            long status = dbHandler.addCustomer(text_name, text_address, "plan");
                            if (status > 0) {
                                Toast.makeText(getContext(), "Inserted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getContext(), "Not Inserted", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });


        return builder.create();
    }


}
