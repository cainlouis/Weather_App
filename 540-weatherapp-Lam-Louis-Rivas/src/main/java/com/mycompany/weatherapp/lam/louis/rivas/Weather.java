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
    private String timezone;
    private String temp;
    private String sunrise;
    private String sunset;
    private String pressure;
    private String uv;
    private String visibility;
    private String windSpeed;
    private String windGust;
    private String maxTemp;
    private String minTemp;
    private String humidity;
    private String description;
    private String icon;
    private String iconUrl = "http://openweathermap.org/img/wn/";
    private String alertEvent;
    private String alertDesc;
    private String dt;
    private String feelsLike;
    
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
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
        return iconUrl + icon + "@2x.png";
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAlertEvent() {
        return alertEvent;
    }

    public void setAlertEvent(String alertEvent) {
        this.alertEvent = alertEvent;
    }

    public String getAlertDesc() {
        return alertDesc;
    }

    public void setAlertDesc(String alertDesc) {
        this.alertDesc = alertDesc;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }
    
    public String getDt() {
        return dt;
    }
    
    public void setDt(String dt) {
        this.dt = dt;
    }
    
    public String getFeelsLike() {
        return feelsLike;
    }
    
    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }
    
    @Override
    public String toString() {
        return "Weather {" + "temp=" + temp + ", maxTemp=" + maxTemp + ", minTemp=" + minTemp + ", humidity=" + humidity + ", description=" + description + ", icon=" + getIcon() + '}';
    }

    
    
}
