package figures;

import java.io.Serializable;

public class Point_2D implements Serializable {
    /**
     * Abscisse du point
     */
    public int x;
    /**
     * Ordonnée du point
     */
    public int y;

    /**
     * Variable de classe comptant le nombre de créés
     */
    public static int nbPoints = 0;
    /**
     * Taux de marge lors du calcul de la distance entre deux points
     */
    public static final int epsilon = 5;

    /**
     * Constructeur par défaut
     */
    public Point_2D() {
        x = 0;
        y = 0;
        nbPoints++;
    }

    /**
     * Constructeur valué, qui reçoit en paramètre les coordonnées du point de
     * création.
     * @param x int
     * @param y int
     */
    public Point_2D(int x, int y) {
        this.x = x;
        this.y = y;
        nbPoints++;
    }

    /**
     * Constructeur par copie
     * @param p Point_2D
     */
    public Point_2D(Point_2D p) {
        x = p.x;
        y = p.y;
        nbPoints++;
    }

    /**
     * Nettoyeur avant destruction de l'objet correspondant
     */
    @Override
    protected void finalize() {
        nbPoints--;
    }

    /**
     * Accesseur de l'abscisse du point
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Accesseur de l'ordonnée du point
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Mutateur de l'abscisse du point
     * @param val int
     */
    public void setX(int val) {
        x = val;
    }

    /**
     * Mutateur de l'ordonnée du point
     * @param val int
     */
    public void setY(int val) {
        y = val;
    }

    /**
     * Retourne le nombre de points créés, valeur de la variable de classe
     * nbPoints.
     * @return
     */
    public static int getNbPoints() {
        return nbPoints;
    }

    /**
     * Affiche le contenu des propriétés de l'objet.
     * @return String
     */
    @Override
    public String toString() {
        return new String(" x = " + getX() + " y = " + getY());
    }

    /**
     * Permet de déplacer un point directement aux coordonnées (dx;dy).
     * @param dx int
     * @param dy int
     */
    public void deplace(int dx, int dy) {
        this.setX(dx);
        this.setY(dy);
    }

    /**
     * Méthode de classe permettant de calculer la distance entre deux points.
     * @param p1 Point_2D
     * @param p2 Point_2D
     * @return double
     */
    public static double distance(Point_2D p1, Point_2D p2) {
        int dx = p1.getX() - p2.getX();
        int dy = p1.getY() - p2.getY();

        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Méthode permettant de calculer la distance entre le point courant et un
     * autre point.
     * @param p Point_2D
     * @return double
     */
    public double distance(Point_2D p) {
        int dx = x - p.getX();
        int dy = y - p.getY();

        return ((int) Math.sqrt((dx * dx) + (dy * dy)));
    }

    /**
     * Méthode de classe permettant de savoir si deux points sont égaux,
     * c'est-à-dire si la distance les séparant est inférieur à un epsilon donné
     * , ici 5.
     * @param p1 Point_2D
     * @param p2 Point_2D
     * @return boolean
     */
    public static boolean equal(Point_2D p1, Point_2D p2) {
        return (Point_2D.distance(p1, p2) < epsilon);
    }
}
