/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *13 juin 2010
 * @author agindre
 */
public class PolygoneConcave extends Exception {

    public String toString() {
        return new String("Erreur: le polygone est concave!");
    }

}
