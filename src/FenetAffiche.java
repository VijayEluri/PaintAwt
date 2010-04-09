import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Vector;

class FenetAffiche  extends Frame {	
	
	ZoneDessin zd;
	Graphics gx;
	Vector <FigureGraphique>figs;
	int xEnfonce,yEnfonce;
	Color couranteCol;
	//Variable de sauvegarde de la figure sélectionnée
	FigureGraphique save;
	
	//Le mouseMotionListener pour le pseudo drag and drop
	GestionDeplacementSouris motionListener = new GestionDeplacementSouris(this);
	
	public FenetAffiche(Vector <FigureGraphique>vec)	{
		setSize(700,500);
		setTitle("Affichage de Figures Graphiques");
		setForeground(Color.blue);
		setBackground(Color.pink);
		couranteCol=Color.blue;
		figs = vec;
		add(zd = new ZoneDessin(this),"Center");
		//$$1
		addWindowListener(new ControleurFenet(this));
		//$$6
		add(new BarreOutils(this), "North");
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
		System.out.println("bouton gauche relaché x:" + x + " y: "+ y);
		//$$4
		/*g.setColor(couranteCol);
		g.drawOval(xEnfonce, yEnfonce, Math.abs(x - xEnfonce), Math.abs(y - yEnfonce));
		//$$5
		int rayon = Math.min(Math.abs(Math.abs(x - xEnfonce) / 2), Math.abs(y - yEnfonce));
		Cercle cercle = new Cercle("New", couranteCol, Color.white, xEnfonce, yEnfonce, rayon);
		figs.add(cercle);*/
		if (save != null) {
			save.deplace(x,y);
			save.dessineToi(g);
			zd.removeMouseMotionListener(motionListener);
			dessineFigs(g);
		}
	}
	
	public void boutonSourisDeplace(int x, int y) {
		Graphics g = zd.getGraphics();
		save.deplace(x, y);
		save.dessineToi(g);
		
	}

}