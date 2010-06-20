package fenetres;

import exceptions.CreateFigureCancelled;
import exceptions.FileSecurity;
import exceptions.FilesCorrupted;
import exceptions.FilesNull;
import javax.swing.JOptionPane;
import toolkit.LoadSave;

/**
 * Class quo gère les différentes fenêtre de dialogue utilisées dans l'application
 */
public class FenetDialogues {

    /**
     * Initialisation des variables utilisées dans les différentes
     * fenêtres de dialogue
     */
    private Object[] options = {"Oui", "Non"};
    private Object[] optionsErreur = {"OK"};
    private String message = new String("\n PaintAwt v1.0 \n \t ENSIIE \n \t Alexandre Gindre - Simon Laubet-Xavier");

    /**
     * Constructeur de class, prend en paramètre la FenetAffiche associée
     * On fait appel à ce constructeur pour la création de la fenêtre de dialogue
     * pour la proposition de sauvegarde
     * @param frame
     */
    public FenetDialogues(FenetAffiche frame) {
        int n = JOptionPane.showOptionDialog(frame,
                "Vous allez perdre tout ce qui n'est pas sauvegardé \n"
                + "Voulez-vous sauvegarder?",
                "Sauvegarde",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (n == 0) {
            LoadSave ls = new LoadSave(frame);
            try {
                ls.save();
            } catch (FileSecurity ex) {
                new FenetDialogues(frame, ex);
            } catch (FilesNull ex) {
                new FenetDialogues(frame, ex);
            } catch (FilesCorrupted ex) {
                new FenetDialogues(frame, ex);
            }
        }

    }

    /**
     * Constructeur de class, prend en paramètre la FenetAffiche associée et le
     * nom par défaut de la figure courante.
     * On utilise ce constructeur pour la fenetre de dialogue lors de la
     * création de figure
     * @param frame FenetAffiche
     * @param nom String
     * @throws CreateFigureCancelled
     */
    public FenetDialogues(FenetAffiche frame, String nom) throws CreateFigureCancelled {
        String s = (String) JOptionPane.showInputDialog(
                frame,
                "Veillez entrer le nom de la figure",
                "Nom figure",
                JOptionPane.PLAIN_MESSAGE, null, null, nom);

        //If a string was returned, say so.
        if (s == null) {
            throw new CreateFigureCancelled();
        } else if (s.length() > 0) {
            frame.nom = s;
        }
    }

    /**
     * Constructeur de class, prend en paramètre la FenetAffiche associée et
     * l'exception à afficher.
     * On utilise ce constructeur pour l'affichage des exceptions.
     * @param frame FenetAffiche
     * @param ex Exception
     */
    public FenetDialogues(FenetAffiche frame, Exception ex) {
        JOptionPane.showOptionDialog(frame,
                ex.toString(),
                "Erreur !",
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.ERROR_MESSAGE,
                null,
                optionsErreur,
                optionsErreur[0]);
    }

    /**
     * Constructeur de class, prend en paramètre la FenetAffiche associée et
     * le type de message.
     * On utilise ce constructeur pour la fenetre de dialogue A propos
     * @param frame FenetAffiche
     * @param type int
     */
    public FenetDialogues(FenetAffiche frame, int type) {
        JOptionPane.showMessageDialog(frame, message, "À propos", JOptionPane.PLAIN_MESSAGE);
    }
}
