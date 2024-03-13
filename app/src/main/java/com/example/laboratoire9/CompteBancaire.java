package com.example.laboratoire9;

public class CompteBancaire {
    private String numero;
    private String titulaire;
    private int solde;

    public CompteBancaire(String numero, String titulaire, int solde) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
    }

    public CompteBancaire(String numero, String titulaire) {
        this(numero, titulaire, 75);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
}
