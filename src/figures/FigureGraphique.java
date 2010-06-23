package figures;

import java.awt.*;
import java.io.Serializable;

public abstract class FigureGraphique implements Figure, Serializable {
        /**
         * Variables des couleurs de remplissages et de contours
         */
	public Color cc, cr;
        /**
         * Nom de la figure
         */
        public String nom;
        /**
         * Variable temporaire lors du déplacement du centre pour les polynomes
         * notamment
         */
        protected Point_2D saveCentre;
        /**
         * Centre de la figure graphique
         */
        protected Point_2D centre;
	
        /**
         * Constructeur par défaut
         */
        public FigureGraphique() {}
        /**
         * Constructeur de la figure graphique. Permet de générer une figure
         * graique à partir de son nom, de ses couleurs de remplissage et de
         * contours et du centre de la figure
         * @param nom
         * @param cc
         * @param cr
         * @param centre
         */
        public FigureGraphique(String nom, Color cc, Color cr, Point_2D centre)	{
		this.cc = cc;	
		this.cr = cr;	
		this.nom = nom;
                this.centre = centre;
                this.saveCentre = centre;
	}

        /**
         * Accesseur du centre de la figure graphique
         * @return Point_2D
         */
        public Point_2D getCentre() {
            return centre;
        }

        /**
         * Calcule la distance entre 2 figures, ou plus précisement, entre
         * chaque centre des figures
         * @param f1
         * @param f2
         * @return double
         */
        public static double distance(Figure f1, Figure f2) {
		return Point_2D.distance(f1.getCentre(), f2.getCentre());
	}

        /**
         * Fonction d'affichage de la figure dans la fenêtre d'affichage. Pour
         * respecter les paramètres, on affiche le triangle plein, de la couleur cr,
         * puis les contours, et le nom, de la couleur cc.
         * @param g
         */
        public abstract void dessineToi(Graphics g);

        /**
         * Fontion translate, qui permet de déplacer plusieurs objets
         * FigureGraphique selon les coordonnées d'un vecteur, présenté sous la
         * forme d'un Point_2D.
         * @param p
         */
        public abstract void translate(Point_2D p);
}