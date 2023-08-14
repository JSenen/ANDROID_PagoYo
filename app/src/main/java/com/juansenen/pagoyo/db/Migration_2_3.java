package com.juansenen.pagoyo.db;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration_2_3 extends Migration {

    public Migration_2_3(){
        super(2,3);
    }
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {

        database.execSQL("ALTER TABLE customer ADD COLUMN numbercoffes INTEGER");
    }
}
