package com.mycompany.weatherapp.lam.louis.rivas;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Rodrigo Rivas
 */
public class City {
    private double id; //double as in the json there are some id that have a decimal.
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

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Map<String,Double> getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return name + ", " + country + " (" + coord.get("lon") + ", " + coord.get("lat")+ ")";
    }

    
    
}
