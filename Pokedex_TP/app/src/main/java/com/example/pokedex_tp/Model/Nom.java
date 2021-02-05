package com.example.pokedex_tp.Model;

public class Nom {
    private Language language;
    private String name;

    public Nom(Language language, String name) {
        this.language = language;
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
