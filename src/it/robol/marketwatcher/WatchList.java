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
public class WatchList {
    
    ArrayList<Stock> stocks;
    ArrayList<WatchListListener> listeners;
    
    String name = "Watch List";
    
    StockQuoteEngine engine = null;
    
    public WatchList() {
        stocks = new ArrayList<>();
        listeners = new ArrayList<>();
    }
    
    public void connectToQuoteEngine (StockQuoteEngine e) {
        if (engine != null) {
            for (Stock s : stocks) {
                engine.removeStock(s);
            }
        }
        
        engine = e;
        
        for (Stock s : stocks) {
            engine.addStock(s);
        }
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public String getName () {
        return name;
    }
    
    public void addStock (Stock s) {
        stocks.add(s);
        
        for (WatchListListener l : listeners) {
            l.stockAdded (s);
        }
        
        if (engine != null) {
            engine.addStock(s);
        }
    }
    
    public void removeStock (Stock s) {
        stocks.remove(s);
        
        for (WatchListListener l : listeners) {
            l.stockRemoved (s);
        }
        
        if (engine != null) {
            engine.removeStock(s);
        }
    }
    
    public ArrayList<Stock> getStockList() {
        return stocks;
    }
    
    public void addListener(WatchListListener l) {
        listeners.add (l);
    }
    
    public void removeListener (WatchListListener l) {
        listeners.remove (l);
    }
    
}
