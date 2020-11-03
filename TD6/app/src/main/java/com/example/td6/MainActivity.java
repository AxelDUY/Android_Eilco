package com.example.td6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitHubService githubService = new Retrofit.Builder()
                .baseUrl(GitHubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);

        Button SearchButton = (Button) findViewById(R.id.B_Search);
        SearchButton.setOnClickListener(new View.OnClickListener() {
            String test = "adrienbusin";
            @Override
            public void onClick(View view) {
                EditText Repos = (EditText) findViewById(R.id.editTextRepos);
                String SRepos = Repos.getText().toString();
                githubService.searchRepos(SRepos).enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        afficherRepos(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        afficherF();
                    }
                });
            }
        });

    }

    public void afficherRepos(List<Repo> repos) {
        Toast.makeText(this,"nombre de dépots : "+repos.size(), Toast.LENGTH_SHORT).show();
    }

    public void afficherF() {
        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show();
    }

}

