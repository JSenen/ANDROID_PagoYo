package com.juansenen.pagoyo.view;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juansenen.pagoyo.R;
import com.juansenen.pagoyo.adapter.MainListAdapter;
import com.juansenen.pagoyo.db.AppDataBase;
import com.juansenen.pagoyo.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Customer> customerList;
    public MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vaciamos lista
        customerList = new ArrayList<>();

        //Recuperamos el recyclerview del layout
        RecyclerView recyclerView = findViewById(R.id.rcview_mainlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Construimos el adapter del recyclerview
        adapter = new MainListAdapter(this,customerList);
        recyclerView.setAdapter(adapter);
    }

    //Al volver a la Activity principal, recuperamos los datos de la BD
    @Override
    protected void onResume() {
        super.onResume();



        final AppDataBase db = Room.databaseBuilder(this,AppDataBase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();

        //Limpiamos la lista de clientes
        customerList.clear();
        //Añadimos los clientes de la BD
        customerList.addAll(db.customerDAO().getAll());
        //Notificamos cambios al adapter
        adapter.notifyDataSetChanged();

    }

    //Opciones de menu en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add) {

            Intent intent = new Intent(this, AddCustomerActivity.class);
            finish();
            startActivity(intent);

            return true;
        }

        return false;
    }

    private void performDeleteAndOtherOperations(long id, int position) {
        // Llamar a deleteAward en el adaptador
        adapter.deleteAward(id,position);

        // Realizar otras operaciones o cambios en los datos

        // Notificar al adaptador de los cambios
        adapter.notifyDataSetChanged();
    }

}