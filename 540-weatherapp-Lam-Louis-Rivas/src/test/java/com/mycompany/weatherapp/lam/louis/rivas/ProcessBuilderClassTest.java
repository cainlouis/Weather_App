/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherapp.lam.louis.rivas;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rodrigo Rivas <rodrigo.rivas.org>
 */
public class ProcessBuilderClassTest {
    
    String theCmd;
    ProcessBuilderClass instance;

    
    public ProcessBuilderClassTest() {
    }
    
    @BeforeClass
    void setUp() {
        this.theCmd = "src/main/C++/DHT11";
        this.instance = new ProcessBuilderClass(theCmd);
    }


    /**
     * Test of startProcess method, of class ProcessBuilderClass.
     */
    @Test
    public void testStartProcess() throws Exception {
        System.out.println("startProcess");
        Process result = this.instance.startProcess();
        assertNotNull(result);
    }
    
}
