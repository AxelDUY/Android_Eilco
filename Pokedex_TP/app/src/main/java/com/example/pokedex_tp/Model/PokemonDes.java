package com.example.pokedex_tp.Model;

import java.util.List;

public class PokemonDes {
    private Color color;
    private List<FlavorTextEntry> flavor_text_entries = null;
    private List<Nom> names = null;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PokemonDes(Color color, List<FlavorTextEntry> flavor_text_entries, List<Nom> name) {
        this.color = color;
        this.flavor_text_entries = flavor_text_entries;
        this.names = names;
    }


    public List<FlavorTextEntry> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(List<FlavorTextEntry> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }


    public List<Nom> getNames() {
        return names;
    }

    public void setNames(List<Nom> names) {
        this.names = names;
    }

    public FlavorTextEntry getFrenchText(){
        FlavorTextEntry def = this.flavor_text_entries.get(0);//retourne le premier par défaut
        for(int i = 0; i< this.flavor_text_entries.size(); i++){
            if(this.flavor_text_entries.get(i).getLanguage().getName().equals("fr")){
                def = this.flavor_text_entries.get(i);
                i = this.flavor_text_entries.size();
            }
        }
        return def;
    }
}
