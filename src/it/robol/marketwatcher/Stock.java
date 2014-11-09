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
    private double dayLow = 0.0;
    private double dayHigh = 0.0;
    private long volume = 0;
    private double change = 0.0;
    
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
        this.name = ticket;
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
    
    public void updateDayHigh(double dayHigh) {
        this.dayHigh = dayHigh;
        triggerListeners();
    }
    
    public double getDayHigh() {
        return this.dayHigh;
    }
    
    public void updateDayLow(double dayLow) {
        this.dayLow = dayLow;
        triggerListeners();
    }
    
    public double getDayLow() {
        return dayLow;
    }
    
    public void updateVolume (long volume) {
        this.volume = volume;
        triggerListeners();
    }
    
    public long getVolume () {
        return this.volume;
    }
    
    public void updateChange (double change) {
        this.change = change;
        triggerListeners();
    }
    
    public double getChange () {
        return this.change;
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
