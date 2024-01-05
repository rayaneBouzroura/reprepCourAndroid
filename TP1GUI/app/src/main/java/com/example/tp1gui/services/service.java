package com.example.tp1gui.services;

import com.example.tp1gui.Tache;

import java.util.Date;

public class service {

    //methode qui cree tache
    public Tache nouvelleTache(String nom, Date dateEcheance){
        Tache t = new Tache();
        t.nom = nom;
        t.dateLimite = dateEcheance;
        return t;
    }

    //methode qui change le pourcentage de la tache
    public void changerPourcentage(Tache t, Integer pourcentage){
        t.pourcentage = pourcentage;
    }

    //oft inutile for now , keep when using backend later

}
