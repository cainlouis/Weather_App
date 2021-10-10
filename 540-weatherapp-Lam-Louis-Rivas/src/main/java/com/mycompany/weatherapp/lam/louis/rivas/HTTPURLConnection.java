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
public class HTTPURLConnection {
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
