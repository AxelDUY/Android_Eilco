package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getLocalClassName());

        Button LogButton = (Button) findViewById(R.id.button_login);
        LogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nom_C = (EditText) findViewById(R.id.Nom_compte);
                String compte = nom_C.getText().toString();

                Intent intent = new Intent(LoginActivity.this, NewActivity.class);
                intent.putExtra("login", compte);

                NewsListApplication app = (NewsListApplication) getApplicationContext();
                app.setLogin(compte);

                LoginActivity.this.startActivity(intent);
                finish();
            }

        });

    }
}