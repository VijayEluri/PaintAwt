package figures;

import exceptions.PolygoneConcave;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Polygone permet de dessiner dans la fenêtre des polygones
 * irréguliers convexes. Elle dispose de toutes les méthodes des figures
 * graphiques.
 */
public class Polygone extends FigureGraphique {

    /**
     * Liste des points formant le polygône
     */
    protected List<Point_2D> points = new ArrayList();
    /**
     * Entier stockant le nombre de points du polygone.
     */
    protected int nbPoints;
    /**
     * Variable de classe stockant le nombre de polygones créés. Est utile lors
     * de la création automatique des noms des polygones.
     */
    public static int nbPoly = 0;
    /**
     * Liste des triangles créés par deux points contigüs du polygone et son
     * centre. Elle facilite les fonctions de déplacements et de translations
     */
    protected List<Triangle> ssTriangles = new ArrayList();
    /**
     * Variable stockant la position des sommets de triangle par rapport au
     * centre. Elle permet de faciliter les fonctions de déplacement.
     */
    protected List<Point_2D> posPoints = new ArrayList();

    /**
     * Constructeur par défaut
     */
    public Polygone() {
        super();
    }

    /**
     * Constructeur.
     * Crée un objet polygone à partir de son nom, de ses couleurs (cc et cr),
     * et de la liste des points formant ce polygone. On vérifie que le polygone
     * ainsi instancié est concave, auquel cas une exception est générée. Enfin
     * on crée les triangles composants le polygone, et qui permettront de
     * vérifier si un point est contenu dans la figure, lors de la fonction de
     * déplacement.
     * @param nom String
     * @param cc Color
     * @param cr Color
     * @param points ArrayList<Point_2D>
     * @param nbPoints int
     * @throws PolygoneConcave
     */
    public Polygone(String nom, Color cc, Color cr, ArrayList<Point_2D> points, int nbPoints) throws PolygoneConcave {
        super(nom, cc, cr, new Point_2D());
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
        if (this.estConcave()) {
            throw new PolygoneConcave();
        }

        setSsTriangles();
    }

    /**
     * Retourne le tableau des ordonnées de tous les sommets du polygone
     * @return int[]
     */
    public int[] getYTab() {
        int[] res = new int[nbPoints];
        for (int i = 0; i < nbPoints; i++) {
            res[i] = points.get(i).getY();
        }
        return res;
    }

    /**
     * Retourne le tableau des abscisses de tous les sommets du polygone
     * @return int[]
     */
    public int[] getXTab() {
        int[] res = new int[nbPoints];
        for (int i = 0; i < nbPoints; i++) {
            res[i] = points.get(i).getX();
        }
        return res;
    }

    /**
     * Fonction d'affichage du polygone dans la fenêtre d'affichage. Pour
     * respecter les paramètres, on affiche le polygone plein, de la couleur cr,
     * puis les contours, et le nom, de la couleur cc.
     * @param g Graphics
     */
    @Override
    public void dessineToi(Graphics g) {
        g.setColor(cr);
        g.fillPolygon(getXTab(), getYTab(), nbPoints);
        g.setColor(cc);
        g.drawPolygon(getXTab(), getYTab(), nbPoints);
        g.drawString(nom, getCentre().getX(), getCentre().getY());
    }

    /**
     * Indique si un point appartient à un polygone convexe. Pour cela, on
     * vérifie que le point appartient à un des sous-triangles.
     * @param p Point_2D
     * @return boolean
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

    /**
     * Fonction de déplacements des polygones, à la position (dx;dy) donnée en
     * paramètre. On doit également veillé à déplacer les triangles qui le
     * composent, afin de la fonction contient continue de fonctionner avec les
     * nouvelles coordonnées du polygone.
     * @param dx int
     * @param dy int
     */
    @Override
    public void deplace(int dx, int dy) {
        centre.deplace(dx, dy);
        saveCentre = new Point_2D(centre);
        for (int i = 0; i < nbPoints; i++) {
            points.get(i).deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
            for (Triangle current : ssTriangles) {
                current.getCentre().setX((current.points[0].getX() + current.points[1].getX() + current.points[2].getX()) / 3);
                current.getCentre().setY((current.points[0].getY() + current.points[1].getY() + current.points[2].getY()) / 3);
            }
        }
    }

    /**
     * Fontion translate, qui permet de déplacer plusieurs objets
     * FigureGraphique selon les coordonnées d'un vecteur, présenté sous la
     * forme d'un Point_2D. De même que pour la fonction déplacer, on doit
     * également déplacer les triangles qui composent de polygone.
     * @param p Point_2D
     */
    public void translate(Point_2D p) {
        centre.setX(saveCentre.getX() + p.getX());
        centre.setY(saveCentre.getY() + p.getY());
        for (int i = 0; i < nbPoints; i++) {
            points.get(i).deplace(centre.getX() - posPoints.get(i).getX(), centre.getY() - posPoints.get(i).getY());
            for (Triangle current : ssTriangles) {
                current.getCentre().setX((current.points[0].getX() + current.points[1].getX() + current.points[2].getX()) / 3);
                current.getCentre().setY((current.points[0].getY() + current.points[1].getY() + current.points[2].getY()) / 3);
            }
        }
    }

    /**
     * Fonction interne qui définit les sous-triangles utilisés pour les
     * fonctions de déplacement et de translations
     */
    private void setSsTriangles() {
        for (Point_2D current : points) {
            ArrayList<Point_2D> listPts = new ArrayList();
            listPts.add(current);
            listPts.add(points.get((points.indexOf(current) + 1) % nbPoints));
            listPts.add(centre);

            Triangle testTriangle = new Triangle(listPts);
            ssTriangles.add(testTriangle);
        }
    }

    /**
     * Fonction interne qui permet de vérifier qu'un triangle est concave, en
     * testant, pour chaque sommet, s'il n'est pas inclus dans le triangle
     * composé du sommet suivant, du sommet précédent, et du centre du polygone.
     * @return boolean
     */
    private boolean estConcave() {
        List<Triangle> trigConcave = new ArrayList();

        for (Point_2D current : points) {
            Point_2D prec = new Point_2D(points.get(((points.indexOf(current) - 1) % nbPoints) == -1 ? nbPoints - 1 : (points.indexOf(current) - 1) % nbPoints));
            Point_2D suiv = new Point_2D(points.get((points.indexOf(current) + 1) % nbPoints));
            ArrayList<Point_2D> listPts = new ArrayList();
            listPts.add(prec);
            listPts.add(suiv);
            listPts.add(centre);

            Triangle testTriangle = new Triangle(listPts);
            trigConcave.add(testTriangle);

        }
        for (Point_2D currentPt : points) {
            for (Triangle currentTr : trigConcave) {
                if (currentTr.contient(currentPt)) {
                    trigConcave.clear();
                    return true;
                }
            }
        }
        trigConcave.clear();
        return false;
    }
}
