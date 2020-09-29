package com.example.td3_dice_roller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
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

                EditText face = (EditText) findViewById(R.id.face); // Récupération de l'editView contenant le nombre de face
                String nbrf = face.getText().toString(); // Récupération de la châine de caractère de l'EditText
                int nb;
                try {
                    nb = Integer.parseInt(nbrf); // Transformation de la chaîne de caractères en nombre (Integer) si pas d'erreur
                }

                catch (NumberFormatException e){
                    nb = 0; // Set à 0 si erreur présente
                }

                if (nb > 0) {
                    int rnd = (int) (Math.random() * nb + 1); //Génération d'un nombre aléatoire
                    int rnd2 = (int) (Math.random() * nb + 1); //Génération d'un deuxième nombre aléatoire

                    TextView resultat = (TextView) findViewById(R.id.resultat); // Récupération de l'EditView
                    resultat.setText("" + rnd); //Affectation du nombre généré à l'EditView

                    TextView resultat2 = (TextView) findViewById(R.id.resultat2); // Récupération de l'EditView
                    resultat2.setText("" + rnd2); //Affectation du deuxième nombre généré à l'EditView

                    Toast toast = Toast.makeText(MainActivity.this, "Dice has been roll!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else{
                    Toast toast = Toast.makeText(MainActivity.this, "0 n'est pas un nombre de face", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}