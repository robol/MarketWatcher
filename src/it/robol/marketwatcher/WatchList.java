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
    
    public WatchList() {
        stocks = new ArrayList<>();
        listeners = new ArrayList<>();
    }
    
    public void addStock (Stock s) {
        stocks.add(s);
    }
    
    public void removeStock (Stock s) {
        stocks.remove(s);
    }
    
    public ArrayList<Stock> getStockList() {
        return stocks;
    }
    
}
