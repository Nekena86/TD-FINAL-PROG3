package org.td2.exam.Model;

public class FinancialAccount {
    private String id;
    private String collectivityId;
    private String typeCompte;
    private int montantInitial;
    private String titulaire;
    private String numeroTelephone;

    public FinancialAccount() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCollectivityId() { return collectivityId; }
    public void setCollectivityId(String collectivityId) { this.collectivityId = collectivityId; }
    public String getTypeCompte() { return typeCompte; }
    public void setTypeCompte(String typeCompte) { this.typeCompte = typeCompte; }
    public int getMontantInitial() { return montantInitial; }
    public void setMontantInitial(int montantInitial) { this.montantInitial = montantInitial; }
    public String getTitulaire() { return titulaire; }
    public void setTitulaire(String titulaire) { this.titulaire = titulaire; }
    public String getNumeroTelephone() { return numeroTelephone; }
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }
}