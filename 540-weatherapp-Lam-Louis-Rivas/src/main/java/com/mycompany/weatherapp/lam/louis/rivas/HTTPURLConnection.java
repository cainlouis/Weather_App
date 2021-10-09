/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *
 * @author Nael Louis 
 */

//website = "https://api.openweathermap.org/data/2.5/onecall/"
// Key = "c9ade50378e0af933f0b33d8eec7eafe"
// "&appid={c9ade50378e0af933f0b33d8eec7eafe}"
public class HTTPURLConnection {
    
    //private final String appId = "&appid={c9ade50378e0af933f0b33d8eec7eafe}";
    
     String sendRequest(String website, String Key, String params) throws IOException{
    
     StringBuilder data = new StringBuilder();
     
     try {
           //Create the URL
           URL url = new URL(website + params);
           
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
