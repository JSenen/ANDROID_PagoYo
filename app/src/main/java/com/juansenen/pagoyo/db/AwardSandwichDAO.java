package com.juansenen.pagoyo.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.juansenen.pagoyo.domain.Award;
import com.juansenen.pagoyo.domain.AwardSandwich;

import java.util.List;

@Dao
public interface AwardSandwichDAO {
    @Query("SELECT * FROM award_sandwinch")
    List<AwardSandwich> getAll();

    @Query("SELECT datewin FROM award_sandwinch WHERE idAwardCustomer =:idcustomer")
    long searchDate(long idcustomer);

    @Query("DELETE FROM award_sandwinch WHERE idAwardCustomer = :idCustomer")
    void deleteByPosition(long idCustomer);

    @Insert
    void insert (AwardSandwich awardSandwich);
}
