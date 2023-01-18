package com.project.factureservice;

public class Facture {

    private Long factureId;
    private String customerName;
    private String productName;
    private int quantity;
    private int price;

    public Facture() {

    }

    public Facture(Long factureId, String customerName,String productName, int price) {
        this.factureId = factureId;
        this.customerName = customerName;
        this.productName = productName;
        this.price = price;
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "factureId=" + factureId +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
