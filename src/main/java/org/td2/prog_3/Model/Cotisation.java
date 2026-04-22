package org.td2.prog_3.Model;

import java.time.LocalDate;

public class Cotisation {

    private Long id;
    private Double montant;
    private LocalDate date;
    private Long membreId;
    private Long collectiviteId;
    private String type;
    private String modePaiement;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }
    public Long getCollectiviteId() { return collectiviteId; }
    public void setCollectiviteId(Long collectiviteId) { this.collectiviteId = collectiviteId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getModePaiement() { return modePaiement; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
}
