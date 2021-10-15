/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * SevenDayForecastTest tests the output of SevenDayForecastTest
 * @author Daniel Lam
 */
public class SevenDayForecastTest {
    private Weather testWeather;
    private List<Weather> testWeatherList;
    private SevenDayForecast sevenDayForecast;
    
    public SevenDayForecastTest() throws IOException {
        List<Weather> testWeatherList = new ArrayList<>();
        
        Weather testWeather = new Weather();
        testWeather.setTimezone("America/Chicago");
        testWeather.setMaxTemp("10");
        testWeather.setMinTemp("5");
        testWeather.setHumidity("80");
        testWeather.setSunrise("1618282134");
        testWeather.setSunset("1618333901");
                
        testWeather.setPressure("500");
        testWeather.setUv("0");
        testWeather.setWindSpeed("30");
        testWeather.setWindGust("20");
        testWeather.setDescription("few clouds");
        testWeather.setIcon("02d");
        testWeather.setAlertDesc("...HEAT ADVISORY REMAINS IN EFFECT FROM 1 PM THIS AFTERNOON TO\\n8 PM CDT THIS EVENING...\\n* WHAT...Heat index values of 105 to 109 degrees expected.\\n* WHERE...Creek, Okfuskee, Okmulgee, McIntosh, Pittsburg,\\nLatimer, Pushmataha, and Choctaw Counties.\\n* WHEN...From 1 PM to 8 PM CDT Thursday.\\n* IMPACTS...The combination of hot temperatures and high\\nhumidity will combine to create a dangerous situation in which\\nheat illnesses are possible.");
        testWeather.setAlertEvent("Heat Advisory");
        testWeather.setDt("1618308000");
        
        testWeatherList.add(testWeather);
        
        this.testWeather = testWeather;
        this.testWeatherList = testWeatherList;
        this.sevenDayForecast = new SevenDayForecast();
    }

    @Test
    public void testGetWeatherInfo() {
        
        String expWeatherInfo = "Max temperature: 10°C" + "\n"
                + "Min temperature: 5°C" + "\n"
                + "Humidity: 80%" + "\n"
                + "Sunrise: 9:48:54 PM CDT" + "\n"
                + "Sunset: 12:11:41 PM CDT" + "\n"
                + "Pressure: 500 hPa" + "\n"
                + "UV Index: 0" + "\n"
                + "Wind Speed: 30 km/h" + "\n"
                + "Wind Gust: 20 km/h";
        
        assertEquals(expWeatherInfo, sevenDayForecast.getWeatherInfo(testWeather));
    }
    
    @Test
    public void testAlert() {
        String expAlert = "...HEAT ADVISORY REMAINS IN EFFECT FROM 1 PM THIS AFTERNOON TO\\n8 PM CDT THIS EVENING...\\n* WHAT...Heat index values of 105 to 109 degrees expected.\\n* WHERE...Creek, Okfuskee, Okmulgee, McIntosh, Pittsburg,\\nLatimer, Pushmataha, and Choctaw Counties.\\n* WHEN...From 1 PM to 8 PM CDT Thursday.\\n* IMPACTS...The combination of hot temperatures and high\\nhumidity will combine to create a dangerous situation in which\\nheat illnesses are possible.";
        String actualAlert = testWeather.getAlertDesc();
        assertEquals(expAlert, actualAlert);
        
        String expAlertEvent = "Heat Advisory";
        String actualAlertEvent = testWeather.getAlertEvent();
        assertEquals(expAlertEvent,actualAlertEvent);
    }
}
