package exceptions;

/**
 * Class représentant les exceptions dû à la concavité d'un polygone
 */
public class PolygoneConcave extends Exception {

    /**
     * Méthode permettant l'envoie d'une notification à l'utlisateur
     * @return String
     */
    public String toString() {
        return new String("Erreur: le polygone est concave!");
    }

}
