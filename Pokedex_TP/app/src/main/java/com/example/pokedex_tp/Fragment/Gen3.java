package com.example.pokedex_tp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_tp.API.PokedexService;
import com.example.pokedex_tp.Adaptateur.PokeImAdaptateur;
import com.example.pokedex_tp.Data.AppDatabase;
import com.example.pokedex_tp.Interface.RecyclerItemClickListener;
import com.example.pokedex_tp.Model.Pokemon;
import com.example.pokedex_tp.R;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Gen3 extends Fragment {
    int gen = 2;
    int prem_poke = 152;
    int der_poke = 153;

    public Gen3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_gen, container, false);

        List<Integer> test = IntStream.rangeClosed(prem_poke,der_poke).boxed().collect(Collectors.toList());
        final PokedexService Pokedexservice = new Retrofit.Builder()
                .baseUrl(PokedexService.ENDPOINT)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokedexService.class);

        AppDatabase db = AppDatabase.getDbInstance(view.getContext());

        RecyclerView rvPokemon = (RecyclerView) view.findViewById(R.id.rvPokemon);

        rvPokemon.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), rvPokemon ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int id = prem_poke + position ;

                        Pokedexservice.getPokemonById(Integer.toString(id)).enqueue(new Callback<Pokemon>() {
                            @Override
                            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                                //EnregistrerPokemon(response.body(),id);
                            }

                            @Override
                            public void onFailure(Call<Pokemon> call, Throwable t) {
                                //afficherF();
                            }
                        });
                        //Intent intent = new Intent(view.getContext().this, Pokemon_details.class);
                        //intent.putExtra("pok_id", id);
                        //Pokedex_Gen1.this.startActivity(intent);
                    }
                })
        );
        rvPokemon.setHasFixedSize(true);

        //PokeAdapteur adaptater = new PokeAdapteur(PokeList, this);
        PokeImAdaptateur adaptaterIm = new PokeImAdaptateur(test, view.getContext());
        rvPokemon.setAdapter(adaptaterIm);
        rvPokemon.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        Log.d("for", test.size() + " id");

        return view;
    }
}
