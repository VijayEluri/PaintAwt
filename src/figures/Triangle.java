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
public class Triangle extends FigureGraphique implements Serializable {

    protected Point_2D[] points;
    protected Point_2D centre = new Point_2D();
    public static int nbTri = 0;
    // Variable stockant la position des sommets de triangle par rapport au centre
    // Elle permet de faciliter les fonctions de déplacement
    protected List<Point_2D> posPoints = new ArrayList();

    public Triangle() {
        super();
    }

    public Triangle(String nom, Color cc, Color cr, Point_2D[] points) {
        super(nom, cc, cr);
        this.points = points;
        centre.x = (points[0].x + points[1].x + points[2].x) / 3;
        centre.y = (points[0].y + points[1].y + points[2].y) / 3;

        for (int i = 0; i < 3; i++) {
            posPoints.add(new Point_2D(centre.getX() - points[i].getX(), centre.getY() - points[i].getY()));
        }
        nbTri += 1;
    }

    
    public Triangle(ArrayList<Point_2D> listePoints) {
        super();
        this.points = new Point_2D[3];
        for (int i = 0; i < 3; i++) {
            points[i] = listePoints.get(i);
        }
        centre.x = (points[0].x + points[1].x + points[2].x) / 3;
        centre.y = (points[0].y + points[1].y + points[2].y) / 3;

        for (int i = 0; i < 3; i++) {
            posPoints.add(new Point_2D(centre.getX() - points[i].getX(), centre.getY() - points[i].getY()));
        }
        nbTri += 1;
    }

    public int[] getYTab() {
        int res[] = new int[3];
        for (int i = 0; i < 3; i++) {
            res[i] = points[i].y;
        }
        return res;
    }

    public int[] getXTab() {
        int[] res = new int[3];
        for (int i = 0; i < 3; i++) {
            res[i] = points[i].x;
        }
        return res;
    }

    @Override
    public void dessineToi(Graphics g) {
        // installer la couleur de remplissage du rectangle
        g.setColor(cr);
        // dessiner l'interieur du rectangle
        g.fillPolygon(getXTab(), getYTab(), 3);
        // installer la couleur de contour du rectangle
        g.setColor(cc);
        // dessiner le contour du rectangle
        g.drawPolygon(getXTab(), getYTab(), 3);
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
        for (int i = 0; i < 3; i++) {
            points[i].deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
        }
    }

    public void translate(Point_2D p) {
        centre.setX(centre.getX() + p.getX());
        centre.setY(centre.getY() + p.getY());
        for (int i = 0; i < 3; i++) {
            points[i].deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
        }
    }

    // Pour faciliter les calculs dans la fonction contient, on passe par une fonction intermédiaire
    private int calcPosition(Point_2D s1, Point_2D s2, Point_2D pos) {
        return s1.getX() * (s2.getY() - pos.getY()) + s2.getX() * (pos.getY() - s1.getY()) + pos.getX() * (s1.getY() - s2.getY());
    }

    @Override
    public boolean contient(Point_2D p) {
        int i1, i2, i3;

        i1 = calcPosition(points[0], points[1], p);
        i2 = calcPosition(points[1], points[2], p);
        i3 = calcPosition(points[2], points[0], p);

        return ((i1 > 0) && (i2 > 0) && (i3 > 0)) || ((i1 < 0) && (i2 < 0) && (i3 < 0));
    }
}
