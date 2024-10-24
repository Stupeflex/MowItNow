package com.github.romankh3.maventemplaterepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TondeuseTest {

    @BeforeAll
    public static void setUpBeforeAll() throws Exception {
    }

    @AfterAll
    public static void tearDownAfterAll() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testAvancer(){
        Tondeuse tondeuseTest = new Tondeuse(); 
        for(int i = 0; i < 3; i++){
            tondeuseTest.avancer(2, 2);            
        }
        assertEquals("0 2 N", tondeuseTest.positionToString());
    }

    @Test
    public void testPivoter(){
        Tondeuse tondeuseTest = new Tondeuse();
        tondeuseTest.pivoter();
    } 

    @Test
    public void testTondre(){
        fail("Not Yet Implemented");
    } 

    @Test
    public void testPositionToString(){
        fail("Not Yet Implemented");
    } 

}
