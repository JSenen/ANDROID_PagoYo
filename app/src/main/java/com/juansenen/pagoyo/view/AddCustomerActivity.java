package com.juansenen.pagoyo.view;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.juansenen.pagoyo.R;
import com.juansenen.pagoyo.db.AppDataBase;
import com.juansenen.pagoyo.domain.Customer;

public class AddCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
    }

    //Boton añadir cliente

    public void butAddCustomer(View view){
        Log.i("INFO--> ","CLICK en añadir boton");
        //Conexion a la DB
        final AppDataBase db = Room.databaseBuilder(this,AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        //Recuperar elementos del layout
        EditText edtxCustomer = findViewById(R.id.edtxt_customer_name);
        //Recuperamos datos
        String newCustomer = edtxCustomer.getText().toString();
        //Crear el objeto
        Customer customer = new Customer(newCustomer, 0);
        //Insertar en BD
        db.customerDAO().insert(customer);
        Log.i("INFO--> ","Añadido a DB");
        //Notificacion emergente
        Toast.makeText(this, "Nuevo registro añadido", Toast.LENGTH_LONG).show();





    }
}