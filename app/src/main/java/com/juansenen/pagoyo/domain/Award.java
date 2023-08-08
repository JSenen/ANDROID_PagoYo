package com.juansenen.pagoyo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.NonNull;

@Entity(tableName = "award")
public class Award {

    @PrimaryKey(autoGenerate = true)
    private int idAward;
    @ColumnInfo
    private String datewin;
    @ColumnInfo
    private int idAwardCustomer;

    public Award(){}

    public Award(int idAward, String datewin, int idAwardCustomer) {
        this.idAward = idAward;
        this.datewin = datewin;
        this.idAwardCustomer = idAwardCustomer;
    }

    public int getIdAward() {
        return idAward;
    }

    public void setIdAward(int idAward) {
        this.idAward = idAward;
    }

    public String getDatewin() {
        return datewin;
    }

    public void setDatewin(String datewin) {
        this.datewin = datewin;
    }

    public int getIdAwardCustomer() {
        return idAwardCustomer;
    }

    public void setIdAwardCustomer(int idAwardCustomer) {
        this.idAwardCustomer = idAwardCustomer;
    }
}
