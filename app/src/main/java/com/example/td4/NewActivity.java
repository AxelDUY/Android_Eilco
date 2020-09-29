package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        setTitle(getLocalClassName());

        Intent intent = getIntent();
        String login;
        if (intent.hasExtra("login")) {
            login = intent.getStringExtra("login");
            TextView pseudo = (TextView) findViewById(R.id.pseudo);
            pseudo.setText("Bonjour " + login);
        }

        Button LogoutButton = (Button) findViewById(R.id.button_logout);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity.this, LoginActivity.class);
                NewActivity.this.startActivity(intent);
                finish();
            }
        });

        Button DetailsButton = (Button) findViewById(R.id.button_detail);
        DetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity.this, DetailsActivity.class);
                NewActivity.this.startActivity(intent);
            }
        });

        Button WebButton = (Button) findViewById(R.id.button_web);
        WebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://news.google.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
