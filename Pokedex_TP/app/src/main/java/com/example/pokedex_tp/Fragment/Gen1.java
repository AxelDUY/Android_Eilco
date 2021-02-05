package com.example.pokedex_tp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_tp.API.PokedexService;
import com.example.pokedex_tp.Adaptateur.PokeImAdaptateur;
import com.example.pokedex_tp.Data.AppDatabase;
import com.example.pokedex_tp.Data.PokeDb;
import com.example.pokedex_tp.Interface.RecyclerItemClickListener;
import com.example.pokedex_tp.Model.Pokemon;
import com.example.pokedex_tp.PokemonInfo;
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

public class Gen1 extends Fragment {
    int gen = 1;
    int prem_poke = 1;
    int der_poke = 151;
    int verif = 0;
    String nom = "";

    public Gen1() {
        // Required empty public constructor
    }

    public void EnregistrerPokemon(Pokemon Pokemon, int i) {
        AppDatabase db = AppDatabase.getDbInstance(this.getActivity().getApplicationContext());
        PokeDb pokeDb = new PokeDb();
        String nom = db.pokeDAO().findPoke(i);
        Toast.makeText(getContext(),"Enregistrement de  : "+ Pokemon.getName() +"   " + nom , Toast.LENGTH_SHORT).show();
        Log.d("test",i + " id");

        pokeDb.id = i;
        pokeDb.name = Pokemon.getName().toString();
        pokeDb.height = Pokemon.getHeight();
        pokeDb.weight = Pokemon.getWeight();
        //pokeDb.type = Pokemon.getType();

        if (Pokemon.getName().equals(nom)) {
            Toast.makeText(getContext(),"déja enregistrer",Toast.LENGTH_SHORT).show();
        }
        else{
            if (TextUtils.isEmpty(nom)){
                db.pokeDAO().insertPoke(pokeDb);
                Log.d("ok","insert");
            }

            else {
                db.pokeDAO().updatePoke(pokeDb);
                Log.d("ok","update");
            }
            verif = 1;

        }
    }

    public void afficherF() {

        Toast.makeText(getContext(),"Failed", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_gen, container, false);

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
                        verif = 0;
                        PokeDb pokeverif = new PokeDb();
                        pokeverif = db.pokeDAO().findPokemon(id);
                        Log.d("for", pokeverif.id + "pokemon");
                        //faire verif bd ici

                        Pokedexservice.getPokemonById(Integer.toString(id)).enqueue(new Callback<Pokemon>() {
                            @Override
                            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                                EnregistrerPokemon(response.body(),id);
                                nom = response.body().getName();
                                Log.d("for", verif + " verif");
                                Log.d("for", nom + " nom");

                                Intent intent = new Intent(view.getContext(), PokemonInfo.class);
                                intent.putExtra("pok_id", id);
                                if (verif == 1){
                                    intent.putExtra("name", nom);
                                }
                                view.getContext().startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Pokemon> call, Throwable t) {
                                afficherF();
                            }
                        });


                    }
                })
        );
        rvPokemon.setHasFixedSize(true);

        List<Integer> test = IntStream.rangeClosed(prem_poke,der_poke).boxed().collect(Collectors.toList());
        PokeImAdaptateur adaptaterIm = new PokeImAdaptateur(test, view.getContext());
        rvPokemon.setAdapter(adaptaterIm);
        rvPokemon.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        Log.d("for", test.size() + " id");

        return view;
    }


}
