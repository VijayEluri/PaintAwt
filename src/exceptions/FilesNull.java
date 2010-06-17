/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *13 juin 2010
 * @author agindre
 */
public class FilesNull extends Exception {

    public String toString() {
        return new String("Vérrifiez que le fichier existe bien !");
    }

}
