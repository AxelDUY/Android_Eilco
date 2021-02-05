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

import com.example.pokedex_tp.API.EvolutionService;
import com.example.pokedex_tp.API.PokedexService;
import com.example.pokedex_tp.Adaptateur.PokeImAdaptateur;
import com.example.pokedex_tp.Data.AppDatabase;
import com.example.pokedex_tp.Data.PokeDb;
import com.example.pokedex_tp.Interface.RecyclerItemClickListener;
import com.example.pokedex_tp.Model.Evolution;
import com.example.pokedex_tp.Model.Pokemon;
import com.example.pokedex_tp.Model.PokemonDes;
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

public class Gen2 extends Fragment {
    //Valeurs par défaut
    int gen = 2;
    int prem_poke = 152;
    int der_poke = 251;
    int verif = 0;
    String nom = "";
    String nompoke = "";
    String description = "";
    String couleur = "";
    int posEvo = 0;
    int nbrEvo = 0;

    public Gen2() { }

    public void EnregistrerPokemon(Pokemon Pokemon, int i,String nomfr, String couleur, String description,int posEvo, int nbrEvo) {

        AppDatabase db = AppDatabase.getDbInstance(this.getActivity().getApplicationContext());
        PokeDb pokeDb = new PokeDb();

        String nom = db.pokeDAO().findPoke(i);

        pokeDb.id = i;
        pokeDb.name = nomfr;
        pokeDb.description = description;
        pokeDb.couleur = couleur;
        pokeDb.height = Pokemon.getHeight();
        pokeDb.weight = Pokemon.getWeight();
        pokeDb.type1 = Pokemon.getType().get(0).getType().getName();
        if (Pokemon.getType().size() == 2){
            pokeDb.type2 = Pokemon.getType().get(1).getType().getName();
        }
        pokeDb.pv = Pokemon.getStats().get(0).getBase_stat();
        pokeDb.atk = Pokemon.getStats().get(1).getBase_stat();
        pokeDb.def = Pokemon.getStats().get(2).getBase_stat();
        pokeDb.atkspe = Pokemon.getStats().get(3).getBase_stat();
        pokeDb.defspe= Pokemon.getStats().get(4).getBase_stat();
        pokeDb.vit = Pokemon.getStats().get(5).getBase_stat();
        pokeDb.posEvo = posEvo;
        pokeDb.nbrEvo = nbrEvo;

        if (TextUtils.isEmpty(nom)){
            db.pokeDAO().insertPoke(pokeDb);
            Log.d("ok","insert");
        }

        else {
            db.pokeDAO().updatePoke(pokeDb);
            Log.d("ok","update");
        }

    }

    public void afficherF() {
        Toast.makeText(getContext(),"Failed", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_gen, container, false);

        //Appel API pour récupérer les données d'un pokemon
        final PokedexService Pokedexservice = new Retrofit.Builder()
                .baseUrl(PokedexService.ENDPOINT)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokedexService.class);

        //Appel Api pour les récupérer les évolutions
        final EvolutionService EvolutionServices = new Retrofit.Builder()
                .baseUrl(EvolutionService.ENDPOINT)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EvolutionService.class);


        AppDatabase db = AppDatabase.getDbInstance(view.getContext());

        //Affichage de chaque pokemon apartenant à la génération
        RecyclerView rvPokemon = (RecyclerView) view.findViewById(R.id.rvPokemon);
        rvPokemon.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), rvPokemon ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int id = prem_poke + position ;
                        verif = 0;

                        //Vérification de l'existance d'un pokemon dans la BDD
                        try {
                            PokeDb pokeverif = new PokeDb();
                            pokeverif = db.pokeDAO().findPokemon(id);
                            nom = pokeverif.name;
                        }
                        catch (Exception e ){
                            verif = 1;
                        }

                        //Le pokemon n'existe pas dans le base de données
                        if (verif == 1){

                            //Appel pour récupérer la description et le nom du pokemon en français
                            Pokedexservice.getPokemonDescription(Integer.toString(id)).enqueue(new Callback<PokemonDes>() {
                                @Override
                                public void onResponse(Call<PokemonDes> call, Response<PokemonDes> response) {
                                    nompoke = response.body().getNames().get(4).getName();
                                    description = response.body().getFrenchText().getFlavor_text();
                                    couleur = response.body().getColor().getName();


                                    EvolutionServices.getEvoFamily(Integer.toString(id)).enqueue(new Callback<List<Evolution>>() {
                                        @Override
                                        public void onResponse(Call<List<Evolution>> call, Response<List<Evolution>> response) {
                                            posEvo = response.body().get(0).getFamille().getEvolution_stage();
                                            nbrEvo = response.body().get(0).getFamille().getEvolution_line().size();

                                            //Appel pour récupérer le type/taille/poid ainsi que l'enregistrement dans la BDD
                                            Pokedexservice.getPokemonById(Integer.toString(id)).enqueue(new Callback<Pokemon>() {
                                                @Override
                                                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                                                    EnregistrerPokemon(response.body(),id,nompoke,couleur,description,posEvo,nbrEvo);
                                                    nom = response.body().getName();

                                                    //Envoi des données dans la prochaine activité car elles n'ont pas encore été chargées dans la BDD
                                                    Intent intent = new Intent(view.getContext(), PokemonInfo.class);
                                                    intent.putExtra("pok_id", id);
                                                    intent.putExtra("name", nompoke);
                                                    intent.putExtra("description", description);
                                                    intent.putExtra("coul", couleur);
                                                    intent.putExtra("taille", response.body().getHeight());
                                                    intent.putExtra("poids", response.body().getWeight());
                                                    intent.putExtra("type1", response.body().getType().get(0).getType().getName());
                                                    if (response.body().getType().size() == 2){
                                                        intent.putExtra("type2", response.body().getType().get(1).getType().getName());
                                                    }
                                                    intent.putExtra("pv", response.body().getStats().get(0).getBase_stat());
                                                    intent.putExtra("atk", response.body().getStats().get(1).getBase_stat());
                                                    intent.putExtra("def", response.body().getStats().get(2).getBase_stat());
                                                    intent.putExtra("atkspe", response.body().getStats().get(3).getBase_stat());
                                                    intent.putExtra("defspe", response.body().getStats().get(4).getBase_stat());
                                                    intent.putExtra("vit", response.body().getStats().get(5).getBase_stat());
                                                    intent.putExtra("posEvo", posEvo);
                                                    intent.putExtra("nbrEvo", nbrEvo);

                                                    //Ouverture de la vue détails du pokemon
                                                    view.getContext().startActivity(intent);

                                                }

                                                @Override
                                                public void onFailure(Call<Pokemon> call, Throwable t) {
                                                    afficherF();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onFailure(Call<List<Evolution>> call, Throwable t) {
                                            Log.d("for", t.getCause() + " cause");
                                        }
                                    });

                                }

                                @Override
                                public void onFailure(Call<PokemonDes> call, Throwable t) {
                                }
                            });


                        }

                        //Le pokemon existe déja
                        else{
                            //Si le pokemon existe déja envoi seulement l'id
                            Intent intent = new Intent(view.getContext(), PokemonInfo.class);
                            intent.putExtra("pok_id", id);
                            view.getContext().startActivity(intent);
                        }

                    }
                })
        );

        //Affichage du recyclerView
        rvPokemon.setHasFixedSize(true);
        List<Integer> listePoke = IntStream.rangeClosed(prem_poke,der_poke).boxed().collect(Collectors.toList());
        PokeImAdaptateur adaptaterIm = new PokeImAdaptateur(listePoke, view.getContext());
        rvPokemon.setAdapter(adaptaterIm);
        rvPokemon.setLayoutManager(new GridLayoutManager(view.getContext(), 3));

        return view;
    }
}
