package com.example.pokedex_tp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.pokedex_tp.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Poke_Stat extends Fragment {

    int id = 0;
    String nom = "";
    int pv = 0;
    int atk = 0;
    int atkspe = 0;
    int def = 0;
    int defspe = 0;
    int vit = 0;

    public Poke_Stat() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stats_layout, container, false);

        //Récupération des données
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            nom = getArguments().getString("nom");
            pv = getArguments().getInt("pv");
            atk = getArguments().getInt("atk");
            atkspe = getArguments().getInt("atkspe");
            def = getArguments().getInt("def");
            defspe = getArguments().getInt("defspe");
            vit = getArguments().getInt("vit");
        }


        //Affichage nom et image
        ImageView urlImageView = (ImageView) view.findViewById(R.id.imageView);
        String url = "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
        Picasso.get().load(url).placeholder(R.drawable.pngwing_com).into(urlImageView);

        TextView StringNom = (TextView) view.findViewById(R.id.textNom);
        StringNom.setText(nom);

        //Création de la chart
        HorizontalBarChart chart = (HorizontalBarChart) view.findViewById(R.id.PokemonStat);

        BarData data = new BarData(
                getDataSet(Color.rgb(71,209,71),6,pv,"point de vie","PV"),
                getDataSet(Color.rgb(65,105,225),5,atk,"attaque","ATK"),
                getDataSet(Color.rgb(138,43,228),4,atkspe,"attaque spéciale","ATKSPE"),
                getDataSet(Color.rgb(205,92,92),3,def,"défense","DEF"),
                getDataSet(Color.rgb(225,165,0),2,defspe,"défense spéciale","DEFSPE"),
                getDataSet(Color.rgb(255,215,0),1,vit,"vitesse","VIT"));

        chart.setData(data);
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getAxisLeft().setAxisMaximum(300);
        chart.getAxisRight().setAxisMinimum(0);
        chart.getAxisRight().setAxisMaximum(300);
        chart.getXAxis().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getDescription().setEnabled(false);
        //chart.setVisibleXRange(0,3); // sets the viewport to show 3 bars
        chart.animateXY(2000, 2000);
        chart.invalidate();


        return view;
    }

    private BarDataSet getDataSet(int color, float position, float value, String entryLabel, String datasetLabel) {
        ArrayList<BarEntry> entries = new ArrayList();
        entries.add(new BarEntry(position, value,entryLabel));

        BarDataSet dataset = new BarDataSet(entries,datasetLabel);
        dataset.setColor(color);
        return dataset;
    }


}
