package org.td2.exam.Model;

import java.time.LocalDate;

public class Payment {
    private String collectivityId;
    private String memberId;
    private int montant;
    private String compteCrediteId;
    private String moyenPaiement;
    private LocalDate datePaiement;

    public Payment() {}

    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public int getMontant() { return montant; }
    public void setMontant(int montant) { this.montant = montant; }
    public String getCompteCrediteId() { return compteCrediteId; }
    public void setCompteCrediteId(String compteCrediteId) { this.compteCrediteId = compteCrediteId; }
    public String getMoyenPaiement() { return moyenPaiement; }
    public void setMoyenPaiement(String moyenPaiement) { this.moyenPaiement = moyenPaiement; }
    public LocalDate getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDate datePaiement) { this.datePaiement = datePaiement; }
}