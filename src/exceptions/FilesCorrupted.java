package exceptions;

/**
 * Class représentant les exceptions dû à des problème de flux lors de la lecture
 * du fichier de sauvegarde/chargement
 */
public class FilesCorrupted extends Exception {

    /**
     * Méthode permettant l'envoie d'une notification à l'utlisateur
     * @return String
     */
    public String toString() {
        return new String("Le fichier est corrompu !");
    }
}
