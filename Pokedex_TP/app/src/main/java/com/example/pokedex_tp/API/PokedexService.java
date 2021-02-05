package com.example.pokedex_tp.API;

import com.example.pokedex_tp.Model.Pokemon;
import com.example.pokedex_tp.Model.PokemonDes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import  io.reactivex.rxjava3.core.Observable;

public interface PokedexService {
        public static final String ENDPOINT = "https://pokeapi.co/api/v2/";

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") String id);

    @GET("pokemon-species/{id}")
    Call<PokemonDes> getPokemonDescription(@Path("id") String pokemonNumber);

}
