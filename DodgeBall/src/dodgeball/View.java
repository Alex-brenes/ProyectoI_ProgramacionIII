// View.java 
// Autor: José Alexander Brenes Brenes
// Métodos para la visualización del juego
package dodgeball;

public class View extends javax.swing.JFrame implements java.util.Observer {

    private Model model;
    private Controller controller;

    public View() {
        super("Dodge Ball");
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        dibujarBola(model.getBola(), graphics);
    }

    private void dibujarCircunferencia(Circunferencia circunferencia, java.awt.Graphics graphics) {
        graphics.setColor(java.awt.Color.BLACK);
        graphics.drawOval(circunferencia.getCoordenada_x()/*WIDTH/2 - circunferencia.getRadio()*/, circunferencia.getCoordenada_y()/*HEIGHT/2 - circunferencia.getRadio()*/, circunferencia.getRadio() * 2, circunferencia.getRadio() * 2);
    }

    private void dibujarBola(Bola bola, java.awt.Graphics graphics) {
        graphics.setColor(java.awt.Color.PINK);
        graphics.fillOval(bola.getCoordenada_x(), bola.getCoordenada_y(), bola.getRadio() * 2, bola.getRadio() * 2);
    }

    final int WIDTH= 590;
    final int HEIGHT = 590;
    
}
