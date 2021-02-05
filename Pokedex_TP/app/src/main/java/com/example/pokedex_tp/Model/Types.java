package com.example.pokedex_tp.Model;

public class Types {
    private int slot;
    private Type type;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Types(int slot, Type type) {
        this.slot = slot;
        this.type = type;
    }
}
