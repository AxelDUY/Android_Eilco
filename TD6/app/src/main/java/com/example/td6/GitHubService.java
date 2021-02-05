package com.example.td6;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    public static final String ENDPOINT = "https://api.github.com";

    @GET("/search/repositories")
    Call<List<Repo>> searchRepos(@Query("q") String repos);

    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Observable<List<Repo>>

}
