package com.github.romankh3.maventemplaterepository;

/**
 * classe principale pour exécuter l'application MowItNow.
 * 
 * <p>
 * Cette classe initialise et lance l'application MowItNow en lisant un fichier d'instructions
 * pour définir la pelouse et les tondeuses, puis exécute les actions correspondantes pour chaque tondeuse.
 * </p>
 * 
 * @author Vincent Thenon
 * @version 1.0
 */
public class RunApp {

     /**
     * Chemin vers le fichier d'entrée contenant les instructions pour paramétrer la taille du terrain, les positions 
     * initiales des tondeuses, et les actions à réaliser par celles-ci.
     */
    public static final String MOWITNOW_INPUT_FILE_PATH = "rsc/myFile.txt";

    /**
     * Méthode principale de l'application qui initialise l'instance de {@link MowItNow} et exécute les actions 
     * des tondeuses telles que définies dans le fichier d'entrée.
     *
     * @param args les arguments de la ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {
        try{
            MowItNow app = new MowItNow(MOWITNOW_INPUT_FILE_PATH);
            app.tondreAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

