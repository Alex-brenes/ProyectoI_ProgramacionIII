// View.java 
// Autor: José Alexander Brenes Brenes
// Métodos para la visualización del juego
package dodgeball;

import java.awt.event.KeyEvent;

public class View extends javax.swing.JFrame implements java.util.Observer {

    private Model model;
    private Controller controller;

    public View() {
        super("Dodge Ball");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP: {
                controller.mover(Model.ARR);
                break;
            }
            case KeyEvent.VK_DOWN: {
                controller.mover(Model.ABA);
                break;
            }
            case KeyEvent.VK_LEFT: {
                controller.mover(Model.IZQ);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                controller.mover(Model.DER);
                break;
            }
        }
    }

    private void formKeyReleased(java.awt.event.KeyEvent evt) {
        int key = evt.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT: {
                controller.detenerHor();
            }
            case KeyEvent.VK_RIGHT: {
                controller.detenerHor();
                break;
            }
            case KeyEvent.VK_UP: {
                controller.detenerHor();
            }
            case KeyEvent.VK_DOWN: {
                controller.detenerVer();
                break;
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void paint(java.awt.Graphics graphics) {
        super.paint(graphics);
        this.dibujarModelo(model, graphics);
    }

    @Override
    public void update(java.util.Observable sujeto, Object objeto) {

        this.repaint();

    }

    public void dibujarModelo(Model model, java.awt.Graphics graphics) {
        dibujarCircunferencia(model.getCircunferencia(), graphics);
        for (Bola b : model.getListaBolas()) {
            dibujarBola(b, graphics);
        }
        dibujarRaqueta(model.getRaqueta(), graphics);
        //dibujarBola(model.getBola(), graphics);
    }

    private void dibujarCircunferencia(Circunferencia circunferencia, java.awt.Graphics graphics) {
        int x = circunferencia.getCoordenada_x();
        int y = circunferencia.getCoordenada_y();
        int r = circunferencia.getRadio();
        graphics.setColor(java.awt.Color.BLACK);
        graphics.drawOval(circunferencia.getCoordenada_x()/*WIDTH/2 - circunferencia.getRadio()*/, circunferencia.getCoordenada_y()/*HEIGHT/2 - circunferencia.getRadio()*/, circunferencia.getRadio() * 2, circunferencia.getRadio() * 2);
        graphics.setColor(java.awt.Color.RED);
        graphics.drawLine(x + r, y, x + r, y + 2 * r);
        graphics.drawLine(x, y + r, x + 2 * r, y + r);
    }

    private void dibujarBola(Bola bola, java.awt.Graphics graphics) {
        graphics.setColor(java.awt.Color.PINK);
        graphics.fillOval(bola.getCoordenada_x(), bola.getCoordenada_y(), bola.getRadio() * 2, bola.getRadio() * 2);
        graphics.setColor(java.awt.Color.BLUE);
        graphics.fillOval(bola.getCoordenada_x(), bola.getCoordenada_y(), 10, 10);
        graphics.fillOval(bola.getCoordenada_x() + 10 * 2/*4*/, bola.getCoordenada_y(), 10, 10);
        graphics.fillOval(bola.getCoordenada_x(), bola.getCoordenada_y() + 10 * 2/*4*/, 10, 10);
        graphics.fillOval(bola.getCoordenada_x() + 10 * 2/*4*/, bola.getCoordenada_y() + 10 * 2/*4*/, 10, 10);
    }

    private void dibujarRaqueta(Raqueta raqueta, java.awt.Graphics graphics) {
        graphics.setColor(java.awt.Color.magenta);
        graphics.drawRect(raqueta.getCoordenada_x(), raqueta.getCoordenada_y(), raqueta.getBase(), raqueta.getAltura());
    }

    private final int WIDTH = 590;
    private final int HEIGHT = 590;

}
