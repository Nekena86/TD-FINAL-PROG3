package org.td2.exam.Model;

import java.time.LocalDate;

public class MembershipFee {
    private String id;
    private String collectivityId;
    private String label;
    private String statut;
    private String frequence;
    private LocalDate eligibleDepuis;
    private int montant;

    public MembershipFee() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getFrequence() { return frequence; }
    public void setFrequence(String frequence) { this.frequence = frequence; }
    public LocalDate getEligibleDepuis() { return eligibleDepuis; }
    public void setEligibleDepuis(LocalDate eligibleDepuis) { this.eligibleDepuis = eligibleDepuis; }
    public int getMontant() { return montant; }
    public void setMontant(int montant) { this.montant = montant; }
}