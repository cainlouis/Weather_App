/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class Weather {
    private String temp;
    private String tempday;
    private String tempNight;
    private String tempEvening;
    private String tempMorning;
    private String humidity;
    private String description;
    private String icon;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTempday() {
        return tempday;
    }

    public void setTempday(String tempday) {
        this.tempday = tempday;
    }

    public String getTempNight() {
        return tempNight;
    }

    public void setTempNight(String tempNight) {
        this.tempNight = tempNight;
    }

    public String getTempEvening() {
        return tempEvening;
    }

    public void setTempEvening(String tempEvening) {
        this.tempEvening = tempEvening;
    }

    public String getTempMorning() {
        return tempMorning;
    }

    public void setTempMorning(String tempMorning) {
        this.tempMorning = tempMorning;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" + "temp=" + temp + ", tempday=" + tempday + ", tempNight=" + tempNight + ", tempEvening=" + tempEvening + ", tempMorning=" + tempMorning + ", humidity=" + humidity + ", description=" + description + ", icon=" + icon + '}';
    }

    
    
}
