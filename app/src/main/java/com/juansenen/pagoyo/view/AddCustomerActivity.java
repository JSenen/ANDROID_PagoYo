package com.juansenen.pagoyo.view;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        EditText edtxConsumiciones = findViewById(R.id.edtxt_customer_consumiciones);
        EditText edtSanwiches = findViewById(R.id.edtxt_customer_sandwiches);
        //Recuperamos datos
        String newCustomer = edtxCustomer.getText().toString();
        newCustomer = newCustomer.toUpperCase();
        int customerConsumiciones;
        String customerConsumicionesTXT = edtxConsumiciones.getText().toString();
        //Si no se introduce numero, pone 10 por defecto. Si no es numero tambien
        if ( customerConsumicionesTXT.isEmpty()){
            customerConsumiciones = 10;
        }else {
            try{
                customerConsumiciones = Integer.parseInt(edtxConsumiciones.getText().toString());
            }catch (NumberFormatException nfe){
                customerConsumiciones = 10;
            }
        }
        //Si no introduce numero de almuerzos, asigna 10 pordefecto. Si no es numero tambien
        String customerSandwichesTXT = edtSanwiches.getText().toString();
        int customerSandwiches;
        if (customerSandwichesTXT.isEmpty()){
            customerSandwiches = 10;
        }else{
            try {
                customerSandwiches = Integer.parseInt(customerSandwichesTXT);
            }catch (NumberFormatException nfe){
                customerSandwiches = 10;
            }
        }

        //Crear el objeto
        Customer customer = new Customer(newCustomer, 0, customerConsumiciones, 0, customerSandwiches);
        //Insertar en BD
        db.customerDAO().insert(customer);
        Log.i("INFO--> ","Añadido a DB");
        //Notificacion emergente
        //Toast.makeText(this, "Nuevo registro añadido", Toast.LENGTH_LONG).show();
        Snackbar.make(view, "Cliente añadido", Snackbar.LENGTH_LONG).show();
    }

    //Opciones de menu en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu_back,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_back) {

            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);

            return true;
        }

        return false;
    }
}