package figures;

import java.io.Serializable;

public class Point_2D implements Serializable {
    // attributs d'instance

    public int x;
    public int y;
    // attributs de classe
    public static int nbPoints = 0;
    public static final int epsilon = 5;

    /**
     * Constructeurs
     */
    // constructeur par d�faut
    public Point_2D() {
        x = 0;
        y = 0;
        nbPoints++;
    }

    // constructeur valu�
    public Point_2D(int x, int y) {
        this.x = x;
        this.y = y;
        nbPoints++;
    }

    // constructeur de copie
    public Point_2D(Point_2D p) {
        x = p.x;
        y = p.y;
        nbPoints++;
    }

    // nettoyeur avant destruction
    protected void finalize() {
        nbPoints--;
    }

    /**
     * Accesseurs
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int val) {
        x = val;
    }

    public void setY(int val) {
        y = val;
    }

    public static int getNbPoints() {
        return nbPoints;
    }

    /**
     * Affichage contenu
     */
    // toString est une m�thode classique en Java, elle est pr�sente
    // dans les objets de type Object, on pourra dinc ainsi l'utiliser
    // dans une �ventuelle Liste de points.
    @Override
    public String toString() {
        return new String(" x = " + getX() + " y = " + getY());
    }

    /**
     * op�rations sur un point
     */
    public void deplace(int dx, int dy) {
        this.setX(dx);
        this.setY(dy);
    }

    /**
     * M�thodes de classe : op�rations sur les points
     */
    public static double distance(Point_2D p1, Point_2D p2) {
        // on  remarquera que l� aussi on
        // utilise des m�thodes statiques
        // de l'objet Math : sqrt

        int dx = p1.getX() - p2.getX();
        int dy = p1.getY() - p2.getY();

        return Math.sqrt((dx * dx) + (dy * dy));
    }

    public double distance(Point_2D p) {
        // on  remarquera que l� aussi on
        // utilise des m�thodes statiques
        // de l'objet Math : sqrt

        int dx = x - p.getX();
        int dy = y - p.getY();

        return ((int) Math.sqrt((dx * dx) + (dy * dy)));
    }

    public static boolean equal(Point_2D p1, Point_2D p2) {
        return (Point_2D.distance(p1, p2) < epsilon);
    }
}
