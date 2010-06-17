/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *13 juin 2010
 * @author agindre
 */
public class FileSecurity extends Exception {

    public String toString() {
        return new String("Vérrifiez les droits dans le dossier et sur le fichier !");
    }
}
