/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher.gui;

import it.robol.marketwatcher.Stock;

/**
 *
 * @author robol
 */
public class StockListTable extends javax.swing.JTable {
    
    private StockTableModel model = null;

    public StockListTable() {
        model = new StockTableModel();
        setModel(model);
    }
}
