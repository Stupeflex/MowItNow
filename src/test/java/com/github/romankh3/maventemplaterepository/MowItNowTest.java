package com.github.romankh3.maventemplaterepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MowItNowTest {
    
    @Test
    public void testConstructeur() {
        assertDoesNotThrow(() -> {
            MowItNow app = new MowItNow("rsc/myFile.txt");
            assertEquals(2, app.getTondeuses().size());
            assertEquals(5, app.getLimiteX());
            assertEquals(5, app.getLimiteY());
        });
    }

    @Test
    public void testTondreAll(){
        assertDoesNotThrow(() -> {
            MowItNow app = new MowItNow("rsc/myFile.txt");
            app.tondreAll();
            assertEquals("1 3 N", app.getTondeuses().get(0).positionToString());
            assertEquals("5 1 E", app.getTondeuses().get(1).positionToString());
        });
    }

    @Test
    public void testConstructeurAvecParametresFichierEntreeEronnees(){
        //Trop d'arguments pour les coordonnées du coin supérieur droit de la pelouse dans le fichier d'entrée
        assertThrows(IOException.class, () -> new MowItNow("rsc/myFile_test1.txt"));

        //Coordonnées du coin supérieur droit de la pelouse négatives dans le fichier d'entrée
        assertThrows(IOException.class, () -> new MowItNow("rsc/myFile_test2.txt"));

        //Argument supplémentaire pour les paramètres de la tondeuse dans le fichier d'entrée
        assertThrows(IOException.class, () -> new MowItNow("rsc/myFile_test3.txt"));
    }

    @Test
    public void testTondreAllAvecParametresFichierEntreeEronnees() {
        assertThrows(IOException.class, () -> {
            MowItNow app = new MowItNow("rsc/myFile_test4.txt");
            app.tondreAll();
        });
    }

    
}
