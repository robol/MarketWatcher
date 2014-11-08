/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher.gui;

import it.robol.marketwatcher.StockQuoteEngine;
import it.robol.marketwatcher.YahooFinanceQuoteEngine;
import java.awt.EventQueue;

/**
 *
 * @author robol
 */
public class MarketWatcherApp {
    
    private static MainWindow mainWindow = null;
    
    private static StockQuoteEngine engine = null;
    
    public static MainWindow getMainWindow() { 
        if (mainWindow == null) {
            mainWindow = new MainWindow();
        }
        
        return mainWindow;
    }
    
    public static StockQuoteEngine getStockQuoteEngine() {
        if (engine == null) {
            // FIXME: In principle the user should be able to
            // select which engine to use here. 
            engine = new YahooFinanceQuoteEngine();
        }
        
        return engine;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                getMainWindow().setVisible(true);
            }
        });
    }
    
}
