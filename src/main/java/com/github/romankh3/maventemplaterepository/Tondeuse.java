package com.github.romankh3.maventemplaterepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe {@code Tondeuse} représente une tondeuse qui se déplace sur une pelouse
 * en fonction d'instructions prédéfinies.
 * Elle utilise un système de coordonnées (X, Y) et une direction cardinale pour se positionner.
 * Les déplacements sont définis par une série d'actions qui incluent avancer et pivoter.
 * 
 * @author Vincent Thenon
 * @version 1.0
 */
public final class Tondeuse {

    /**
     * Ensemble des directions cardinales valides (Nord, Est, Sud, Ouest).
     */
    public static final Set<Character> DIRECTIONS_VALIDES = new HashSet<>(Arrays.asList('N', 'E', 'S', 'W'));

    /**
     * Ensemble des virages valides pour pivoter la tondeuse (Gauche, Droite).
     */
    public static final Set<Character> VIRAGES_VALIDES = new HashSet<>(Arrays.asList('G', 'D'));

    private int coorX, coorY;
    private char direction;
    private String actions;

    /**
     * Constructeur par défaut qui initialise la tondeuse aux coordonnées (0, 0)
     * avec une direction vers le Nord et aucune action définie.
     */
    public Tondeuse() {
        this.coorX = 0;
        this.coorY = 0;
        this.direction = 'N';
        this.actions = "";
    }

    /**
     * Constructeur avec paramètres pour initialiser la tondeuse avec des coordonnées,
     * une direction initiale et une série d'actions.
     *
     * @param coorX     La coordonnée X de la tondeuse.
     * @param coorY     La coordonnée Y de la tondeuse.
     * @param direction La direction initiale de la tondeuse ('N', 'E', 'S', 'W').
     * @param actions   Les actions à exécuter pour déplacer la tondeuse.
     * @throws IllegalArgumentException si les coordonnées sont négatives ou si la direction est invalide.
     */
    public Tondeuse(int coorX, int coorY, char direction, String actions) {
        if (coorX < 0 || coorY < 0) {
            throw new IllegalArgumentException("Les coordonnées de la tondeuse ne peuvent pas être négatives");
        }
        if (!DIRECTIONS_VALIDES.contains(direction)) {
            throw new IllegalArgumentException("La direction de la tondeuse doit être 'N', 'E', 'S' ou 'W', reçu : " + direction);
        }
        this.coorX = coorX;
        this.coorY = coorY;
        this.direction = direction;
        this.actions = actions;
    }

    /**
     * Fait pivoter la tondeuse à gauche ou à droite.
     *
     * @param virage Le virage à effectuer ('G' pour gauche, 'D' pour droite).
     * @throws IllegalArgumentException si le virage n'est pas valide.
     */
    public void pivoter(char virage) {
        if (!VIRAGES_VALIDES.contains(virage)) {
            throw new IllegalArgumentException("Le virage de la tondeuse doit être 'G' ou 'D', reçu : " + virage);
        }

        char[] directions = {'N', 'E', 'S', 'W'};
        int currentDirectionIndex = -1;

        // On récupère la direction actuelle de la tondeuse
        for (int i = 0; i < directions.length; i++) {
            if (directions[i] == this.direction) {
                currentDirectionIndex = i;
                break;
            }
        }

        // Les positions sont rangées de manière circulaire dans le tableau. On utilise cette relation pour trouver la prochaine position. 
        if (virage == 'D') {
            currentDirectionIndex = (currentDirectionIndex + 1) % directions.length;
        } else if (virage == 'G') {
            currentDirectionIndex = (currentDirectionIndex - 1 + directions.length) % directions.length;
        }

        this.direction = directions[currentDirectionIndex];
    }

    /**
     * Fait avancer la tondeuse d'une case dans la direction actuelle, 
     * si elle ne dépasse pas les limites spécifiées.
     *
     * @param limiteX La limite maximale de la pelouse en X.
     * @param limiteY La limite maximale de la pelouse en Y.
     */
    public void avancer(int limiteX, int limiteY) {
        switch (this.direction) {
            case 'N': // La tondeuse avance d'une case au Nord
                if (this.coorY + 1 <= limiteY) {
                    this.coorY += 1;
                }
                break;
            case 'E': // La tondeuse avance d'une case à l'Est
                if (this.coorX + 1 <= limiteX) {
                    this.coorX += 1;
                }
                break;
            case 'S': // La tondeuse avance d'une case au Sud
                if (this.coorY - 1 >= 0) {
                    this.coorY -= 1;
                }
                break;
            case 'W': // La tondeuse avance d'une case à l'Ouest
                if (this.coorX - 1 >= 0) {
                    this.coorX -= 1;
                }
                break;
            default:
                break;
        }
    }

    /**
     * Exécute les actions de la tondeuse, telles que pivoter ou avancer,
     * en fonction des limites de la pelouse spécifiées.
     *
     * @param limiteX La limite maximale de la pelouse en X.
     * @param limiteY La limite maximale de la pelouse en Y.
     */
    public void tondre(int limiteX, int limiteY) {
        for (int i = 0; i < this.actions.length(); i++) {
            if (this.actions.charAt(i) == 'A') { 
                this.avancer(limiteX, limiteY);
            } else {
                this.pivoter(this.actions.charAt(i));
            }
        }
    }

    /**
     * Retourne la position actuelle de la tondeuse sous forme de chaîne de caractères.
     *
     * @return La position actuelle sous la forme "X Y Direction".
     */
    public String positionToString() {
        return coorX + " " + coorY + " " + direction;
    }

    /**
     * Obtient les actions de la tondeuse.
     *
     * @return La chaîne d'actions de la tondeuse.
     */
    public String getActions() {
        return this.actions;
    }

    /**
     * Définit les actions de la tondeuse.
     *
     * @param actions La nouvelle chaîne d'actions de la tondeuse.
     */
    public void setActions(String actions) {
        this.actions = actions;
    }
}
