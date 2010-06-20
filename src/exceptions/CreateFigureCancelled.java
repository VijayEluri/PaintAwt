package exceptions;

/**
 * Class représentant les exceptions dû à l'annulation de création de
 * figure
 */
public class CreateFigureCancelled extends Exception {

    /**
     * Méthode permettant l'envoie d'une notification à l'utlisateur
     * @return String
     */
     public String toString() {
        return new String("Erreur: Vous avez annulé la création de la figure!");
    }

}
