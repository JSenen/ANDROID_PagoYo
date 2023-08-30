package com.juansenen.pagoyo.view;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.juansenen.pagoyo.R;
import com.juansenen.pagoyo.adapter.MainListAdapter;
import com.juansenen.pagoyo.db.AppDataBase;
import com.juansenen.pagoyo.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainListAdapter.AdapterListener {

    public List<Customer> customerList;
    private List<Customer> originalCustomerList; // Lista original sin filtrar
    public MainListAdapter adapter;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos listas
        customerList = new ArrayList<>();
        originalCustomerList = new ArrayList<>(); // Inicializa la lista original

        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterCustomers(editable.toString());
            }
        });

        // Recuperamos el recyclerview del layout
        RecyclerView recyclerView = findViewById(R.id.rcview_mainlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // Construimos el adapter del recyclerview
        adapter = new MainListAdapter(this, customerList);
        recyclerView.setAdapter(adapter);

        loadCustomersFromDatabase(); // Carga los datos desde la base de datos
    }

    private void loadCustomersFromDatabase() {
        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        // Añadimos los clientes de la BD a originalCustomerList
        originalCustomerList.addAll(db.customerDAO().getAll());
        // Añadimos los mismos datos a customerList inicialmente
        customerList.addAll(originalCustomerList);
    }

    private void filterCustomers(String searchText) {
        List<Customer> filteredList = new ArrayList<>(); // Crear una lista para los resultados filtrados
        for (Customer customer : originalCustomerList) {
            if (customer.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(customer); // Agregar a la lista filtrada
            }
        }
        customerList.clear(); // Limpiar la lista original
        customerList.addAll(filteredList); // Agregar los elementos filtrados a la lista original
        adapter.notifyDataSetChanged(); // Notificar cambios al adaptador
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
        } else if (item.getItemId() == R.id.item_info) {
            Intent intent = new Intent(this, InfoActivity.class);
            finish();
            startActivity(intent);

        }

        return false;
    }

    private void performDeleteAndOtherOperations(long id, int position) {
        // Llamar a deleteAward en el adaptador
        adapter.deleteAward(id,position);

        // Realizar otras operaciones o cambios en los datos
        customerList.get(position).setCoffes(0);

        // Notificar al adaptador de los cambios
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteAndOtherOperations(long id, int position) {
        performDeleteAndOtherOperations(id, position);
    }
}