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
public interface StockQuoteListener {
    
    /**
     * Called when the quote of the associated Stock
     * changes. 
     * 
     * @param quote The value of the new quote
     */
    public void updateQuote(double quote);
    
}
