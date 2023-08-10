package com.juansenen.pagoyo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.NonNull;

@Entity(tableName = "customer")
public class Customer {

    @PrimaryKey(autoGenerate = true)
    private long idcustomer;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private int coffes;
    @ColumnInfo
    private boolean award = false;

    public Customer(){

    }

    public Customer(long idcustomer, String name, int coffes, boolean award) {
        this.idcustomer = idcustomer;
        this.name = name;
        this.coffes = coffes;
    }
    public Customer(String name, int coffes) {
        this.name = name;
        this.coffes = coffes;
    }

    public long getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(long idcustomer) {
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

    public boolean isAward() {
        return award;
    }

    public void setAward(boolean award) {
        this.award = award;
    }
}
