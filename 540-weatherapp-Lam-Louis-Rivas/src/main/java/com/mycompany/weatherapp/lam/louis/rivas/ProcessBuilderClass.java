package com.mycompany.weatherapp.lam.louis.rivas;

import java.io.IOException;

/**
 * ProcessBuilderClass initiates the ProcessBuilder object, sets up its environment with the path to the C++ exec, starts it and returns the process.
 * @author Daniel Lam 1932789
 */

public class ProcessBuilderClass {
    
    private ProcessBuilder processBuilderObj;
    
    /**
     * Parameterized Constructor for ProcessBuilderClass to set up ProcessBuilder's environment
     * @param theApp 
     */
    public ProcessBuilderClass(String theApp) {
        this.processBuilderObj = new ProcessBuilder();

        this.processBuilderObj.command("sudo", theApp);
    }
    
    /**
     * startProcess() starts the ProcessBuilder object and returns it
     * @return process
     * @throws IOException 
     */
    Process startProcess() throws IOException {
        //Start the process
        var process = this.processBuilderObj.start();
        
        return process;
    }
}

