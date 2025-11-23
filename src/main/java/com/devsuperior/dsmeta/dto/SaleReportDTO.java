package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleReportProjection;

import java.time.LocalDate;

public class SaleReportDTO {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String sellername;

    public SaleReportDTO(){

    }

    public SaleReportDTO(Long id, Double amount, LocalDate date, String sellername) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.sellername = sellername;
    }

    public SaleReportDTO(SaleReportProjection projection) {
        id = projection.getId();
        amount = projection.getAmount();
        date = projection.getDate();
        sellername = projection.getSellerName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }
}
