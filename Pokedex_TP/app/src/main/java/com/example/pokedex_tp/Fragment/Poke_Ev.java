package com.example.pokedex_tp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_tp.Adaptateur.EvoImAdaptateur;
import com.example.pokedex_tp.Adaptateur.PokeImAdaptateur;
import com.example.pokedex_tp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Poke_Ev extends Fragment {

    int id = 0;
    String nom = "";
    String evo = "";
    int nbrevo = 0;
    String affEvo = "";



    public Poke_Ev() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.evolution_layout, container, false);

        //Récupération des données
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            nom = getArguments().getString("nom");
            evo = getArguments().getString("listevo");

        }



        //Affichage des données
        ImageView urlImageView = (ImageView) view.findViewById(R.id.imageView);
        String url = "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
        Picasso.get().load(url).placeholder(R.drawable.pngwing_com).into(urlImageView);

        TextView StringNom = (TextView) view.findViewById(R.id.textNom);
        StringNom.setText(nom);

        String[] listEvo = evo.split(",");
        nbrevo = listEvo.length;

        List<String> Liste = new ArrayList<String>();

        for (int e =1; e < nbrevo;e++){
            affEvo = affEvo + listEvo[e] + " - ";
            Liste.add(listEvo[e].toLowerCase());
        }

        TextView StringEv = (TextView) view.findViewById(R.id.Evo);
        StringEv.setText(affEvo);

        //test d'affichage des évolutions (voir adaptater)
        RecyclerView rvEvoPokemon = (RecyclerView) view.findViewById(R.id.rvEvoPokemon);
        rvEvoPokemon.setHasFixedSize(true);
        EvoImAdaptateur adaptaterIm = new EvoImAdaptateur(Liste, view.getContext());
        rvEvoPokemon.setAdapter(adaptaterIm);
        rvEvoPokemon.setLayoutManager(new GridLayoutManager(view.getContext(), 3));


        return view;
    }


}
