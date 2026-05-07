package org.td2.exam.Model;

import java.time.LocalDate;

public class Transaction {
    private String collectivityId;
    private String memberDebiteId;
    private int montant;
    private String compteCrediteId;
    private String moyenPaiement;
    private LocalDate dateCreation;

    public Transaction() {}

    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
    public String getMemberDebiteId() { return memberDebiteId; }
    public void setMemberDebiteId(String memberDebiteId) { this.memberDebiteId = memberDebiteId; }
    public int getMontant() { return montant; }
    public void setMontant(int montant) { this.montant = montant; }
    public String getCompteCrediteId() { return compteCrediteId; }
    public void setCompteCrediteId(String compteCrediteId) { this.compteCrediteId = compteCrediteId; }
    public String getMoyenPaiement() { return moyenPaiement; }
    public void setMoyenPaiement(String moyenPaiement) { this.moyenPaiement = moyenPaiement; }
    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }
}