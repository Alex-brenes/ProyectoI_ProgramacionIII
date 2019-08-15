// Bola.java 
// Autor: José Alexander Brenes Brenes
// Define la clase bola, modelo que permite la obtención de puntos e interactúa con
// la raqueta y la circunferencia

package dodgeball;

/**
 *
 * @author pc
 */
public class Bola extends Actor {
    
    private int radio;
    
    public Bola(int coordenada_x, int coordenada_y, int direccion_x, int direccion_y, int radio) {
        super(coordenada_x, coordenada_y, direccion_x, direccion_y);
        this.radio = radio;
    }
    
    public void movimiento(Model model){
        
    }
    
}
