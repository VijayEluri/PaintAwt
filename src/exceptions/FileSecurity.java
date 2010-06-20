package exceptions;

/**
 * Class représentant les exceptions dû au problème de droit sur le fichier
 * de sauvegarde
 */
public class FileSecurity extends Exception {

    /**
     * Méthode permettant l'envoie d'une notification à l'utlisateur
     * @return String
     */
    public String toString() {
        return new String("Vérrifiez les droits dans le dossier et sur le fichier !");
    }
}
