package com.example.pokedex_tp.Fragment;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_tp.API.PokedexService;
import com.example.pokedex_tp.Adaptateur.PokeImAdaptateur;
import com.example.pokedex_tp.Data.AppDatabase;
import com.example.pokedex_tp.Data.PokeDb;
import com.example.pokedex_tp.Interface.RecyclerItemClickListener;
import com.example.pokedex_tp.Model.Pokemon;
import com.example.pokedex_tp.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Poke_De extends Fragment {

    int id = 0;
    String nom = "";
    String description = "";
    String coul = "";
    double taille = 0;
    double poids = 0;
    String type1 = "";
    String type2 = "";

    public Poke_De() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pokemon_layout, container, false);

        //Récupération des données
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            nom = getArguments().getString("nom");
            description = getArguments().getString("des");
            coul = getArguments().getString("coul");
            taille = getArguments().getDouble("taille");
            poids = getArguments().getDouble("poids");
            type1 = getArguments().getString("type1");
            type2 = getArguments().getString("type2");
        }


        //Tentative de changement de couleur en fonction du pokemon

        //Log.d("for", coul + " couleur");
        //LayerDrawable shapeRectangle = (LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.shape);
        //GradientDrawable gradient = (GradientDrawable) shapeRectangle.findDrawableByLayerId(R.id.oval);
        //gradient.setColor(Color.BLACK);

        //Affichage des données
        ImageView urlImageView = (ImageView) view.findViewById(R.id.imageView);
        String url = "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
        Picasso.get().load(url).placeholder(R.drawable.pngwing_com).into(urlImageView);

        TextView StringNom = (TextView) view.findViewById(R.id.textNom);
        StringNom.setText(nom);

        TextView StringDescription = (TextView) view.findViewById(R.id.textDescription);
        StringDescription.setText(description);

        TextView StringTaille = (TextView) view.findViewById(R.id.textTaille);
        StringTaille.setText("Taille : " + taille/10 + " m");

        TextView StringPoids = (TextView) view.findViewById(R.id.textPoids);
        StringPoids.setText("Poids : " + poids/10 + " kg");

        TextView StringType = (TextView) view.findViewById(R.id.textType);
        StringType.setText("Type : " + type1 + " - " + type2);


        return view;
    }


}
