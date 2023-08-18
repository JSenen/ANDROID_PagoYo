package com.juansenen.pagoyo.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.juansenen.pagoyo.domain.Award;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface AwardDAO {

    @Query("SELECT * FROM award")
    List<Award> getAll();

    @Query("SELECT datewin FROM award WHERE idAwardCustomer =:idcustomer")
    long searchDate(long idcustomer);

    @Query("DELETE FROM award WHERE idAwardCustomer = :idCustomer")
    void deleteByPosition(long idCustomer);

    @Insert
    void insert (Award award);
}
