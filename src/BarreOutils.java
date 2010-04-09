import java.awt.*;

class BarreOutils  extends Panel	{
	public BarreOutils(FenetAffiche frame)	{
	//$6
		Choice listeCouleur;
		Choice forme;
		
		String formes[] = {"rectangle", "cercle"};
		String libelleCouleur[] = {"blanc","rouge", "jaune", "vert", "bleu", "noir"};
		
		Color couleurs[] = {Color.white, Color.red, Color.yellow, Color.green, Color.blue, Color.black};
		
		listeCouleur = new Choice();
		forme = new Choice();
		
		for (String current : libelleCouleur) {
			listeCouleur.addItem(current);
		}
		
		for (String current : formes) {
			forme.addItem(current);
		}
		
		listeCouleur.select(0);
		forme.select(0);
		
		add(listeCouleur);
		add(forme);
		
		listeCouleur.addItemListener(new ControlItemCouleur(couleurs, frame));
		forme.addItemListener(new ControlItemForme(formes, frame));
	}
	
}
