package com.example.pokedex_tp.Model;

import java.util.List;

public class family {
    private int id;
    private int evolutionStage;
    private List<String> evolutionLine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvolution_stage() {
        return evolutionStage;
    }

    public void setEvolution_stage(int evolution_stage) {
        this.evolutionStage = evolution_stage;
    }

    public List<String> getEvolution_line() {
        return evolutionLine;
    }

    public void setEvolution_line(List<String> evolution_line) {
        this.evolutionLine = evolution_line;
    }
}
