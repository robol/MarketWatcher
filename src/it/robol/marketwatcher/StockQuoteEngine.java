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
public interface StockQuoteEngine {
    
    /**
     * Add a Stock to the ones watched by the StockQuoteEngine. 
     * 
     * Once the Stock has been added the StockQuoteEngine will provide
     * updates calling the updateQuote() method on the registered Stock. 
     * 
     * @param s The Stock whose quote will be monitored. 
     */
    public void addStock(Stock s);
    
    /**
     * Remove the given Stock from the watch list. 
     * 
     * @param s The Stock that shall be removed. 
     */
    public void removeStock(Stock s);
    
}
