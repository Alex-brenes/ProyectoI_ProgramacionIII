// View.java 
// Autor: José Alexander Brenes Brenes
// Métodos para la visualización del juego

package dodgeball;

public class View extends javax.swing.JFrame implements java.util.Observer {
 
    private Model model;
    private Controller controller;
    
    public View(){
        super("Dodge Ball");
        this.setSize(590,590);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    public void setController(Controller controller){
        this.controller = controller;
    }
    public void setModel(Model model){
        this.model = model;
        model.addObserver(this);
    }
    
    public void dibujarModelo(Model model,java.awt.Graphics graphics){
        dibujarCircunferencia(model.getCircunferencia(),graphics);
    }
    @Override
    public void paint(java.awt.Graphics graphics){
        super.paint(graphics);
        this.dibujarModelo(model, graphics);
    }
    @Override
    public void update(java.util.Observable sujeto,Object objeto){
        
        this.repaint();
        
    }
    
    private void dibujarCircunferencia(Circunferencia circunferencia, java.awt.Graphics graphics){
        graphics.setColor(java.awt.Color.BLACK);
        graphics.drawOval(circunferencia.getCoordenada_x(), circunferencia.getCoordenada_y(), circunferencia.getRadio() * 2, circunferencia.getRadio() * 2);
    }
    
}
