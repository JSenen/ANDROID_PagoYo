package com.juansenen.pagoyo.view;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.juansenen.pagoyo.R;
import com.juansenen.pagoyo.adapter.MainListAdapter;
import com.juansenen.pagoyo.db.AppDataBase;
import com.juansenen.pagoyo.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainListAdapter.AdapterListener {

    public List<Customer> customerList;
    public MainListAdapter adapter;
    private RecyclerView recyclerView; // Declaración de recyclerView como variable miembro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos la lista de clientes
        customerList = new ArrayList<>();

        // Inicializamos el recyclerview del layout
        recyclerView = findViewById(R.id.rcview_mainlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Construimos el adapter del recyclerview
        adapter = new MainListAdapter(this, customerList, this);
        recyclerView.setAdapter(adapter);
    }




    //Al volver a la Activity principal, recuperamos los datos de la BD
    @Override
    protected void onResume() {
        super.onResume();

        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        // Limpiamos la lista de clientes
        customerList.clear();
        // Añadimos los clientes de la BD
        customerList.addAll(db.customerDAO().getAll());

        // Notificamos cambios al adaptador
        adapter.notifyDataSetChanged();
    }

    //Opciones de menu en la action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("METODO","--> filtarlista por texto "+newText);
                adapter.filtrarLista(newText); // Utiliza la referencia al adaptador para llamar al método
                return true;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add) {

            Intent intent = new Intent(this, AddCustomerActivity.class);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.item_update) {
            reloadAndNotifyAdapter(); // Volver a cargar y notificar al adaptador
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void performDeleteAndOtherOperations(long id, int position) {
        // Llamar a deleteAward en el adaptador
        adapter.deleteAward(id, position);

        // Realizar otras operaciones o cambios en los datos
        customerList.get(position).setCoffes(0);

        // Llamar a reloadAndNotifyAdapter para actualizar los datos
        reloadAndNotifyAdapter();
    }

    @Override
    public void onDeleteAndOtherOperations(long id, int position) {
        performDeleteAndOtherOperations(id, position);
    }
    //Actualizar todos los datos recargando
    private void reloadAndNotifyAdapter() {
        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        // Limpiar la lista de clientes
        customerList.clear();

        // Obtener nuevamente los clientes de la BD
        customerList.addAll(db.customerDAO().getAll());

        // Notificar cambios al adaptador
        adapter.notifyDataSetChanged();
    }

}