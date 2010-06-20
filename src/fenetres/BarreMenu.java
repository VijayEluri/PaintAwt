package fenetres;

import controls.ControlMenuItemAbout;
import controls.ControlMenuItemLoad;
import controls.ControlMenuItemNew;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import controls.ControlMenuItemExit;
import controls.ControlMenuItemSave;

/**
 * Class qui gère la création et la mise en place du menu de l'application
 */
public class BarreMenu extends MenuBar {

    //Declaration des variables de Menu
    private MenuItem menuItemNouveau;
    private MenuItem menuItemQuitter;
    private MenuItem menuItemSauvegarde;
    private MenuItem menuItemCharger;
    private MenuItem menuItemAPropos;

    /**
     * Constructeur de class, prend en paramètre la FenetAffiche à qui on va
     * associer la barre de menu
     * @param frame FenetAffiche
     */
    public BarreMenu(FenetAffiche frame) {

        Menu menuFichier = new Menu("Fichier");
        // TODO: remplir les sous menu de Aide
        Menu menuAide = new Menu("Aide");

        menuItemNouveau = new MenuItem("Nouveau");
        menuItemQuitter = new MenuItem("Quitter");
        menuItemSauvegarde = new MenuItem("Sauvegarder");
        menuItemCharger = new MenuItem("Charger");
        menuItemAPropos = new MenuItem("À propose");

        //Ajout de actionListener pour chacun des item
        menuItemQuitter.addActionListener(new ControlMenuItemExit(frame));
        menuItemSauvegarde.addActionListener(new ControlMenuItemSave(frame));
        menuItemCharger.addActionListener(new ControlMenuItemLoad(frame));
        menuItemNouveau.addActionListener(new ControlMenuItemNew((frame)));
        menuItemAPropos.addActionListener(new ControlMenuItemAbout(frame));

        menuFichier.add(menuItemNouveau);
        menuFichier.add(menuItemSauvegarde);
        menuFichier.add(menuItemCharger);
        menuFichier.add(menuItemQuitter);

        menuAide.add(menuItemAPropos);

        add(menuFichier);
        add(menuAide);
    }
}
