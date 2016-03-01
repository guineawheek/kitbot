package org.usfirst.frc.team2785.misc;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Player {
    /**
     * Replays arrays of Doubles in HashMaps to asking parties
     */
    private Map<String, ArrayList<Double>> data;
    public Player() {
        
    }
    public void read(String filePath) {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            data = (Map<String, ArrayList<Double>>) objIn.readObject();
            fileIn.close();
            objIn.close();
        } catch (Exception e) {
            e.printStackTrace(); //ouch!
        }
    }
    public Iterator<Double> getIterator(String tableName) {
        return data.get(tableName).iterator();
    }
}
