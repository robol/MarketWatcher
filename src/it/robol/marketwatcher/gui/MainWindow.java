/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher.gui;

import javax.swing.JFrame;

/**
 *
 * @author robol
 */
public class MainWindow extends JFrame {
    
    public MainWindow() {
        setTitle("Market Watcher 0.1");
        setLocationRelativeTo(null);
        setSize(300, 400);
        
        // FIXME: As of now we exit on close, but it's quite
        // likely that we will need some cleanup at a certain point. 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
