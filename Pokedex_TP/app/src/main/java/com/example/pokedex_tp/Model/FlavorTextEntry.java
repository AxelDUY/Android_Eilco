package com.example.pokedex_tp.Model;

public class FlavorTextEntry {

    private String flavor_text;
    private Language language;
    private Version version;

    public String getFlavor_text() {
        return flavor_text;
    }

    public void setFlavor_text(String flavor_text) {
        this.flavor_text = flavor_text;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }


    public FlavorTextEntry(String flavorText, Language language, Version version) {
        this.flavor_text = flavorText;
        this.language = language;
        this.version = version;
    }
}

