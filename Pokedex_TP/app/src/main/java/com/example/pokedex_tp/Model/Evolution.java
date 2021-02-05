package com.example.pokedex_tp.Model;

public class Evolution {
    private family family;

    public Evolution(family family) {
        this.family = family;
    }

    public family getFamille() {
        return family;
    }

    public void setFamille(family family) {
        this.family = family;
    }
}
