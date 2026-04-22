package org.td2.prog_3.Model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Member {

    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String genre;
    private String adresse;
    private String metier;
    private String telephone;
    private String email;
    private LocalDate dateAdhesion;
    private String poste;
    private Long collectivityId;
    private Integer ancienneteFederationJours;
    private String statut;

    public Member() {
        this.statut = "ACTIF";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(LocalDate dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Long getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(Long collectivityId) {
        this.collectivityId = collectivityId;
    }

    public Integer getAncienneteFederationJours() {
        return ancienneteFederationJours;
    }

    public void setAncienneteFederationJours(Integer ancienneteFederationJours) {
        this.ancienneteFederationJours = ancienneteFederationJours;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("nom", this.nom);
        map.put("prenom", this.prenom);
        map.put("email", this.email);
        map.put("telephone", this.telephone);
        map.put("poste", this.poste);
        map.put("dateAdhesion", this.dateAdhesion != null ? this.dateAdhesion.toString() : null);
        map.put("collectivityId", this.collectivityId);
        map.put("statut", this.statut);
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}