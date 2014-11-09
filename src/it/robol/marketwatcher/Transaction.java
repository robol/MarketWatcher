/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher;

/**
 *
 * @author robol
 */
public class Transaction {
    
    private double pricePaid;
    private double openCommission = 0.0;
    private double closeCommission = 0.0;
    private int quantity = 0;
    
    private Stock stock;
    
    public Transaction(Stock stock, int quantity, double pricePaid, 
            double openCommission, double closeCommission) {
        this.quantity = quantity;
        this.openCommission = openCommission;
        this.closeCommission = closeCommission;
        this.pricePaid = pricePaid;
        this.stock = stock;
    }
    
    public Stock getStock() { return stock; }
    public double getPricePaid() { return pricePaid; }
    public double getOpenCommission() { return openCommission; }
    public double getCloseCommission() { return closeCommission; }
    public int getQuantity() { return quantity; }
    
}
