package com.juansenen.pagoyo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.NonNull;

@Entity(tableName = "customer")
public class Customer {

    @PrimaryKey
    @NonNull
    private int idcustomer;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int coffes;

    public Customer(){

    }

    public Customer(int idcustomer, String name, int coffes) {
        this.idcustomer = idcustomer;
        this.name = name;
        this.coffes = coffes;
    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoffes() {
        return coffes;
    }

    public void setCoffes(int coffes) {
        this.coffes = coffes;
    }
}
