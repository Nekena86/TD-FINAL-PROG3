package org.td2.prog_3.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Collectivity {

    private Long id;
    private String numero;
    private String nom;
    private String ville;
    private String specialite;
    private LocalDate dateCreation;
    private Boolean autorisationFormelle;
    private String statut;
    private List<Member> membres;

    public Collectivity() {
        this.membres = new ArrayList<>();
        this.statut = "EN_ATTENTE";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getAutorisationFormelle() {
        return autorisationFormelle;
    }

    public void setAutorisationFormelle(Boolean autorisationFormelle) {
        this.autorisationFormelle = autorisationFormelle;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<Member> getMembres() {
        return membres;
    }

    public void setMembres(List<Member> membres) {
        this.membres = membres;
    }

    public boolean hasUniqueIdentifiers() {
        return this.numero != null && this.nom != null;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("numero", this.numero);
        map.put("nom", this.nom);
        map.put("ville", this.ville);
        map.put("specialite", this.specialite);
        map.put("dateCreation", this.dateCreation != null ? this.dateCreation.toString() : null);
        map.put("statut", this.statut);
        map.put("nombreMembres", this.membres != null ? this.membres.size() : 0);
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collectivity that = (Collectivity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

