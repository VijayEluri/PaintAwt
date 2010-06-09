package fenetres;

import controls.ControlMenuItemLoad;
import controls.ControlMenuItemNew;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import controls.ControlMenuItemExit;
import controls.ControlMenuItemSave;
/**
 *
 * @author grimm
 */
public class BarreMenu extends MenuBar {

    //Declaration des variables de Menu
    private MenuItem menuItemNouveau;
    private MenuItem menuItemQuitter;
    private MenuItem menuItemSauvegarde;
    private MenuItem menuItemCharger;
    private MenuItem menuItemAPropos;
    private MenuItem menuItemAide;

    public BarreMenu(FenetAffiche frame) {

        Menu menuFichier = new Menu("Fichier");
        Menu menuAide = new Menu("Aide");

        menuItemNouveau = new MenuItem("Nouveau");
        menuItemQuitter = new MenuItem("Quitter");
        menuItemSauvegarde = new MenuItem("Sauvegarde");
        menuItemCharger = new MenuItem("Charger");
        menuItemAPropos = new MenuItem("À propose");
        menuItemAide = new MenuItem("Aide");

        //Ajout de actionListener à faire sur les menuItemNouveau
        menuItemQuitter.addActionListener(new ControlMenuItemExit(frame));
        menuItemSauvegarde.addActionListener(new ControlMenuItemSave(frame));
        menuItemCharger.addActionListener(new ControlMenuItemLoad(frame));
        menuItemNouveau.addActionListener(new ControlMenuItemNew((frame)));

        menuFichier.add(menuItemNouveau);
        menuFichier.add(menuItemSauvegarde);
        menuFichier.add(menuItemCharger);
        menuFichier.add(menuItemQuitter);

        menuAide.add(menuItemAide);
        menuAide.add(menuItemAPropos);

        add(menuFichier);
        add(menuAide);
    }
}
