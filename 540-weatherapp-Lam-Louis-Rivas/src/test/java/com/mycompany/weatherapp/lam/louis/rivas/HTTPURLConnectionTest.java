/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class HTTPURLConnectionTest {
    
    public HTTPURLConnectionTest() {
    }
    

    /**
     * Test of sendRequest method, of class HTTPURLConnection.
     * This method fetches to the weatherApi by providing the 
     * latitude and the longitude.
     */
    @Test
    public void testSendRequest() throws Exception {
        System.out.println("sendRequest");
        Double lat = 45.50;
        Double lon = 73.56;
        HTTPURLConnection instance = new HTTPURLConnection();
        String result = instance.sendRequest(lat, lon);
        assertNotNull(result);
    }
    
}
