import java.awt.*;


public class Rectangle extends FigureGraphique
{
	protected Point_2D pgh;
	protected int larg,haut;
	protected static int nbRect = 0;

	/**
	 * Constructeurs
	 */
	public Rectangle() {super();}
	// constructeur valu� : position + rayon
	public Rectangle(String nom,Color cc, Color cr,int x, int y, int larg,int haut)
	{
		super(nom,cc,cr);
		pgh = new Point_2D(x,y);
		this.larg = larg;
		this.haut = haut;
		nbRect += 1;
	}

	/**
	 * Accesseurs
	 */

	/**
	 * Concretisation Figure
	 * textualisation d'un rectangle
	 */
	public String toString(){
		return new String(" rectangle : "+ nom +" point gauche haut : "+ pgh.toString()+
			" largeur : "+ larg+" hauteur : "+ haut+"\n\t\t CC : "+cc+ "\t CR : "+cr );

		}


	/**
	 * calcul du centre du rectangle
	 */
	public Point_2D getCentre()
	{	return new Point_2D (pgh.getX()+(larg)/2,pgh.getY()+(haut)/2);	}

	public int getlarg()
	{	return larg;	}

	public int gethaut()
	{	return haut;	}

	/**
	 * D�placement du cercle = deplacement du centre
	 */
	public void deplace(int dx, int dy)
	{	pgh.deplace(dx,dy);	}

	/**
	 * test de contenu
	 */
	public boolean contient(Point_2D p)
	{	return ((pgh.getX()< p.getX())&& ( p.getX()< pgh.getX()+ larg)&&
		(pgh.getY()< p.getY())&& ( p.getY()< pgh.getY()+ haut));
	}

	public double surface(){
		return larg*haut;
	}


	public void dessineToi (Graphics g){
		// installer la couleur de remplissage du rectangle
		g.setColor(cr);              
		// dessiner l'interieur du rectangle
		g.fillRect(pgh.x,pgh.y,larg,haut); 
		// installer la couleur de contour du rectangle
		g.setColor(cc);
		// dessiner le contour du rectangle
		g.drawRect(pgh.x,pgh.y,larg,haut) ;
		// afficher le nom de la figure � partir de son centre 
		g.drawString(nom,getCentre().x,getCentre().y);    
	}
}	