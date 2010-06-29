package fenetres;

import controles.ControlClavier;
import gestions.GestionBoutonsSouris;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Class gérant la zone de dessin de l'application
 */
public class ZoneDessin extends Canvas {

    //Ensemble des variables pour les paramètres de l'application
    private int xinit, yinit;
    private int x = 20, y = 20, width = 20, height = 20;
    private Color color = Color.black;
    private FenetAffiche frame;
    private BufferStrategy strategy;

    /**
     * Constructeur de l'application, prend en paramètre la FenetAffiche associée
     * @param frame FenetAffiche
     */
    public ZoneDessin(FenetAffiche frame) {
        this.frame = frame;
        setBackground(Color.white);
        addMouseListener(new GestionBoutonsSouris(frame));
        addKeyListener(new ControlClavier(frame));
        setFocusable(true);
        requestFocus();
    }

    /**
     * Méthode appelée lorsque la zone de dessin a besoin d'être déssinée
     * @param g Graphic
     */
    public void paint(Graphics g) {
        frame.dessineFigs(g);
    }
}
