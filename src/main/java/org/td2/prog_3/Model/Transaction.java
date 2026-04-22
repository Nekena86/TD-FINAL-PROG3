package org.td2.prog_3.Model;


import java.time.LocalDate;

public class Transaction {

    private Long id;
    private Double montant;
    private LocalDate date;
    private String type;
    private Long compteId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getCompteId() { return compteId; }
    public void setCompteId(Long compteId) { this.compteId = compteId; }
}
