import java.awt.*;

class BarreOutils  extends Panel	{
	public BarreOutils(FenetAffiche frame)	{
	//$6
		Choice listeCouleur;
		String libelleCouleur[] = {"blanc","rouge", "jaune", "vert", "bleu", "noir"};
		Color couleurs[] = {Color.white, Color.red, Color.yellow, Color.green, Color.blue, Color.black};
		listeCouleur = new Choice();
		for (String current : libelleCouleur) {
			listeCouleur.addItem(current);
		}
		listeCouleur.select(0);
		add(listeCouleur);
		listeCouleur.addItemListener(new ControlItemCouleur(couleurs, frame));
	}
	
}
