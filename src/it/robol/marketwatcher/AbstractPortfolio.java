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
public class AbstractPortfolio {
    
    private ArrayList<Transaction> transactions;
    
    public AbstractPortfolio() {
        transactions = new ArrayList<>();
    }
    
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }
    
}
