package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Triangle extends FigureGraphique implements Serializable {

    /**
     * Tableau des points du triangle
     */
    protected Point_2D[] points;
    /**
     * Variable de classe stockant le nombre de triangles créés
     */
    public static int nbTri = 0;
    /**
     * Variable stockant la position des sommets de triangle par rapport au
     * centre. Elle permet de faciliter les fonctions de déplacement.
     */
    protected List<Point_2D> posPoints = new ArrayList();

    /**
     * Constructeur par défaut
     */
    public Triangle() {
        super();
    }

    /**
     * Constructeur le plus utilisé. Crée un triangle à partir du nom, des
     * couleurs de remplissage et de contours, et du tableau de points
     * @param nom String
     * @param cc Color
     * @param cr Color
     * @param points Point_2D[]
     */
    public Triangle(String nom, Color cc, Color cr, Point_2D[] points) {
        super(nom, cc, cr, new Point_2D());
        this.points = points;
        centre.x = (points[0].x + points[1].x + points[2].x) / 3;
        centre.y = (points[0].y + points[1].y + points[2].y) / 3;
        saveCentre = new Point_2D(centre);
        for (int i = 0; i < 3; i++) {
            posPoints.add(new Point_2D(centre.getX() - points[i].getX(), centre.getY() - points[i].getY()));
        }
        nbTri += 1;
    }

    
    /**
     * Constructeur utilisé lors de la création des polygones, pour créer les
     * sous-triangles. On a seulement besoin d'une liste de points. On ne met
     * par contre pas à jour la variable nbTri, vu que les triangles sont
     * invisibles poour l'utilisateur
     * @param listePoints ArrayList<Point_2D>
     */
    public Triangle(ArrayList<Point_2D> listePoints) {
        super();
        this.points = new Point_2D[3];
        for (int i = 0; i < 3; i++) {
            points[i] = listePoints.get(i);
        }
        centre = new Point_2D();
        centre.setX((points[0].getX() + points[1].getX() + points[2].getX()) / 3);
        centre.setY((points[0].getY() + points[1].getY() + points[2].getY()) / 3);

        for (int i = 0; i < 3; i++) {
            posPoints.add(new Point_2D(centre.getX() - points[i].getX(), centre.getY() - points[i].getY()));
        }
    }

    /**
     * Retourne le tableau des ordonnées de tous les sommets du triangle
     * @return int[]
     */
    public int[] getYTab() {
        int res[] = new int[3];
        for (int i = 0; i < 3; i++) {
            res[i] = points[i].getY();
        }
        return res;
    }

    /**
     * Retourne le tableau des abscisses de tous les sommets du triangle
     * @return int[]
     */
    public int[] getXTab() {
        int[] res = new int[3];
        for (int i = 0; i < 3; i++) {
            res[i] = points[i].getX();
        }
        return res;
    }

    /**
     * Vérifier si un point est contenu dans un triangle.
     * @param p Point_2D
     * @return boolean
     */
    @Override
    public boolean contient(Point_2D p) {
        int i1, i2, i3;

        i1 = calcPosition(points[0], points[1], p);
        i2 = calcPosition(points[1], points[2], p);
        i3 = calcPosition(points[2], points[0], p);

        return ((i1 > 0) && (i2 > 0) && (i3 > 0)) || ((i1 < 0) && (i2 < 0) && (i3 < 0));
    }

    /**
     * Pour faciliter les calculs dans la fonction contient, on passe par une
     * fonction intermédiaire
     * @param s1 Point_2D
     * @param s2 Point_2D
     * @param pos Point_2D
     * @return int
     */
    private int calcPosition(Point_2D s1, Point_2D s2, Point_2D pos) {
        return s1.getX() * (s2.getY() - pos.getY()) + s2.getX() * (pos.getY() - s1.getY()) + pos.getX() * (s1.getY() - s2.getY());
    }

    /**
     * Fonction d'affichage du triangle dans la fenêtre d'affichage. Pour
     * respecter les paramètres, on affiche le triangle plein, de la couleur cr,
     * puis les contours, et le nom, de la couleur cc.
     * @param g Graphics
     */
    @Override
    public void dessineToi(Graphics g) {
        g.setColor(cr);
        g.fillPolygon(getXTab(), getYTab(), 3);
        g.setColor(cc);
        g.drawPolygon(getXTab(), getYTab(), 3);
        g.drawString(nom, getCentre().x, getCentre().y);
    }

    /**
     * Fonction de déplacements des triangles, à la position (dx;dy) donnée en
     * paramètre. Pour effectuer cela, on déplace le centre, et on recalcule la
     * poisition des autres points via la variable posPoints.
     * @param dx
     * @param dy
     */
    @Override
    public void deplace(int dx, int dy) {
        centre.deplace(dx, dy);
        saveCentre = new Point_2D(centre);
        for (int i = 0; i < 3; i++) {
            points[i].deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
        }
    }

    /**
     * Fontion translate, qui permet de déplacer plusieurs objets
     * FigureGraphique selon les coordonnées d'un vecteur, présenté sous la
     * forme d'un Point_2D.
     * @param p Point_2D
     */
    public void translate(Point_2D p) {
        centre.setX(saveCentre.getX() + p.getX());
        centre.setY(saveCentre.getY() + p.getY());
        for (int i = 0; i < 3; i++) {
            points[i].deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
        }
    }
}
