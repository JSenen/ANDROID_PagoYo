package com.juansenen.pagoyo.adapter;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.ULocale;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.juansenen.pagoyo.R;
import com.juansenen.pagoyo.db.AppDataBase;
import com.juansenen.pagoyo.domain.Award;
import com.juansenen.pagoyo.domain.AwardSandwich;
import com.juansenen.pagoyo.domain.CoffesContainer;
import com.juansenen.pagoyo.domain.Converters;
import com.juansenen.pagoyo.domain.Customer;
import com.juansenen.pagoyo.domain.SandWichContainer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListHolder> {

    private List<Customer> customerList;
    private List<Award> awardList;
    private Context context;

    public MainListAdapter (Context context, List<Customer> customerList){
        this.context = context;
        this.customerList = customerList;
    }

    // Otros campos del adaptador

    private AdapterListener adapterListener;

    public MainListAdapter(Context context, List<Customer> customerList, AdapterListener listener) {
        // Inicializa los campos del adaptador

        this.adapterListener = listener;
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
        holder.txtCustomerCoffes.setText(String.valueOf(customerList.get(position).getCoffes()));
        holder.txtCustomerSandwiches.setText(String.valueOf(customerList.get(position).getSandwiches()));

        // Comprobamos el número de cafés y configuramos la visibilidad de la imagen premiada
        if (customerList.get(position).getCoffes() >= customerList.get(position).getNumbercoffes()) {
            holder.imgAward.setVisibility(View.VISIBLE);
            holder.txtDateWin.setVisibility(View.VISIBLE);
            long id = customerList.get(position).getIdcustomer();
            long dat = seeDateWin(id);


            // Convertir el valor long a LocalDate utilizando el converter
            LocalDate localDate = Converters.fromTimestamp(dat);
            LocalDate localDatePlusTen = localDate.plusDays(10);
            LocalDate localDateToday = LocalDate.now();

            //Caluclo diferencia dias entre premio y hoy
            long daysDifference = localDate.until(localDateToday, ChronoUnit.DAYS);


            // Formatear la fecha legible y establecerla en el TextView
            String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String formattedDatePlusTen = localDatePlusTen.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            holder.txtDateWin.setText(formattedDate);
            holder.txtDateEnd.setText(formattedDatePlusTen);

            //Si se supera un dia de los 10 establecidos, el premio desaparece
            if (daysDifference > 10 ) {
                holder.imgAward.setVisibility(View.INVISIBLE);
                holder.txtDateWin.setVisibility(View.INVISIBLE);
                holder.txtDateEnd.setVisibility(View.INVISIBLE);
                //Borramos el premio
                customerList.get(position).setCoffes(0);
                deleteAward(id, position);
            }

        } else {
            holder.imgAward.setVisibility(View.INVISIBLE);
            holder.txtDateWin.setVisibility(View.INVISIBLE);
            holder.txtDateEnd.setVisibility(View.INVISIBLE);
        }

        if (customerList.get(position).getSandwiches() >= customerList.get(position).getConsusandwiches()) {
            holder.imgAwardSandwich.setVisibility(View.VISIBLE);
            holder.txtDateWinSandwich.setVisibility(View.VISIBLE);
            long id = customerList.get(position).getIdcustomer();
            long dat = seeDateWinSandwich(id);


            // Convertir el valor long a LocalDate utilizando el converter
            LocalDate localDate = Converters.fromTimestamp(dat);
            LocalDate localDatePlusTen = localDate.plusDays(10);
            LocalDate localDateToday = LocalDate.now();

            //Caluclo diferencia dias entre premio y hoy
            long daysDifference = localDate.until(localDateToday, ChronoUnit.DAYS);


            // Formatear la fecha legible y establecerla en el TextView
            String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String formattedDatePlusTen = localDatePlusTen.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            holder.txtDateWinSandwich.setText(formattedDate);
            holder.txtDateEndSandwich.setText(formattedDatePlusTen);

            //Si se supera un dia de los 10 establecidos, el premio desaparece
            if (daysDifference > 10 ) {
                holder.imgAwardSandwich.setVisibility(View.INVISIBLE);
                holder.txtDateWinSandwich.setVisibility(View.INVISIBLE);
                holder.txtDateEndSandwich.setVisibility(View.INVISIBLE);
                //Borramos el premio
                customerList.get(position).setSandwiches(0);
                deleteAwardSandwich(id, position);
            }

        } else {
            holder.imgAwardSandwich.setVisibility(View.INVISIBLE);
            holder.txtDateWinSandwich.setVisibility(View.INVISIBLE);
            holder.txtDateEndSandwich.setVisibility(View.INVISIBLE);
        }


    }

    //Obtenemos el tamaño del listado
    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class MainListHolder extends RecyclerView.ViewHolder{

        public TextView txtCustomerName, txtCustomerCoffes, txtCustomerSandwiches, txtDateWin, txtDateEnd
                , txtDateWinSandwich, txtDateEndSandwich;
        public ImageButton btnDeleteCustomer, btnAddCoffe, btnAddSandwich;
        public ImageView imgAward, imgAwardSandwich;
        public View parentview;

        public MainListHolder(View view){
            super(view);

            parentview = view;

            //Recuperamos los elementos del layout
            txtCustomerName = view.findViewById(R.id.txtview_customername);
            txtCustomerCoffes = view.findViewById(R.id.txtview_coffes_customer);
            txtDateWin = view.findViewById((R.id.txtview_datewin));
            txtDateEnd = view.findViewById(R.id.txtview_dateend);
            txtCustomerSandwiches = view.findViewById(R.id.txtview_sandwiches_customer);
            txtDateWinSandwich = view.findViewById(R.id.txtview_datewin_sandwich);
            txtDateEndSandwich = view.findViewById(R.id.txtview_dateend_sandwich);

            btnDeleteCustomer = view.findViewById(R.id.btn_delete_customer);
            btnDeleteCustomer.setOnClickListener(view1 -> deleteCustomer(getAdapterPosition()));

            btnAddCoffe = view.findViewById(R.id.btn_add_coffe);
            btnAddCoffe.setOnClickListener((view2 -> addCoffeCustomer(getAdapterPosition())));

            btnAddSandwich = view.findViewById(R.id.btn_add_sandwich);
            btnAddSandwich.setOnClickListener((view3 -> addSandwinchCustomer(getAdapterPosition())));

            imgAward = view.findViewById(R.id.imgview_award);
            imgAwardSandwich = view.findViewById(R.id.imagenawardSandwich);


        }

        public void deleteCustomer(int position){

            //Creamos dialogo de alerta con opciones
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Desea boorar el cliente?")
                    .setTitle("Eliminar cliente")
                    .setPositiveButton("Si", (dialog, id) -> {
                        //Al pulsar en OK eliminamos cliente de la base de datos
                        final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                                .allowMainThreadQueries().build();
                        Customer customer = customerList.get(position);
                        db.customerDAO().delete(customer);

                        customerList.remove(position);
                        //Notificamos el cambio
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton(("No"), (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private void addSandwinchCustomer(int position) {

            //Buscamos el cliente y añadimos 1 a los almuerzos
            long id = customerList.get(position).getIdcustomer();
            int sandwiches = customerList.get(position).getSandwiches();
            int ingestionSandwich = customerList.get(position).getConsusandwiches();

            if (sandwiches < ingestionSandwich){
                sandwiches = sandwiches + 1;
                if (sandwiches == ingestionSandwich){
                    AwardSandwich awardSandwich = new AwardSandwich();
                    LocalDate currentDate = LocalDate.now();
                    awardSandwich.setDatewin(currentDate);
                    awardSandwich.setIdAwardCustomer(id);
                    updateAwardSandwichDB(awardSandwich);

                }
            }else{
                SandWichContainer sandWichContainer = new SandWichContainer(sandwiches);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("ENTREGAR ALMUERZO")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sandWichContainer.value = 0;
                                updateSandWichDB(sandWichContainer.value, id, position);
                                deleteAwardSandwich(id, position);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();


            }

            updateSandWichDB(sandwiches, id, position);
            Toast.makeText(context,"Almuerzo añadido",Toast.LENGTH_SHORT).show();

        }

        private void addCoffeCustomer(int position) {
            //Buscamos el cliente y añadimos 1 al cafe
            long id = customerList.get(position).getIdcustomer();
            int coffes = customerList.get(position).getCoffes();
            int ingestions = customerList.get(position).getNumbercoffes();

            if (coffes < ingestions){
                coffes = coffes +1;
                if (coffes == ingestions){
                    Award award = new Award();
                    LocalDate currentDate = LocalDate.now();
                    award.setDatewin(currentDate);
                    award.setIdAwardCustomer(id);
                    updateAwardDB(award);
                }
            }else {
                CoffesContainer coffesContainer = new CoffesContainer(coffes);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("ENTREGAR CAFÉ")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        coffesContainer.value = 0;
                                        updateDB(coffesContainer.value, id, position);
                                        deleteAward(id, position);
                                    }
                                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                                AlertDialog dialog = builder.create();
                dialog.show();

            }

            updateDB(coffes, id, position);


            Toast.makeText(context,"Cafe añadido",Toast.LENGTH_SHORT).show();

        }

        private void updateDB(int coffes, long id, int position){
            //Actualizamos la DB
            final AppDataBase db = Room.databaseBuilder(context,AppDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.customerDAO().updateCoffeCustomer(coffes, id);

            // Actualizamos los datos en la lista local
            customerList.get(position).setCoffes(coffes);

            //Notificamos el cambio
            notifyItemChanged(position);

        }

        private void updateAwardDB(Award award){
            //Actulizamos DB de premios
            final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.awardDAO().insert(award);
        }
        private void updateAwardSandwichDB(AwardSandwich awardSandwich){
            //Actulizamos DB de premios almuerzo
            final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.awardSandwichDAO().insert(awardSandwich);
        }
        private void updateSandWichDB(int sandwiches, long id, int position){
            //Actulizamos DB de almuerzos
            final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.customerDAO().updateSandwichesCustomer(sandwiches, id);

            // Actualizamos los datos en la lista local
            customerList.get(position).setSandwiches(sandwiches);

            //Notificamos el cambio
            notifyItemChanged(position);
        }


    }
    public long seeDateWin(long idcustomer){
        //Buscamos en la base de datos
        final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        long dat = db.awardDAO().searchDate(idcustomer);
        return dat;
    }
    public long seeDateWinSandwich(long idcustomer){
        //Buscamos en la base de datos
        final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        long dat = db.awardSandwichDAO().searchDate(idcustomer);
        return dat;
    }
    public void deleteAward(long idcustomer, int position){
        //Eliminamos el premio
        final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.awardDAO().deleteByPosition(idcustomer);

        // Llamar al método en el AdapterListener de la MainActivity
        if (adapterListener != null) {
            adapterListener.onDeleteAndOtherOperations(idcustomer, position);
        }


    }
    public void deleteAwardSandwich(long idcustomer, int position){
        //Eliminamos el premio
        final AppDataBase db = Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.awardSandwichDAO().deleteByPosition(idcustomer);

        // Llamar al método en el AdapterListener de la MainActivity
        if (adapterListener != null) {
            adapterListener.onDeleteAndOtherOperations(idcustomer, position);
        }
    }

    public interface AdapterListener {
        void onDeleteAndOtherOperations(long id, int position);
    }

}
