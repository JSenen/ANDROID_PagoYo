package com.juansenen.pagoyo.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.juansenen.pagoyo.domain.Award;

import java.util.List;

@Dao
public interface AwardDAO {

    @Query("SELECT * FROM award")
    List<Award> getAll();

    @Insert
    void insert (Award award);
}
