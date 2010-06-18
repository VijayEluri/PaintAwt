/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *18 juin 2010
 * @author agindre
 */
public class CreateFigureCancelled extends Exception {

     public String toString() {
        return new String("Erreur: Vous avez annulé la création de la figure!");
    }

}
