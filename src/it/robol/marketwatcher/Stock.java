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
    private String name = null;
    private double currentQuote = 0.0;
    
    private ArrayList<StockListener> listeners;
    
    /**
     * This field is true if updates to the Stock cause notifications
     * to be propagated to the listeners.
     * 
     * It can be altered by using disableNotifications() and enableNotifications()
     * in order to group a number of notifications together (but don't forget
     * to call triggerListeners() after re-enabling notifications!). 
     */
    private boolean notificationsEnabled = true;
    
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
     * Obtain the name of this Stock. 
     * 
     * @return A string containing the current name of the stock. 
     */
    public String getName() {
        return this.name;
    }
    
    public void updateName(String name) {
        this.name = name;
        this.triggerListeners();
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
    
    public void disableNotifications() {
        notificationsEnabled = false;
    }
    
    public void enableNotifications() {
        notificationsEnabled = true;
    }
    
    public void triggerListeners() {
        if (notificationsEnabled) {
            for (StockListener l : this.listeners) {
                l.updated(this);
            }
        }
    }
    
    public void addListener(StockListener l) {
        this.listeners.add(l);
    }
    
    public void removeListener(StockListener l) {
        this.listeners.remove(l);
    }
    
}
