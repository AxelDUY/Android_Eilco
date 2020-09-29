package com.example.td3_dice_roller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rollButton = (Button) findViewById(R.id.bouton);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rnd = (int)(Math.random()*6); //Génération d'un nombre aléatoire

                TextView resultat = (TextView) findViewById(R.id.resultat); // Récupération de l'EditView
                resultat.setText(""+rnd); //Affectation de nombre généré à l'EditView

                Toast toast = Toast.makeText(MainActivity.this, "Dice has been roll!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}