/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher;

import java.io.File;
import java.sql.*;

/**
 *
 * @author robol
 */
public class Storage {
    
    private static final Storage INSTANCE = new Storage();
    
    private static WatchList exampleList;
    
    private Connection connection = null;
    
    private Storage() {
        exampleList = new WatchList();
        exampleList.setName("Example list");
        
        openDb();
    }
    
    public static Storage getInstance() {
        return INSTANCE;
    }
    
    private void openDb() {
        if (connection != null) {
            closeDb();
        }
        
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + getDbPath(), "", "");
        } catch (Exception e) {
            e.printStackTrace();
            // FIXME: Handle the exception
        }
        
        initDatabase();
    }
    
    private void closeDb() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (Exception e) {
            // FIXME: Log errors
        }
    }
    
    private void initDatabase() {
        try {
            Statement stmt = connection.createStatement();
            
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS watchlists ("
                        + "id INTEGER PRIMARY KEY, "
                        + "name TEXT)"
            );
            
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS watchlists_tickets ("
                        + "id INTEGER PRIMARY KEY,"
                        + "watchlist INTEGER,"
                        + "ticket TEXT,"
                        + "FOREIGN KEY(watchlist) REFERENCES watchlists(id))");
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    private String getDbPath() {
        return SystemLocations.getDataDir() + File.separator + "storage.db";
    }
    
    public WatchList[] getWatchLists() {
        WatchList lists[] = { exampleList };
        return lists;
    }
}
