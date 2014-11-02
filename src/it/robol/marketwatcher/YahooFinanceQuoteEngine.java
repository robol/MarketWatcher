/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher;

import java.util.ArrayList;
import java.awt.EventQueue;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author robol
 */
public class YahooFinanceQuoteEngine extends TimerTask implements StockQuoteEngine {
    
    private ArrayList<Stock> stocks;
    private boolean active = true;
    private boolean updateInProgress = false;
    private Timer timer;
    
    public YahooFinanceQuoteEngine() {
        timer = new Timer();
        stocks = new ArrayList<Stock>();
        timer.scheduleAtFixedRate(this, 1000, 5000);
    }
    
    @Override 
    public void run() {
        this.updateStockQuotes();
    }
    
    @Override
    public void finalize() {
        timer.cancel();
    }
    
    public void updateStockQuotes() {
        if (!updateInProgress) {
            updateInProgress = true;
        }
        else {
            return;
        }
        
        if (this.active) {        
            for (final Stock s : stocks) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                       YahooFinanceQuoteEngine.this.updateQuote(s);
                    }
                });
            }
        }
        
        updateInProgress = false;
    }
    
    private void updateQuote(Stock s) {
        try {
            String api_url = "https://query.yahooapis.com/v1/public/yql/?q=";
            String query = "select*from yahoo.finance.quote where symbol=\"" +
                    s.getTicket() + "\"";
            String options = "env=store://datatables.org/alltableswithkeys";
            
            URL url = new URL(api_url + URLEncoder.encode(query,"UTF-8") + "&" + options);
            URLConnection connection = url.openConnection();
            
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = f.newDocumentBuilder();            
            Document doc = db.parse(connection.getInputStream());
            
            NodeList symbols = doc.getElementsByTagName("quote");
            
            if (symbols.getLength() >= 1) {
                Element quote = (Element) symbols.item(0);
                Node price = quote.getElementsByTagName("LastTradePriceOnly").item(0);
                s.updateQuote(Double.parseDouble(price.getTextContent()));
            }
            else
                s.updateQuote(0.0);
        } catch (Exception ex) {
            Logger.getLogger(YahooFinanceQuoteEngine.class.getName()).log(Level.SEVERE, null, ex);            
            s.updateQuote(0.0);
        }
        
    }

    @Override
    public void addStock(Stock s) {
        stocks.add(s);
    }

    @Override
    public void removeStock(Stock s) {
        stocks.remove(s);
    }
    
    public static void main(String[] args) {
        YahooFinanceQuoteEngine e = new YahooFinanceQuoteEngine();
        final Stock s = new Stock(args[0]);
        e.addStock(s);
        
        s.addListener(new StockQuoteListener() {
            @Override
            public void updateQuote(double quote) {
                System.out.format("%s: %e\n", s.getTicket(), quote);
            }
        });
    }
}
