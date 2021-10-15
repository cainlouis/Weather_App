package com.mycompany.weatherapp.lam.louis.rivas;

/**
 * Weather object used in dashboard and SevenDays scene, the parameters are set 
 * by the readJson object.
 * @author Rodrigo Rivas
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
    
    /**
     * getter method for the timezone
     * @return String timezone
     */
    public String getTimezone() {
        return timezone;
    }
    
    /**
     * setter for the timezone param
     * @param timezone string from readJson object
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    /**
     * getter method for the temperature
     * @return String temp
     */
    public String getTemp() {
        return temp;
    }
    
    /**
     * setter for the temp param
     * @param temp string from readJson object
     */
    public void setTemp(String temp) {
        this.temp = temp;
    }
    
    /**
     * getter method for the max temperature
     * @return String maxTemp
     */
    public String getMaxTemp() {
        return maxTemp;
    }
    
    /**
     * setter for the maxTemp param
     * @param maxTemp string maxTemp
     */
    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }
    
    /**
     * getter method for the min temperature
     * @return String minTemp
     */
    public String getMinTemp() {
        return minTemp;
    }
    
    /**
     * setter for the minTemp param
     * @param minTemp string from readJson object
     */
    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }
    
    /**
     * getter method for the humidity
     * @return String humidity
     */
    public String getHumidity() {
        return humidity;
    }
    
    /**
     * setter for the humidity param
     * @param humidity string from readJson object
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    
    /**
     * getter method for the weather description
     * @return String description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * setter for the description param
     * @param description string from readJson object
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * getter method for the full icon url
     * @return String fullIconUrl
     */
    public String getIcon() {
        return iconUrl + icon + "@2x.png";
    }
    
    /**
     * setter for the icon param
     * @param icon string from readJson object
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /**
     * getter method for the alert event
     * @return String alertEvent
     */
    public String getAlertEvent() {
        return alertEvent;
    }
    
    /**
     * setter for the alertEvent param
     * @param alertEvent string from readJson object
     */
    public void setAlertEvent(String alertEvent) {
        this.alertEvent = alertEvent;
    }
    
    /**
     * getter method for the alert description
     * @return String alertDesc
     */
    public String getAlertDesc() {
        return alertDesc;
    }
    
    /**
     * setter for the alertDesc param
     * @param alertDesc string from readJson object
     */
    public void setAlertDesc(String alertDesc) {
        this.alertDesc = alertDesc;
    }
    
    /**
     * getter method for the sunrise time
     * @return String sunrise
     */
    public String getSunrise() {
        return sunrise;
    }
    
    /**
     * setter for the sunrise param
     * @param sunrise string from readJson object
     */
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    
    /**
     * getter method for the sunset time
     * @return String sunset
     */
    public String getSunset() {
        return sunset;
    }
    
    /**
     * setter for the sunset param
     * @param sunset string for readJson object
     */
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
    
    /**
     * getter method for the pressure
     * @return String pressure
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * setter for the pressure param
     * @param pressure string from readJson object
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
    
    /**
     * getter method for the uv index
     * @return String uv
     */
    public String getUv() {
        return uv;
    }
    
    /**
     * setter for the uv param
     * @param uv string from readJson object
     */
    public void setUv(String uv) {
        this.uv = uv;
    }
    
    /**
     * getter method for the visibility
     * @return String visibility
     */
    public String getVisibility() {
        return visibility;
    }
    
    /**
     * setter for the visibility param
     * @param visibility 
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
    /**
     * getter method for the speed of the wind
     * @return String windSpeed
     */
    public String getWindSpeed() {
        return windSpeed;
    }
    
    /**
     * setter for the windSpeed param
     * @param windSpeed string from readJson object
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
    
    /**
     * getter method for the wind gust 
     * @return String windGust
     */
    public String getWindGust() {
        return windGust;
    }
    
    /**
     * setter for the windGust param
     * @param windGust string from readJson object
     */
    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }
    
    /**
     * getter method for the time where we do the fetch
     * @return String dt
     */
    public String getDt() {
        return dt;
    }
    
    /**
     * setter for the dt param
     * @param dt string from readJson object
     */
    public void setDt(String dt) {
        this.dt = dt;
    }
    
    /**
     * getter method for the Feels like temperature
     * @return String feelsLike
     */
    public String getFeelsLike() {
        return feelsLike;
    }
    
    /**
     * setter for the feels like param
     * @param feelsLike string from readJson object 
     */
    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }
    
    @Override
    public String toString() {
        return "Weather {" + "temp=" + temp + ", maxTemp=" + maxTemp + ", minTemp=" + minTemp + ", humidity=" + humidity + ", description=" + description + ", icon=" + getIcon() + '}';
    }

    
    
}
