package exceptions;

/**
 * Class représentant les exceptions dû à la non existence du fichier pour
 * la sauvegarde/chargement
 */
public class FilesNull extends Exception {

    /**
     * Méthode permettant l'envoie d'une notification à l'utlisateur
     * @return String
     */
    public String toString() {
        return new String("Vérrifiez que le fichier existe bien !");
    }

}
