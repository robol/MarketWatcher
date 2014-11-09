/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher.gui;

import it.robol.marketwatcher.Stock;
import it.robol.marketwatcher.StockListener;
import it.robol.marketwatcher.WatchList;
import it.robol.marketwatcher.WatchListListener;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author robol
 */
public class StockTableModel extends AbstractTableModel {
      
    private WatchList list;
    private WatchListListener listlistener;
    
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
    
    /**
     * Create a table model linked to a WatchList. 
     * 
     * @param list A valid WatchList whose internal ArrayList
     * of Stocks will be used as reference. 
     */
    public StockTableModel(WatchList list) {
        this();
        
        this.list = list;
        
        for (Stock s : list.getStockList()) {
            addStock (s);
        }
        
        listlistener = new WatchListListener() {
            @Override
            public void stockAdded(Stock s) {
                addStock(s);
            }

            @Override
            public void stockRemoved(Stock s) {
                removeStock(s);
            }
        };
        
        list.addListener (listlistener);
    }
    
    public void dispose() {
        list.removeListener(listlistener);
        
        for (StockEntry s : stocks) {
            s.stock.removeListener(s.listener);
        }
    }
    
    public final void addStock(Stock s) {        
        StockListener l = new StockListener() {
            @Override
            public void updated(Stock s) {
                fireTableDataChanged();
            }
        };
        
        s.addListener(l);
        stocks.add(new StockEntry (s, l));
        
        fireTableRowsInserted(stocks.size() - 1, stocks.size());
    }
    
    public void removeStock(Stock s) {
        for (StockEntry entry : stocks) {
            if (entry.stock == s) {
                s.removeListener (entry.listener);
                stocks.remove (entry);
            }
        }
        
        fireTableDataChanged();
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
                double quote = s.getQuote();
                return (quote == 0.0) ? "Not available" : quote;
            case 2:
                double change = s.getChange();
                return (change == 0.0) ? "Not available" : change;
            default:
                return "";
        }
    }
    
}
