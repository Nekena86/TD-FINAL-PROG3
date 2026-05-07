package org.td2.exam.Model;

import java.time.LocalDate;

public class Collectivity {
    private String id;
    private String numero;
    private String nom;
    private String localite;
    private String specialisation;
    private LocalDate dateCreation;

    public Collectivity() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getLocalite() { return localite; }
    public void setLocalite(String localite) { this.localite = localite; }
    public String getSpecialisation() { return specialisation; }
    public void setSpecialisation(String specialisation) { this.specialisation = specialisation; }
    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }
}