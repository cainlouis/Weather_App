package com.mycompany.weatherapp.lam.louis.rivas;

import java.util.Map;
import java.util.Objects;

/**
 * This class creates an object of type City
 * containing all the needed properties to create
 * the object from the Json using Jackson.
 * @author Rodrigo Rivas
 */
public class City {
    private double id;
    private String name;
    private String state;
    private String country;
    private Map<String, Double> coord;


    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the state from the Json for later
     * use it to display it on the UI.
     */
    public String getState() {
        return state;
    }
    
    /**
     * This method gets the country from the Json for later
     * use it to display it on the UI.
     */
    public String getCountry() {
        return country;
    }

   /**
     * This method gets the coordinates from the Json for later
     * use it to fetch all the information based on the coordinates.
     */
    public Map<String,Double> getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return name + ", " + country + " (" + coord.get("lon") + ", " + coord.get("lat")+ ")";
    }

    
    
}
