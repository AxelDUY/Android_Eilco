package com.example.pokedex_tp.Adaptateur;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_tp.API.PokedexService;
import com.example.pokedex_tp.Model.Pokemon;
import com.example.pokedex_tp.PokemonInfo;
import com.example.pokedex_tp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EvoImAdaptateur extends RecyclerView.Adapter<EvoImAdaptateur.ViewHolder>  {

    private List<String> evo;
    private Context Context;
    int id = 0;



    public EvoImAdaptateur(List<String> evo, Context mcontext) { this.evo = evo; this.Context = mcontext; }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View pokeView = inflater.inflate(R.layout.pokedex_image,parent,false);

        return new EvoImAdaptateur.ViewHolder(pokeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String md = evo.get(position);

        final PokedexService Pokedexservice = new Retrofit.Builder()
                .baseUrl(PokedexService.ENDPOINT)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokedexService.class);

        Pokedexservice.getPokemonById(md).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                id = response.body().getId();
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
            }
        });


        ImageView urlImageView = holder.urlImageView;
        String url = "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
        Picasso.get().load(url).placeholder(R.drawable.pngwing_com).into(urlImageView);

    }




    @Override
    public int getItemCount() {
        return evo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView urlImageView;


        public ViewHolder(View itemView){
            super(itemView);

            urlImageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}
