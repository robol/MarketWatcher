/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher.gui;

import it.robol.marketwatcher.Stock;
import it.robol.marketwatcher.Storage;
import it.robol.marketwatcher.WatchList;

/**
 *
 * @author robol
 */
public class StockListTable extends javax.swing.JTable {
    
    private StockTableModel model = null;

    public StockListTable() {
        Storage s = Storage.getInstance();
        WatchList w = s.getWatchLists()[0];
        w.connectToQuoteEngine(MarketWatcherApp.getStockQuoteEngine());
        
        setModel (new StockTableModel(w));
    }
}
