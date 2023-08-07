package com.juansenen.pagoyo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.juansenen.pagoyo.domain.Customer;

@Database(entities = {Customer.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CustomerDAO customerDAO();

}
