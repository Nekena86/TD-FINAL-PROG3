package org.td2.prog_3.Model;

import java.util.HashMap;
import java.util.Map;

public class FinancialAccount {
    private Long id;
    private String type;
    private String libelle;
    private Double solde;
    private Long collectivityId;
    private String titulaire;
    private String banque;
    private String numeroCompte;
    private String service;
    private String numeroTelephone;

    public FinancialAccount() {
        this.solde = 0.0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Double getSolde() { return solde; }
    public void setSolde(Double solde) { this.solde = solde; }

    public Long getCollectivityId() { return collectivityId; }
    public void setCollectivityId(Long collectivityId) { this.collectivityId = collectivityId; }

    public String getTitulaire() { return titulaire; }
    public void setTitulaire(String titulaire) { this.titulaire = titulaire; }

    public String getBanque() { return banque; }
    public void setBanque(String banque) { this.banque = banque; }

    public String getNumeroCompte() { return numeroCompte; }
    public void setNumeroCompte(String numeroCompte) { this.numeroCompte = numeroCompte; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getNumeroTelephone() { return numeroTelephone; }
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("type", this.type);
        map.put("libelle", this.libelle);
        map.put("solde", this.solde);

        Map<String, Object> infos = new HashMap<>();
        if (this.titulaire != null) infos.put("titulaire", this.titulaire);
        if (this.banque != null) infos.put("banque", this.banque);
        if (this.numeroCompte != null) infos.put("numeroCompte", this.numeroCompte);
        if (this.service != null) infos.put("service", this.service);
        if (this.numeroTelephone != null) infos.put("numeroTelephone", this.numeroTelephone);

        if (!infos.isEmpty()) {
            map.put("informations", infos);
        }

        return map;
    }
}
