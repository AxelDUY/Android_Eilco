package com.example.pokedex_tp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.pokedex_tp.Adaptateur.InfoAdaptateur;
import com.example.pokedex_tp.Data.AppDatabase;
import com.example.pokedex_tp.Data.PokeDb;
import com.google.android.material.tabs.TabLayout;

public class PokemonInfo extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    int id = 0;
    String nom = "";
    String description = "";
    String coul = "";
    double taille = 0;
    double poids = 0;
    String type1 = "";
    String type2 = "";
    int posevo;
    String listevo = "";
    int pv;
    int atk;
    int atkspe;
    int def;
    int defspe;
    int vit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        //Récupération des données envoyées par l'activité précédante
        Intent intent = getIntent();

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        PokeDb pokeDb = new PokeDb();

        if (intent.hasExtra("pok_id")) {
            id = intent.getIntExtra("pok_id",1);
        }

        //Si pokemon pas encore enregistré
        if (intent.hasExtra("name")) {
            nom = intent.getStringExtra("name");
            description = intent.getStringExtra("description");
            coul = intent.getStringExtra("coul");
            taille = intent.getIntExtra("taille",1);
            poids = intent.getIntExtra("poids",1);
            type1 = intent.getStringExtra("type1");
            if (intent.hasExtra("type2")) {
                type2 = intent.getStringExtra("type2");
            }
            posevo = intent.getIntExtra("posEvo",1);
            listevo = intent.getStringExtra("listevo");
            pv = intent.getIntExtra("pv",1);
            atk = intent.getIntExtra("atk",1);
            atkspe = intent.getIntExtra("atkspe",1);
            def = intent.getIntExtra("def",1);
            defspe = intent.getIntExtra("defspe",1);
            vit = intent.getIntExtra("vit",1);

        }

        //Si pokemon déja enregistré récupération via BDD
        else{
            pokeDb = db.pokeDAO().findPokemon(id);
            nom = pokeDb.name;
            description = pokeDb.description;
            coul = pokeDb.couleur;
            taille = pokeDb.height;
            poids = pokeDb.weight;
            type1 = pokeDb.type1;
            if (!TextUtils.isEmpty(pokeDb.type2)){
                type2 = pokeDb.type2;
            }
            posevo = pokeDb.posEvo;
            listevo = pokeDb.listEvo;
            pv = pokeDb.pv;
            atk = pokeDb.atk;
            atkspe = pokeDb.atkspe;
            def = pokeDb.def;
            defspe = pokeDb.defspe;
            vit = pokeDb.vit;

        }

        //Envoi des données aux framgents
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("nom",nom);
        bundle.putString("des",description);
        bundle.putString("coul",coul);
        bundle.putDouble("taille",taille);
        bundle.putDouble("poids",poids);
        bundle.putString("type1", type1);
        bundle.putString("type2", type2);
        bundle.putInt("posevo", posevo);
        bundle.putString("listevo", listevo);
        bundle.putInt("pv", pv);
        bundle.putInt("atk", atk);
        bundle.putInt("atkspe", atkspe);
        bundle.putInt("def", def);
        bundle.putInt("defspe", defspe);
        bundle.putInt("vit", vit);



        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.ViewPager);


        //Initialisation du tabnav des détails
        final InfoAdaptateur adaptateur = new InfoAdaptateur(this,getSupportFragmentManager(), tabLayout.getTabCount(),bundle);
        viewPager.setAdapter(adaptateur);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
