package com.example.td5;

public class Contact{
    private String nom;
    private String prenom;
    private String URL;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Contact(String nom, String prenom, String URL) {
        this.nom = nom;
        this.prenom = prenom;
        this.URL = URL;
    }


}

