package com.example.pokedex_tp.API;

import com.example.pokedex_tp.Model.Evolution;
import com.example.pokedex_tp.Model.Pokemon;
import com.example.pokedex_tp.Model.PokemonDes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EvolutionService {
    public static final String ENDPOINT = "https://pokeapi.glitch.me/v1/";

    @GET("pokemon/{id}")
    Call<List<Evolution>> getEvoFamily(@Path("id") String id);


}
