package com.juansenen.pagoyo.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.juansenen.pagoyo.domain.Customer;

import java.util.List;

@Dao
public interface CustomerDAO {

    @Query("SELECT * FROM customer")
    List<Customer> getAll();

    @Insert
    void insert(Customer customer);


}
