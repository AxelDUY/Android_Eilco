package com.example.pokedex_tp.Adaptateur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_tp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokeImAdaptateur extends RecyclerView.Adapter<PokeImAdaptateur.ViewHolder>  {

    private List<Integer> nbpokemon;
    private Context Context;



    public PokeImAdaptateur(List<Integer> mnbpokemon, Context mcontext) { this.nbpokemon = mnbpokemon; this.Context = mcontext; }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View pokeView = inflater.inflate(R.layout.pokedex_image,parent,false);

        return new PokeImAdaptateur.ViewHolder(pokeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int md = nbpokemon.get(position);

        ImageView urlImageView = holder.urlImageView;
        String url = "https://pokeres.bastionbot.org/images/pokemon/" + md + ".png";
        Picasso.get().load(url).placeholder(R.drawable.pngwing_com).into(urlImageView);

    }




    @Override
    public int getItemCount() {
        return nbpokemon.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView urlImageView;


        public ViewHolder(View itemView){
            super(itemView);

            urlImageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}
