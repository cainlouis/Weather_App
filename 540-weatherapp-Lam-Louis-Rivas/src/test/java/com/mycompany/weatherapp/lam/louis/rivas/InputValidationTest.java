package com.mycompany.weatherapp.lam.louis.rivas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the InputValidation class.
 * @author Nael Louis 
 */
public class InputValidationTest {
    private static InputValidation instance;
    
    public InputValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new InputValidation();
    }
    

    /**
     * Test of validateString method, of class InputValidation with uppercase letters.
     */
    @Test
    public void testValidateStringWithUppercase() {
        System.out.println("validateString");
        String s = "City";
        String expResult = "city";
        String result = instance.validateString(s);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateString method, of class InputValidation with space.
     */
    @Test
    public void testValidateStringWithSpace() {
        System.out.println("validateString");
        String s = "ci ty";
        String expResult = "ci ty";
        String result = instance.validateString(s);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateString method, of class InputValidation with other characters 
     * than alphabetic characters.
     */
    @Test
    public void testValidateStringWithOtherCharacter() {
        System.out.println("validateString with non alphabetical characters");
        String s = "ci'ty!";
        String expResult = "city";
        String result = instance.validateString(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateDouble method, of class InputValidation
     * with non numeric character.
     */
    @Test
    public void testValidateDoubleWithNonNumeric() {
        System.out.println("validateDouble with non numeric");
        double num = Double.NaN;
        double expResult = 0.0;
        double result = instance.validateDouble(num);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of validateDouble method, of class InputValidation
     * with positive infinity.
     */
    @Test
    public void testValidateDoubleWithPositiveInfinity() {
        System.out.println("validateDouble with positive infinity");
        double num = Double.POSITIVE_INFINITY;
        double expResult = 0.0;
        double result = instance.validateDouble(num);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of validateDouble method, of class InputValidation
     * with negative infinity.
     */
    @Test
    public void testValidateDoubleWithNegativeInfinity() {
        System.out.println("validateDouble with negative infinity");
        double num = Double.NEGATIVE_INFINITY;
        double expResult = 0.0;
        double result = instance.validateDouble(num);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of validateDouble method, of class InputValidation
     * with normal double.
     */
    @Test
    public void testValidateDouble() {
        System.out.println("validateDouble with normal double");
        double num = 5.0;
        double expResult = 5.0;
        double result = instance.validateDouble(num);
        assertEquals(expResult, result, 5.0);
    }
    
}
