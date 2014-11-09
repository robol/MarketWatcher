/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher;

/**
 *
 * @author robol
 */
public interface WatchListListener {
    
    public void stockAdded (Stock s);
    public void stockRemoved (Stock s);
    
}
