package com.example.td6;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    public static final String ENDPOINT = "https://api.github.com";

    @GET("/search/repositories")
    Call<List<Repo>> searchRepos(@Query("q") String repos);

}
