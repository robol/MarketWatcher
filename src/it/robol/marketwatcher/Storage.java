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
public class Storage {
    
    private static final Storage INSTANCE = new Storage();
    
    private static WatchList exampleList;
    
    private Storage() {
        exampleList = new WatchList();
        exampleList.setName("Example list");
    }
    
    public static Storage getInstance() {
        return INSTANCE;
    }
    
    public WatchList[] getWatchLists() {
        WatchList lists[] = { exampleList };
        return lists;
    }
}
