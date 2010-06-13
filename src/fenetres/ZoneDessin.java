package fenetres;

import controls.ControlClavier;
import gestions.GestionBoutonsSouris;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ZoneDessin extends Canvas {

    private int xinit, yinit;
    private int x = 20, y = 20, width = 20, height = 20;
    private Color color = Color.black;
    private FenetAffiche frame;
    private BufferStrategy strategy;

    public ZoneDessin(FenetAffiche frame) {
        this.frame = frame;
        setBackground(Color.white);
        //$$3
        addMouseListener(new GestionBoutonsSouris(frame));
        addKeyListener(new ControlClavier(frame));
        setFocusable(true);
        requestFocus();
    }

    public void paint(Graphics g) {
        //$$2
        /*strategy = getBufferStrategy();
        Graphics gr = strategy.getDrawGraphics();*/
        frame.dessineFigs(g);
        //Sstrategy.show();
    }
}
