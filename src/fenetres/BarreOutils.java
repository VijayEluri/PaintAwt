package fenetres;

import controls.ControlItemCouleurFond;
import controls.ControlItemForme;
import controls.ControlItemCouleur;
import java.awt.*;

public class BarreOutils  extends Panel	{
	public BarreOutils(FenetAffiche frame)	{
	//$6
		Choice listeCouleur;
		Choice listeCouleurFg;
		Choice forme;
		
		String formes[] = {"rectangle", "cercle"};
		String libelleCouleur[] = {"bleu" ,"blanc","rouge", "jaune", "vert", "noir"};
		
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
