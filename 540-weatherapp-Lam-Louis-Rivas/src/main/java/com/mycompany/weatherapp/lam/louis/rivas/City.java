/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import java.util.Objects;

/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class City {
    private Double cityId; //Double as in the json there are some id that have a decimal.
    private String cityName;
    private String country;
    private Double longitude;
    private Double latitude;

    public City(Double cityId, String cityName, String country, Double longitude, Double latitude) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.cityId);
        hash = 89 * hash + Objects.hashCode(this.country);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.cityId, other.cityId)) {
            return false;
        }
        return true;
    }
    
    
}
