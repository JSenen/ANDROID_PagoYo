package com.juansenen.pagoyo.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "award_sandwinch")
public class AwardSandwich {

    @PrimaryKey(autoGenerate = true)
    private long idAwardSandwich;
    @ColumnInfo
    private LocalDate datewin;

    @ColumnInfo
    private long idAwardCustomer;

    public AwardSandwich(){}

    public AwardSandwich(long idAwardSandwich, LocalDate datewin, long idAwardCustomer) {
        this.idAwardSandwich = idAwardSandwich;
        this.datewin = datewin;
        this.idAwardCustomer = idAwardCustomer;
    }

    public long getIdAwardSandwich() {
        return idAwardSandwich;
    }

    public void setIdAwardSandwich(long idAwardSandwich) {
        this.idAwardSandwich = idAwardSandwich;
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
