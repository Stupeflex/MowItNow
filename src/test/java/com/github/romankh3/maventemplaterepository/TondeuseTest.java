package com.github.romankh3.maventemplaterepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TondeuseTest {

    @Test
    public void testPositionToString(){
        Tondeuse tondeuseTest = new Tondeuse(2, 3, 'E', "");
        assertEquals("2 3 E", tondeuseTest.positionToString());
    } 

    @Test
    public void testPivoter(){
        Tondeuse tondeuseTest = new Tondeuse();

        tondeuseTest.pivoter('D');
        assertEquals("0 0 E", tondeuseTest.positionToString());
        tondeuseTest.pivoter('D');
        assertEquals("0 0 S", tondeuseTest.positionToString());
        tondeuseTest.pivoter('D');
        assertEquals("0 0 W", tondeuseTest.positionToString());
        tondeuseTest.pivoter('D');
        assertEquals("0 0 N", tondeuseTest.positionToString());

        tondeuseTest.pivoter('G');
        assertEquals("0 0 W", tondeuseTest.positionToString());
        tondeuseTest.pivoter('G');
        assertEquals("0 0 S", tondeuseTest.positionToString());
        tondeuseTest.pivoter('G');
        assertEquals("0 0 E", tondeuseTest.positionToString());
        tondeuseTest.pivoter('G');
        assertEquals("0 0 N", tondeuseTest.positionToString());
    } 
    
    @Test
    public void testAvancer(){

        Tondeuse tondeuseTest = new Tondeuse(); 
        for(int i = 0; i < 3; i++){
            tondeuseTest.avancer(2, 2);            
        }
        assertEquals("0 2 N", tondeuseTest.positionToString());

        tondeuseTest = new Tondeuse(0,2,'S',""); 
        for(int i = 0; i < 3; i++){
            tondeuseTest.avancer(2, 2);            
        }
        assertEquals("0 0 S", tondeuseTest.positionToString());

        tondeuseTest = new Tondeuse(0,0,'E',""); 
        for(int i = 0; i < 3; i++){
            tondeuseTest.avancer(2, 2);            
        }
        assertEquals("2 0 E", tondeuseTest.positionToString());

        tondeuseTest = new Tondeuse(0,0,'W',""); 
        for(int i = 0; i < 3; i++){
            tondeuseTest.avancer(2, 2);            
        }
        assertEquals("0 0 W", tondeuseTest.positionToString());
    }

    @Test
    public void testTondre(){
        Tondeuse tondeuseTest = new Tondeuse(0,0,'N',"AAADAADA");
        tondeuseTest.tondre(2, 2);
        assertEquals("2 1 S", tondeuseTest.positionToString());
    } 

    @Test
    public void testConstructeurAvecCoordonneesInvalides() {
        assertThrows(IllegalArgumentException.class, () -> new Tondeuse(-1, 0, 'N', ""));
        assertThrows(IllegalArgumentException.class, () -> new Tondeuse(0, -1, 'N', ""));
    }

    @Test
    public void testConstructeurAvecDirectionInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new Tondeuse(0, 0, 'A', ""));
    }

    @Test
    public void testPivoterAvecVirageInvalide() {
        Tondeuse tondeuseTest = new Tondeuse();
        assertThrows(IllegalArgumentException.class, () -> tondeuseTest.pivoter('X'));
    }

}
