package com.github.romankh3.maventemplaterepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La classe {@code MowItNow} gère la lecture des instructions à partir d'un fichier
 * pour configurer une série de tondeuses et leurs actions sur une pelouse définie.
 * Elle permet d'initialiser plusieurs instances de {@link Tondeuse} et de les
 * contrôler en fonction des instructions fournies.
 * 
 * <p>
 * Les coordonnées limites de la pelouse sont également lues depuis le fichier pour
 * s'assurer que les tondeuses ne sortent pas des limites définies.
 * </p>
 * 
 * @author Vincent Thenon
 * @version 1.0
 */
public final class MowItNow {

    private List<Tondeuse> tondeuses;
    private int limiteX, limiteY;

    /**
     * Constructeur de la classe {@code MowItNow} qui lit un fichier d'instructions pour initialiser
     * la pelouse et les tondeuses. Le fichier doit suivre un format spécifique où :
     * <ul>
     *   <li>La première ligne contient les coordonnées maximales (X, Y) de la pelouse.</li>
     *   <li>Les lignes impaires (à partir de la deuxième ligne) définissent la position initiale et la direction d'une tondeuse.</li>
     *   <li>Les lignes paires suivantes contiennent les actions à exécuter pour cette tondeuse.</li>
     * </ul>
     *
     * @param cheminFichier Le chemin vers le fichier contenant les instructions d'entrée.
     * @throws IOException si une erreur se produit lors de la lecture ou du formatage du fichier.
     * @throws IllegalArgumentException si les valeurs des coordonnées ou les directions des tondeuses ne sont pas valides.
     */
    public MowItNow(String cheminFichier) throws IOException {

        this.tondeuses = new ArrayList<>();

        // Fichier d'instructions initialisant le programme
        File fichierEntree = new File(cheminFichier);

        try (BufferedReader br = new BufferedReader(new FileReader(fichierEntree))) {

            List<String> instructions = new ArrayList<>(br.lines().collect(Collectors.toList()));

            for (int i = 0; i < instructions.size(); i++) {

                String[] instructionCourrante = instructions.get(i).split("\\s+");

                // Initialisation des limites de la pelouse (première ligne du fichier)
                if (i == 0) {

                    if (instructionCourrante.length != 2) {
                        throw new IOException("Erreur dans le fichier d'entree - Arguments incorrects pour : coordonnées du coin supérieur droit de la pelouse");
                    }

                    this.limiteX = Integer.parseInt(instructionCourrante[0]);
                    this.limiteY = Integer.parseInt(instructionCourrante[1]);

                    if (this.limiteX < 0 || this.limiteY < 0) {
                        throw new IOException("Erreur dans le fichier d'entree - Les coordonnées du coin supérieur droit de la pelouse ne peuvent pas être négatives");
                    }

                // Initialisation des tondeuses à chaque ligne impaires selon le format du fichier d'instructions
                } else if (i % 2 != 0) {

                    if (instructionCourrante.length != 3) {
                        throw new IOException("Erreur dans le fichier d'entree - Arguments incorrects pour : coordonnées et direction de la tondeuse");
                    }

                    // Initialisation de chaque tondeuse
                    int tondeuseCoorX = Integer.parseInt(instructionCourrante[0]);
                    int tondeuseCoorY = Integer.parseInt(instructionCourrante[1]);
                    char tondeuseDirection = instructionCourrante[2].charAt(0);
                    String tondeuseActions = String.join("", instructions.get(i + 1).split("\\s+"));

                    this.tondeuses.add(new Tondeuse(tondeuseCoorX, tondeuseCoorY, tondeuseDirection, tondeuseActions));
                }
            }
        } catch (IllegalArgumentException e) {
            throw new IOException("Erreur dans le fichier d'entree - " + e.getMessage());
        }
    }

    /**
     * Fait tondre toutes les tondeuses en exécutant leurs actions respectives.
     * Chaque tondeuse se déplace sur la pelouse en fonction des limites définies
     * et des instructions lues dans le fichier.
     *
     * <p>
     * Les positions finales de toutes les tondeuses sont affichées dans la console.
     * </p>
     *
     * @throws IOException si une erreur se produit lors de l'exécution des actions des tondeuses.
     * @throws IllegalArgumentException si les actions ou les limites dépassent les valeurs autorisées.
     */
    public void tondreAll() throws IOException {
        try {
            String res = "";
            for (Tondeuse tondeuse : tondeuses) {
                tondeuse.tondre(limiteX, limiteY);
                res += tondeuse.positionToString() + " ";
            }
            System.out.println(res);
        } catch (IllegalArgumentException e) {
            throw new IOException("Erreur dans le fichier d'entree - " + e.getMessage());
        }
    }
}
