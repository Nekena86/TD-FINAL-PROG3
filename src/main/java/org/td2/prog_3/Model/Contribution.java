
package org.td2.prog_3.Model;

import java.time.LocalDate;

public class Contribution {

    private Long id;
    private Double amount;
    private LocalDate date;
    private Long memberId;
    private Long collectivityId;
    private String type;
    private String paymentMode;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public Long getCollectivityId() { return collectivityId; }
    public void setCollectivityId(Long collectivityId) { this.collectivityId = collectivityId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
}