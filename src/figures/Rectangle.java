package figures;

import java.awt.*;

public class Rectangle extends FigureGraphique {

    /**
     * Sommet du coin supérieur gauche du rectangle
     */
    protected Point_2D pgh;
    /**
     * Largeur du rectangle
     */
    protected int larg;
    /**
     * Hauteur du rectangle
     */
    protected int haut;
    /**
     * Variable de classe stockant le nombre de rectangles créés
     */
    public static int nbRect = 0;
    
    /**
     * Constructeur par défaut
     */
    public Rectangle() {
        super();
    }

    /**
     * Constructeur valué, qui outre les classiques couleurs et nom, prend en
     * paramètre les coordonnées du point supérieur gauche, ainsi que la largeur
     * et la hauteur du rectangle
     * @param nom String
     * @param cc Color
     * @param cr Color
     * @param x int
     * @param y int
     * @param larg int
     * @param haut int
     */
    public Rectangle(String nom, Color cc, Color cr, int x, int y, int larg, int haut) {
        super(nom, cc, cr, new Point_2D());
        pgh = new Point_2D(x, y);
        this.larg = larg;
        this.haut = haut;
        this.centre = new Point_2D(pgh.getX() + (larg) / 2, pgh.getY() + (haut) / 2);
        saveCentre = new Point_2D(centre);
        nbRect += 1;
    }

    /**
     * Affiche le contenu des propriétés de l'objet.
     * @return String
     */
    @Override
    public String toString() {
        return new String(" rectangle : " + nom + " point gauche haut : " + pgh.toString()
                + " largeur : " + larg + " hauteur : " + haut + "\n\t\t CC : " + cc + "\t CR : " + cr);

    }

    /**
     * Accesseur de la largeur du rectangle
     * @return int
     */
    public int getlarg() {
        return larg;
    }

    /**
     * Accesseur de la hauteur du rectangle
     * @return int
     */
    public int gethaut() {
        return haut;
    }

    /**
     * Fonction d'affichage du rectangle dans la fenêtre d'affichage. Pour
     * respecter les paramètres, on affiche le rectangle plein, de la couleur
     * cr, puis les contours, et le nom, de la couleur cc.
     * @param p Point_2D
     * @return boolean
     */
    public void dessineToi(Graphics g) {
        g.setColor(cr);
        g.fillRect(pgh.x, pgh.y, larg, haut);
        g.setColor(cc);
        g.drawRect(pgh.x, pgh.y, larg, haut);
        g.drawString(nom, getCentre().x, getCentre().y);
    }

    /**
     * Vérifie si un point est contenu dans un rectangle
     * @param p Point_2D
     * @return boolean
     */
    public boolean contient(Point_2D p) {
        return ((pgh.getX() < p.getX()) && (p.getX() < pgh.getX() + larg)
                && (pgh.getY() < p.getY()) && (p.getY() < pgh.getY() + haut));
    }
    
    /**
     * Fonction de déplacement du rectangle. Déplace d'abord le centre du
     * rectangle au point dont les coordonnées sont passés en paramètres. Puis
     * recalcule les positions des sommets à partir de la variable pgh et des
     * valeurs de la largeur et de la hauteur
     * @param dx int
     * @param dy int
     */
    public void deplace(int dx, int dy) {
       centre.deplace(dx, dy);
       saveCentre = new Point_2D(centre);
       pgh.setX(centre.getX() - (this.getlarg() / 2));
       pgh.setY(centre.getY() - (this.gethaut() / 2));
    }

    /**
     * Fontion translate, qui permet de déplacer plusieurs objets
     * FigureGraphique selon les coordonnées d'un vecteur, présenté sous la
     * forme d'un Point_2D.
     * @param dx int
     * @param dy int
     */
    public void translate(Point_2D p) {
        centre.x = saveCentre.x+ p.x;
        centre.y = saveCentre.y + p.y;
        pgh.setX(centre.getX() - (this.getlarg() / 2));
        pgh.setY(centre.getY() - (this.gethaut() / 2));
    }
}
