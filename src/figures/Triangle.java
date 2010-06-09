/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *30 mai 2010
 * @author agindre
 */
public class Triangle extends FigureGraphique implements Serializable {

    protected Point_2D[] points;
    protected Point_2D centre = new Point_2D();
    public static int nbTri = 0;

    public Triangle() {
        super();
    }

    public Triangle(String nom, Color cc, Color cr, Point_2D[] points) {
        super(nom, cc, cr);
        this.points = points;
        centre.x = (points[0].x + points[1].x + points[2].x) / 3;
        centre.y = (points[0].y + points[1].y + points[2].y) / 3;
        nbTri += 1;
    }

    public int[] getYTab() {
        int res[] = new int[3];
        for (int i=0; i < 3; i++) {
            res[i] = points[i].y;
        }
        return res;
    }

    public int[] getXTab() {
        int[] res = new int[3];
        for(int i=0; i < 3; i++) {
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
    }

    public void translate(Point_2D p) {
        centre.x = centre.x + p.x;
        centre.y = centre.y + p.y;
    }

    @Override
    public boolean contient(Point_2D p) {
        
        return false;
    }

}
