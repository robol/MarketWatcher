/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.robol.marketwatcher;

import java.io.File;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author robol
 */
public class SystemLocations {
    
    private static final String APP_NAME = "MarketWatcher";
    
    /**
     * This function can be used to find a app-specific place when data
     * can be stored. 
     * 
     * It will run differently on the different operatin systems, in a way
     * that a safe location for (non-visible) user-data can be stored. 
     * 
     * On GNU/Linux systems this is isually ~/.local/share/AppName/
     * 
     * Note that this function will create the directory if it does not
     * exists, making sure that is always usable. 
     * 
     * @return A String with the absolute path of the directory. 
     */
    public static String getDataDir() {
        String OS = System.getProperty("os.name").toLowerCase();
        String dataDirPath = System.getProperty("user.home") + 
                File.separator + APP_NAME;
        
        // Detect the operating system and operate accordingly
        if (OS.contains("win")) {
            throw new NotImplementedException();
        }
        else if (OS.contains("mac")) {
            throw new NotImplementedException();
        }
        else if (OS.contains("nux")) {
            dataDirPath = getDataDirLinux();
        }
        
        File dataDirFile = new File(dataDirPath);
        
        if (! dataDirFile.exists()) {
            dataDirFile.mkdirs();
        }
        else if (! dataDirFile.isDirectory()) {
            throw new RuntimeException("Data directory " + dataDirPath + 
                    " is not a directory");
        }
        
        return dataDirFile.getAbsolutePath();
    }
    
    private static String getDataDirLinux() {
        String xdg_data_dir = System.getenv("XDG_DATA_DIR");
        
        if (xdg_data_dir == null) {
            xdg_data_dir = System.getProperty("user.home") + 
                    File.separator + ".local" + 
                    File.separator + "share";
        }
        
        return xdg_data_dir + File.separator + APP_NAME;
    }
    
}
