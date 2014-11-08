/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher.gui;

import it.robol.marketwatcher.Stock;
import it.robol.marketwatcher.StockListener;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author robol
 */
public class StockTableModel extends AbstractTableModel {
    
    private class StockEntry {
        public Stock stock;
        public StockListener listener;
        
        public StockEntry (Stock s, StockListener l) {
            stock = s;
            listener = l;
        }
    }
    
    private ArrayList<StockEntry> stocks;
    
    public StockTableModel() {
        stocks = new ArrayList<>();
    }
    
    public void addStock(Stock s) {        
        StockListener l = new StockListener() {
            @Override
            public void updated(Stock s) {
                fireTableDataChanged();
            }
        };
        
        s.addListener(l);
        stocks.add(new StockEntry (s, l));
    }
    
    public void removeStock(Stock s) {
        for (StockEntry entry : stocks) {
            if (entry.stock == s) {
                s.removeListener (entry.listener);
                stocks.remove (s);
            }
        }
    }

    @Override
    public int getRowCount() {
        return stocks.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName (int i1) {
        switch (i1) {
            case 0:
                return "Name";
            case 1:
                return "Quote";
            case 2:
                return "Change";
        }
        
        return null;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Stock s = stocks.get(i).stock;
        
        switch (i1) {
            case 0:
                return s.getName();
            case 1:
                return s.getQuote();
            case 2:
                return s.getChange();
            default:
                return "";
        }
    }
    
}
