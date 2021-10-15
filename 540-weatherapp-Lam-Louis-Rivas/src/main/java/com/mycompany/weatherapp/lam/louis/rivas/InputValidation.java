package com.mycompany.weatherapp.lam.louis.rivas;

import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

/**
 *
 * @author Nael Louis 
 */
public class InputValidation {
    //Setup a Logger for the class
    private static final Logger LOGGER = Logger.getLogger(InputValidation.class.getName());
    
    /**
     * This method validates the input from outside of the project i.e. user input,
     * data from the json.
     */
    public String validateString(String s) {
        String toValidate = s.toLowerCase();

        toValidate = Normalizer.normalize(toValidate, Normalizer.Form.NFKC);
        Pattern patternObj = Pattern.compile("[<>]");
        Matcher matcherObj = patternObj.matcher(toValidate);
        if (matcherObj.find()) {
            LOGGER.log(Level.SEVERE, "Black listed character found in input");
        }
        else if (Pattern.matches("^[a-zA-Z]+$", toValidate)) {
            return toValidate;
        }
        return toValidate.replaceAll("[^A-Za-z0-9 ]", "");
    }

    /**
     * Verify that the number entered is a number and not infinity
     * @param num a double from outside source
     * @return the number entered
     */
    public double validateDouble(double num) {
        num = Double.isNaN(num) ? 0.0 : num;
        num = num == Double.NEGATIVE_INFINITY ? 0.0 : num;
        num = num == Double.POSITIVE_INFINITY ? 0.0 : num;
        return num;
    }
}
