package org.td2.prog_3.Model;


import java.time.LocalDate;

public class Paiement {

    private Long id;
    private Double montant;
    private LocalDate date;
    private String mode;
    private Long membreId;
    private Long cotisationId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    public Long getMembreId() { return membreId; }
    public void setMembreId(Long membreId) { this.membreId = membreId; }
    public Long getCotisationId() { return cotisationId; }
    public void setCotisationId(Long cotisationId) { this.cotisationId = cotisationId; }
}