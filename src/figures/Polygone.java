/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *30 mai 2010
 * @author agindre
 */
public class Polygone extends FigureGraphique implements Serializable {

    protected List<Point_2D> points = new ArrayList();
    protected Point_2D centre = new Point_2D();
    protected int nbPoints;
    public static int nbPoly = 0;
    // Variable stockant la position des sommets de triangle par rapport au centre
    // Elle permet de faciliter les fonctions de déplacement
    protected List<Point_2D> posPoints = new ArrayList();
    protected List<Triangle> ssTriangle = new ArrayList();

    public Polygone() {
        super();
    }

    public Polygone(String nom, Color cc, Color cr, ArrayList<Point_2D> points, int nbPoints) {
        super(nom, cc, cr);
        this.points = points;
        this.nbPoints = nbPoints;
        for (Point_2D current : points) {
            centre.x += current.x;
            centre.y += current.y;
        }
        centre.x = centre.x / nbPoints;
        centre.y = centre.y / nbPoints;
        nbPoly += 1;
        for (Point_2D current : points) {
            posPoints.add(new Point_2D(centre.getX() - current.getX(), centre.getY() - current.getY()));
        }
        // TODO: Mettre la boucle for dans la fonction setSsTriangle ???
        for (Point_2D current : points) {
            setSsTriangle(current);
        }
        for (Point_2D currentPt : points) {
            for (Triangle currentTr : ssTriangle) {
                if (currentTr.contient(currentPt)) {
                    // TODO: Gérer l'exception des polygones concaves (c'toi le con dans la cave)
                    break;
                }
            }
        }
    }

    public int[] getYTab() {
        int[] res = new int[nbPoints];
        for (int i = 0; i < nbPoints; i++) {
            res[i] = points.get(i).getY();
        }
        return res;
    }

    public int[] getXTab() {
        int[] res = new int[nbPoints];
        for (int i = 0; i < nbPoints; i++) {
            res[i] = points.get(i).getX();
        }
        return res;
    }

    @Override
    public void dessineToi(Graphics g) {
        // installer la couleur de remplissage du rectangle
        g.setColor(cr);
        // dessiner l'interieur du rectangle
        g.fillPolygon(getXTab(), getYTab(), nbPoints);
        // installer la couleur de contour du rectangle
        g.setColor(cc);
        // dessiner le contour du rectangle
        g.drawPolygon(getXTab(), getYTab(), nbPoints);
        // afficher le nom de la figure � partir de son centre
        g.drawString(nom, getCentre().x, getCentre().y);
    }

    @Override
    public double surface() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Point_2D getCentre() {
        return centre;
    }

    @Override
    public void deplace(int dx, int dy) {
        centre.deplace(dx, dy);
        for (int i = 0; i < nbPoints; i++) {
            points.get(i).deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
        }
    }

    public void translate(Point_2D p) {
        centre.x = centre.x + p.x;
        centre.y = centre.y + p.y;
    }

    /**
     * Fonction contient
     * @param p
     * @return un booléen
     * Indique si un point appartient à un polygone convexe
     * En cas de polygone concave, un exception est générée
     * Fonctionnement :
     */
    @Override
    public boolean contient(Point_2D p) {
        boolean flag = false;
        for (Triangle currentTr : ssTriangle) {
            if (currentTr.contient(p)) {
               flag = true;
               break;
            }
        }
        return flag;
    }

    private void setSsTriangle(Point_2D p) {
        Point_2D prec = new Point_2D(points.get(((points.indexOf(p) - 1) % nbPoints) == -1 ? nbPoints -1 : (points.indexOf(p) - 1) % nbPoints));
        Point_2D suiv = new Point_2D(points.get((points.indexOf(p) + 1) % nbPoints));
        // TODO: Vérifier qu'on puisse pas faire une initialisation de l'ArrayList sans passer par les add
        ArrayList<Point_2D> listPts = new ArrayList();
        listPts.add(prec);
        listPts.add(suiv);
        listPts.add(centre);

        ssTriangle.add(new Triangle(listPts));
    }
}
