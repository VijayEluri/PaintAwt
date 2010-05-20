package fenetres;

import gestions.GestionDeplacementSouris;
import figures.Point_2D;
import figures.FigureGraphique;
import figures.Cercle;
import figures.Rectangle;
import controls.*;
import java.awt.*;
import java.util.Vector;

public class FenetAffiche  extends Frame {
	
	public ZoneDessin zd;
	public Graphics gx;
	public Vector <FigureGraphique>figs;
	private int xEnfonce,yEnfonce;
	public Color couranteCol;
	public Color couranteFgCol;
	//Variable de sauvegarde de la figure sélectionnée
	public FigureGraphique save;
	//Variable pour stocker le type de dessin à produire
	public String choice;
	//Le mouseMotionListener pour le pseudo drag and drop
	GestionDeplacementSouris motionListener = new GestionDeplacementSouris(this);
	
	public FenetAffiche(Vector <FigureGraphique>vec) {
		setSize(700,500);
		setTitle("Affichage de Figures Graphiques");
		setForeground(Color.black);
		setBackground(Color.GRAY);
		couranteCol=Color.blue;
		couranteFgCol = Color.blue;
		choice = "rectangle";
		figs = vec;
		add(zd = new ZoneDessin(this),"Center");
		//$$1
		addWindowListener(new ControleurFenet(this));
		//$$6
		add(new BarreOutils(this), "North");
                setMenuBar(new BarreMenu(this));
		setVisible(true);
		Graphics gx = zd.getGraphics();
		dessineFigs(gx);
	}
	
	public void dessineFigs(Graphics g){
		for (int i= 0; i< figs.size(); i++)
			figs.get(i).dessineToi(g);
	}

	public void changeCouleur(Color color)	
	{
		// faire le changement de couleur
		couranteCol = color;		
	}		


	public void boutonSourisEnfonce(int x, int y)	{
                xEnfonce = x;
                yEnfonce = y;
		save = null;
                
		//Test de la gestion de déplacement de figure
		//Je met une variable FigureGraphique pour save la derniere figure pouvant abriter les bons parametre
		//Pour l'instant c'est pour test avec la boucle for
		for (FigureGraphique current : figs) {
			Point_2D p = new Point_2D(x, y);
			if (current.contient(p)) {
				save = current;
				zd.addMouseMotionListener(motionListener);
			}
		}
		//$$3
		/*System.out.println("bouton gauche enfoncé x:" + x + " y: "+ y);
		Graphics g = zd.getGraphics();
		g.drawString("<xE : "+x+", yE : "+y+">",x,y);*/
	}

	public void boutonSourisRelache(int x, int y)	{	
		Graphics g = zd.getGraphics();
		//$$3
		//System.out.println("bouton gauche relaché x:" + x + " y: "+ y);
		//$$4
		if (save != null) {
			save.deplace(x,y);
			save.dessineToi(g);
			zd.removeMouseMotionListener(motionListener);
		} else {
			g.setColor(couranteCol);
			if (choice.compareTo("cercle") == 0) {				
				g.drawOval(xEnfonce, yEnfonce, Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
				//$$5
				int rayon = Math.min(Math.abs(Math.abs(x - xEnfonce) / 2), Math.abs(y - yEnfonce));
				Cercle cercle = new Cercle("c" + Cercle.nbCercle, couranteCol, couranteFgCol, xEnfonce, yEnfonce, rayon);
				figs.add(cercle);
			} else {
				Point_2D p = new Point_2D(Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
				g.drawRect(xEnfonce, yEnfonce, p.x, p.y);
				Rectangle rectangle = new Rectangle("r" + Rectangle.nbRect, couranteCol, couranteFgCol, xEnfonce, yEnfonce, p.x, p.y);
				figs.add(rectangle);
			}
		}
		zd.repaint();
	}
	
	public void boutonSourisDeplace(int x, int y) {
		Graphics g = zd.getGraphics();
		if (save != null) {
			save.deplace(x, y);
			save.dessineToi(g);
		} else {
			if (choice.compareTo("cercle") == 0) {
				g.drawOval(xEnfonce, yEnfonce, Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
				System.out.println("cer" + x + " " + y);
			} else { 
				g.drawRect(xEnfonce, yEnfonce, x, y);
				System.out.println("rect" + x + " " + y);
			}
		}
		zd.repaint();
	}
	
}
