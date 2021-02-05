package com.example.pokedex_tp.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface PokeDAO {

    @Query("SELECT * FROM POKEDB")
    List<PokeDb> getAllPoke();

    @Insert
    void insertPoke(PokeDb... pokes);

    @Update
    void updatePoke(PokeDb... pokes);

    @Query("SELECT nom FROM POKEDB WHERE id = :search")
    public String findPoke(int search);

    @Query("SELECT * FROM POKEDB WHERE id = :search")
    public PokeDb findPokemon(int search);

}
