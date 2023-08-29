package com.juansenen.pagoyo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juansenen.pagoyo.domain.Customer;

import java.util.List;

@Dao
public interface CustomerDAO {

    @Query("SELECT * FROM customer ORDER BY name")
    List<Customer> getAll();

    @Insert
    void insert(Customer customer);
    @Delete
    void delete(Customer customer);

    @Query("UPDATE customer SET coffes=:coffe WHERE idcustomer = :id")
    void updateCoffeCustomer(int coffe, long id);
    @Query("UPDATE customer SET sandwiches=:sandwiches WHERE idcustomer = :id")
    void updateSandwichesCustomer(int sandwiches, long id);

    @Query("SELECT * FROM customer WHERE name LIKE :query || '%'")
    List<Customer> searchCustomersByName(String query);


}
