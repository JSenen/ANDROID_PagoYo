package com.juansenen.pagoyo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

import lombok.NonNull;

@Entity(tableName = "award")
public class Award {

    @PrimaryKey(autoGenerate = true)
    private long idAward;
    @ColumnInfo
    private LocalDate datewin;

    @ColumnInfo
    private long idAwardCustomer;

    public Award(){}

    public Award(long idAward, LocalDate datewin, long idAwardCustomer) {
        this.idAward = idAward;
        this.datewin = datewin;
        this.idAwardCustomer = idAwardCustomer;
    }

    public long getIdAward() {
        return idAward;
    }

    public void setIdAward(long idAward) {
        this.idAward = idAward;
    }

    public LocalDate getDatewin() {
        return datewin;
    }

    public void setDatewin(LocalDate datewin) {
        this.datewin = datewin;
    }

    public long getIdAwardCustomer() {
        return idAwardCustomer;
    }

    public void setIdAwardCustomer(long idAwardCustomer) {
        this.idAwardCustomer = idAwardCustomer;
    }
}
