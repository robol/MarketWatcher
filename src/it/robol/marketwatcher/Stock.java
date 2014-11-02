/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher;

import java.util.ArrayList;

/**
 *
 * @author robol
 */
public class Stock {
    
    private String ticket = null;
    
    private ArrayList<StockQuoteListener> listeners;
    
    private double currentQuote = 0.0;
    
    public Stock(String ticket) {
        this.listeners = new ArrayList<>();
        this.ticket = ticket;
    }
    
    /**
     * Retrieve the current quote for this Stock element. 
     * 
     * @return A floating point number with the current quote
     * for the given stock. 
     */
    public double getQuote() {
        return this.currentQuote;
    }
    
    /**
     * Obtain the ticket characterizing this stock element. 
     * 
     * @return A string representing the ticket. 
     */
    public String getTicket() {
        return this.ticket;
    }
    
    /**
     * Update the current quote of this Stock. 
     * 
     * @param quote the updated quote. 
     */
    public void updateQuote(double quote) {
       this.currentQuote = quote; 
       this.triggerListeners();
    }
    
    private void triggerListeners() {
        for (StockQuoteListener l : this.listeners) {
            l.updateQuote(currentQuote);
        }
    }
    
    public void addListener(StockQuoteListener l) {
        this.listeners.add(l);
    }
    
    public void removeListener(StockQuoteListener l) {
        this.listeners.remove(l);
    }
    
}
