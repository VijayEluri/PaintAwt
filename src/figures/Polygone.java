/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figures;

import exceptions.PolygoneConcave;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *30 mai 2010
 * @author agindre
 */
public class Polygone extends FigureGraphique {

    protected List<Point_2D> points = new ArrayList();
    protected Point_2D centre = new Point_2D();
    protected int nbPoints;
    public static int nbPoly = 0;
    // Variable stockant la position des sommets de triangle par rapport au centre
    // Elle permet de faciliter les fonctions de déplacement
    protected List<Point_2D> posPoints = new ArrayList();
    protected List<Triangle> ssTriangles = new ArrayList();

    public Polygone() {
        super();
    }

    public Polygone(String nom, Color cc, Color cr, ArrayList<Point_2D> points, int nbPoints) throws PolygoneConcave {
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
                    throw new PolygoneConcave();
                }
            }
        }
        setSsTriangles();
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
        // installer la couleur de remplissage du polygone
        g.setColor(cr);
        // dessiner l'interieur du polygone
        g.fillPolygon(getXTab(), getYTab(), nbPoints);
        // installer la couleur de contour du polygone
        g.setColor(cc);
        // dessiner le contour du polygone
        g.drawPolygon(getXTab(), getYTab(), nbPoints);
        // afficher le nom de la figure a partir de son centre
        g.drawString(nom, getCentre().getX(), getCentre().getY());
        for (Triangle current: ssTriangles) {
            current.dessineToi(g);
        }
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
        //int dxCentre = dx - centre.getX();
        //int dyCentre = dy - centre.getY();
        //int dxTriangle, dyTriangle;

        // TODO: Attention, méthode de tchétchène, c'est dégueulasse, c'est crade, c'est bourrin, mais ça marche
        centre.deplace(dx, dy);
        for (int i = 0; i < nbPoints; i++) {
            points.get(i).deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
            for (Triangle current: ssTriangles) {
                //dxTriangle = current.getCentre().getX() + dxCentre;
                //dyTriangle = current.getCentre().getY() + dyCentre;
                //System.out.println(current.nom + " : dx = " + dxTriangle + ", dy = " + dyTriangle);
                //current.getCentre().deplace(dxTriangle, dyTriangle);
                //System.out.println(current.nom + " : dx = " + current.getCentre().getX() + ", dy = " + current.getCentre().getY());

                current.getCentre().setX((current.points[0].getX() + current.points[1].getX() + current.points[2].getX()) / 3);
                current.getCentre().setY((current.points[0].getY() + current.points[1].getY() + current.points[2].getY()) / 3);
            }
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
        for (Triangle currentTr : ssTriangles) {
            if (currentTr.contient(p)) {
               flag = true;
               break;
            }
        }
        return flag;
    }

    private void setSsTriangles() {
        for (Point_2D current: points) {
            //Point_2D suiv = new Point_2D(points.get((points.indexOf(current) + 1) % nbPoints));
            //Point_2D centrePol = new Point_2D(centre);
            // TODO: Vérifier qu'on puisse pas faire une initialisation de l'ArrayList sans passer par les add
            ArrayList<Point_2D> listPts = new ArrayList();
            // On initialise un nouveau point pour ne pas utiliser une référence de current
            listPts.add(current);
            listPts.add(points.get((points.indexOf(current) + 1) % nbPoints));
            listPts.add(centre);

            Triangle testTriangle = new Triangle(listPts, Color.RED, "Test", Color.WHITE);
            ssTriangles.add(testTriangle);
        }
    }

    private boolean estConcave() {
        //TODO: Cette méthode de vérification ne marche pas à 100%. En cas de polygône qui a deux segments qui se croisent (cf |><|), ça marche pas

        List<Triangle> trigConcave = new ArrayList();

        for (Point_2D current: points) {
            Point_2D prec = new Point_2D(points.get(((points.indexOf(current) - 1) % nbPoints) == -1 ? nbPoints -1 : (points.indexOf(current) - 1) % nbPoints));
            Point_2D suiv = new Point_2D(points.get((points.indexOf(current) + 1) % nbPoints));
            // TODO: Vérifier qu'on puisse pas faire une initialisation de l'ArrayList sans passer par les add
            ArrayList<Point_2D> listPts = new ArrayList();
            listPts.add(prec);
            listPts.add(suiv);
            listPts.add(centre);

            Triangle testTriangle = new Triangle(listPts, Color.RED, "Test", Color.WHITE);
            trigConcave.add(testTriangle);

        }
        for (Point_2D currentPt : points) {
            for (Triangle currentTr: trigConcave) {
                if (currentTr.contient(currentPt)) {
                    // TODO: Gérer l'exception des polygones concaves (c'toi le con dans la cave)
                    trigConcave.clear();
                    return true;
                }
            }
        }
        trigConcave.clear();
        return false;
    }
}
