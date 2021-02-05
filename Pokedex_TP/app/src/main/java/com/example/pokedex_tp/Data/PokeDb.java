package com.example.pokedex_tp.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pokedex_tp.Model.Types;
import java.util.List;


@Entity
public class PokeDb {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nom")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "couleur")
    public String couleur;

    @ColumnInfo(name = "taille")
    public int height;

    @ColumnInfo(name = "poid")
    public int weight;

    @ColumnInfo(name = "type1")
    public String type1;

    @ColumnInfo(name = "pv")
    public int pv;

    @ColumnInfo(name = "atk")
    public int atk;

    @ColumnInfo(name = "atkspe")
    public int atkspe;

    @ColumnInfo(name = "def")
    public int def;

    @ColumnInfo(name = "defspe")
    public int defspe;

    @ColumnInfo(name = "vit")
    public int vit;

    @ColumnInfo(name = "type2")
    public String type2;


    @ColumnInfo(name = "positionEvo")
    public int posEvo;

    @ColumnInfo(name = "nbrEvo")
    public int nbrEvo;
}
