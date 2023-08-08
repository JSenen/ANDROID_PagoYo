package com.juansenen.pagoyo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.pagoyo.R;
import com.juansenen.pagoyo.domain.Customer;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListHolder> {

    private List<Customer> customerList;
    private Context context;

    public MainListAdapter (Context context, List<Customer> customerList){
        this.context = context;
        this.customerList = customerList;
    }

    //Creamos ViewHolder e inicializamos campos del RecyclerView
    @Override
    public MainListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcview_mainlist_items, parent, false);
        return new MainListHolder(view);
    }
    //Establecemos los datos sobre un Item del Recycler
    @Override
    public void onBindViewHolder(MainListHolder holder, int position) {
        holder.txtCustomerName.setText(customerList.get(position).getName());

    }

    //Obtenemos el tamaño del listado
    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class MainListHolder extends RecyclerView.ViewHolder{

        public TextView txtCustomerName;
        public View parentview;

        public MainListHolder(View view){
            super(view);
            parentview = view;

            //Recuperamos los elementos del layout
            txtCustomerName = view.findViewById(R.id.txtview_customername);

        }
    }


}
