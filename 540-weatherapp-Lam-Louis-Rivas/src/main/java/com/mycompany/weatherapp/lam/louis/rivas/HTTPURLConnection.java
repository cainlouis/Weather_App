package com.mycompany.weatherapp.lam.louis.rivas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class takes charge to make a request to the 
 * based on the latitude and longitude
 * @author Nael Louis
 */
public class HTTPURLConnection {
    
    private final String key = "&appid=c9ade50378e0af933f0b33d8eec7eafe";
    
 /** 
  * @param it takes the latitude
 * @param it takes the longitude
 * @return String of the json fetched from the api
 */
    
     String sendRequest(Double lat,Double lon) throws IOException{
    
     StringBuilder data = new StringBuilder();
     
     try {
           //Create the URL
           String fetchUrl = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&exclude=minutely,hourly&units=metric"+key;
           URL url = new URL(fetchUrl);
           //Open the connection
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           
           //Get the response code
           int responseCode = conn.getResponseCode();
           if (responseCode == HttpURLConnection.HTTP_OK) {
               
               //Setup input stream
               InputStream inStream = conn.getInputStream();
               
               try ( //Wrap the InputSteam in a BufferReader for reading data to Strings
                     BufferedReader in = new BufferedReader(new InputStreamReader(inStream))) {
                   String line;
                   //Read the server response one line at a time
                   while ((line = in.readLine()) != null) {
                       data.append("\n"+line);
                   }
                   //Close the BufferedReader
               }
               
           } else {
                System.err.println("Wrong response code: " + responseCode);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return data.toString();
    }
}
