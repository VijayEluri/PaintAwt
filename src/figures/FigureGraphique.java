package figures;

import java.awt.*;
import java.io.Serializable;

public abstract class FigureGraphique implements Figure,Serializable {
	Color cc, cr;
	public String nom;
        protected Point_2D saveCentre;
	
	public FigureGraphique() {}
	public FigureGraphique (String nom, Color cc, Color cr, Point_2D saveCentre)	{
		this.cc = cc;	
		this.cr = cr;	
		this.nom = nom;
                this.saveCentre = saveCentre;
	}

	public Color getcc()
	{	return cc;	}

	public Color getcr()
	{	return cr;	}

	public static double distance(Figure f1, Figure f2)
	{
		return Point_2D.distance(f1.getCentre(), f2.getCentre());
	}

	public abstract void dessineToi(Graphics g);

        public abstract void translate(Point_2D p);
}