package com.juansenen.pagoyo.db;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.juansenen.pagoyo.domain.Award;
import com.juansenen.pagoyo.domain.Customer;

@Database(entities = {Customer.class, Award.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CustomerDAO customerDAO();
    public abstract AwardDAO awardDAO();

    // Aqui definimos las migraciones entre Bases de Datos
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Implementa la lógica de migración aquí
        }
    };

    // Método para obtener una instancia de la base de datos
    public static AppDataBase getInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                .addMigrations(MIGRATION_1_2) // Agrega la migración aquí
                .build();
    }

}
