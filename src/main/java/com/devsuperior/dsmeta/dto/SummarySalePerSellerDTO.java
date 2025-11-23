package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SummarySalePerSellerProjection;

public class SummarySalePerSellerDTO {
    private String sellername;
    private Double salesum;

    public SummarySalePerSellerDTO(){

    }

    public SummarySalePerSellerDTO(String sellername, Double salesum) {
        this.sellername = sellername;
        this.salesum = salesum;
    }

    public SummarySalePerSellerDTO(SummarySalePerSellerProjection projection){
        sellername = projection.getSellerName();
        salesum = projection.getSaleSum();
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public Double getSalesum() {
        return salesum;
    }

    public void setSalesum(Double salesum) {
        this.salesum = salesum;
    }
}
