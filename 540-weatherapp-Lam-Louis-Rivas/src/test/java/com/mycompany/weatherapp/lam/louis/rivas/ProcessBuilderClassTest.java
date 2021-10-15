/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Lam
 */
public class ProcessBuilderClassTest {
    
    private ProcessBuilderClass processBuilder;
    
    /**
     * Constructor for ProcessBuilderClassTest
     */
    public ProcessBuilderClassTest() {
        String path = "src/main/C++/DHT11";
        this.processBuilder = new ProcessBuilderClass(path);
    }
    
    /**
     * Test of startProcess method, of class ProcessBuilderClass.
     */
    @Test
    public void testStartProcess() throws Exception {
        System.out.println("startProcess() tests:");
        
        Process expResult = processBuilder.startProcess();
        assertNotNull(expResult);
        
    }
    
}
