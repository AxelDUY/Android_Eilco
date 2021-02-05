package com.example.pokedex_tp.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PokeDb.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase{

    public abstract PokeDAO pokeDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_Poke")
                       .allowMainThreadQueries()
                       .build();

        }
        return INSTANCE;
    }
}
