package figures;

import java.awt.*;

public class Cercle extends FigureGraphique {

    /**
     * Rayon du cercle
     */
    private int rayon;
    /**
     * Variable de classe stockant le nomre de cercles créés
     */
    public static int nbCercle = 0;

    /**
     * Constructeur par défaut
     */
    public Cercle() {
        super();
    }

    /**
     * Constructeur valué, qui prend en paramètre le nom, les couleurs de
     * remplissage et de contour, les coordonnées du centre, et le rayon du
     * cercle
     * @param nom String
     * @param cc Color
     * @param cr Color
     * @param x int
     * @param y int
     * @param r int
     */
    public Cercle(String nom, Color cc, Color cr, int x, int y, int r) {
        super(nom, cc, cr, new Point_2D());
        centre = new Point_2D(x, y);
        saveCentre = new Point_2D(centre);
        rayon = r;
        nbCercle += 1;
    }

    /**
     * Affiche le contenu des propriétés de l'objet.
     * @return String
     */
    @Override
    public String toString() {
        return new String(" cercle : " + nom + " centre : " + centre.toString()
                + " rayon : " + rayon + "\n\t\t couleur remlissage: " + cr + " couleur contour " + cc);
    }

    /**
     * Accesseur du rayon du cercle
     * @return
     */
    public int getRayon() {
        return rayon;
    }

    /**
     * Mutateur du centre du cercle
     * @param centre Point_2D
     */
    public void setCentre(Point_2D centre) {
        this.centre = centre;
    }

    /**
     * Mutateur du rayon du cercle
     * @param rayon int
     */
    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    /**
     * Concretisation FigureGraphique
     * test de contenu
     * @param p Point_2D
     * @return
     */
    public boolean contient(Point_2D p) {
        return (Point_2D.distance(centre, p) < rayon);
    }

    /**
     * Fonction d'affichage du cercle dans la fenêtre d'affichage. Pour
     * respecter les paramètres, on affiche le cercle plein, de la couleur
     * cr, puis les contours, et le nom, de la couleur cc.
     * @param g Graphics
     */
    public void dessineToi(Graphics g) {
        g.setColor(cr);
        g.fillOval(getCentre().x - rayon, getCentre().y - rayon, rayon * 2, rayon * 2);
        g.setColor(cc);
        g.drawOval(getCentre().x - rayon, getCentre().y - rayon, rayon * 2, rayon * 2);
        g.drawString(nom, getCentre().x, getCentre().y);
    }

    /**
     * Fonction de déplacement du cercle. Déplace seulement le centre, le reste
     * des points étant calculé durant la fonction dessineToi
     * @param dx int
     * @param dy int
     */
    public void deplace(int dx, int dy) {
        centre.deplace(dx, dy);
        saveCentre = new Point_2D(centre);
    }

    /**
     * Fontion translate, qui permet de déplacer plusieurs objets
     * FigureGraphique selon les coordonnées d'un vecteur, présenté sous la
     * forme d'un Point_2D.
     * @param p Point_2D
     */
    public void translate(Point_2D p) {
        centre.x = saveCentre.getX()+ p.x;
        centre.y = saveCentre.getY() + p.y;
    }
}
