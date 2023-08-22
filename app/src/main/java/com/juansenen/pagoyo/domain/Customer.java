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
    @ColumnInfo
    private int numbercoffes;
    @ColumnInfo
    private int sandwiches;
    @ColumnInfo
    private int consusandwiches;

    public Customer(){

    }

    public Customer(long idcustomer, String name, int coffes, int consumiciones, int sandwiches, int consusandwiches) {
        this.idcustomer = idcustomer;
        this.name = name;
        this.coffes = coffes;
        this.numbercoffes = consumiciones;
        this.sandwiches = sandwiches;
        this.consusandwiches = consusandwiches;
    }
    public Customer(String name, int coffes, int ingestions, int sandwiches, int consusandwiches) {
        this.name = name;
        this.coffes = coffes;
        this.numbercoffes = ingestions;
        this.sandwiches = sandwiches;
        this.consusandwiches = consusandwiches;
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

    public int getNumbercoffes() {
        return numbercoffes;
    }

    public void setNumbercoffes(int numbercoffes) {
        this.numbercoffes = numbercoffes;
    }

    public int getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(int sandwiches) {
        this.sandwiches = sandwiches;
    }

    public int getConsusandwiches() {
        return consusandwiches;
    }

    public void setConsusandwiches(int consusandwiches) {
        this.consusandwiches = consusandwiches;
    }
}
