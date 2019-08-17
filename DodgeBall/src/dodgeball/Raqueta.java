// Raqueta.java 
// Autor: José Alexander Brenes Brenes
// Define la clase raqueta para el objeto de colisión con la bola

package dodgeball;

public class Raqueta extends Actor {
    
    private int base;
    private int altura;
    
    public Raqueta(int coordenada_x, int coordenada_y, int direccion_x, int direccion_y, int base,int altura) {
        super(coordenada_x, coordenada_y, direccion_x, direccion_y);
        this.base = base;
        this.altura = altura;
    }
    @Override
    public void movimiento(Model model){
        
        
        
    }

    
    public int getBase(){
        return this.base;
    }
    public int getAltura(){
        return this.altura;
    }
    public void setAltura(int altura){
        this.altura = altura;
    }
    public void setBase(int base){
        this.base = base;
    }
    
}
