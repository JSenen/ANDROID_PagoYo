package com.juansenen.pagoyo.db;

import static com.juansenen.pagoyo.db.Constans.DATABASE_NAME;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.juansenen.pagoyo.domain.Award;
import com.juansenen.pagoyo.domain.Converters;
import com.juansenen.pagoyo.domain.Customer;

@Database(entities = {Customer.class, Award.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract CustomerDAO customerDAO();
    public abstract AwardDAO awardDAO();

    // Aqui definimos las migraciones entre Bases de Datos
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE customer ADD COLUMN numbercoffes INTEGER");
        }
    };

    // Método para obtener una instancia de la base de datos
    public static AppDataBase getInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                .addMigrations(MIGRATION_2_3) // Agrega la migración aquí
                .build();
    }

}
