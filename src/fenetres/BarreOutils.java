package fenetres;

import controles.ControlItemCouleurFond;
import controles.ControlItemForme;
import controles.ControlItemCouleur;
import java.awt.*;

/**
 * Class permettant l'affichage des différentes options comme les couleurs de
 * la figure courante, la couleur de fond et la forme.
 */
public class BarreOutils extends Panel {

    /**
     * Constructeur de la class, prend en paramètre la FenetAffiche à qui
     * on associe la barre d'outil
     * @param frame
     */
    public BarreOutils(FenetAffiche frame) {

        Choice listeCouleur;
        Choice listeCouleurFg;
        Choice forme;

        String formes[] = {"rectangle", "cercle", "triangle", "polygone"};
        String libelleCouleur[] = {"bleu", "blanc", "rouge", "jaune", "vert", "noir"};

        Color couleurs[] = {Color.blue, Color.white, Color.red, Color.yellow, Color.green, Color.black};

        listeCouleur = new Choice();
        listeCouleurFg = new Choice();
        forme = new Choice();

        for (String current : libelleCouleur) {
            listeCouleur.addItem(current);
            listeCouleurFg.addItem(current);
        }

        for (String current : formes) {
            forme.addItem(current);
        }

        listeCouleur.select(0);
        listeCouleurFg.select(0);
        forme.select(0);

        add(new Label("Couleur du contour :"));
        add(listeCouleur);
        add(new Label("Couleur de fond :"));
        add(listeCouleurFg);
        add(new Label(" Forme de la figure :"));
        add(forme);

        listeCouleur.addItemListener(new ControlItemCouleur(couleurs, frame));
        listeCouleurFg.addItemListener(new ControlItemCouleurFond(couleurs, frame));
        forme.addItemListener(new ControlItemForme(formes, frame));
    }
}
