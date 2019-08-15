// Circunferencia.java 
// Autor: José Alexander Brenes Brenes
// Funciona como delimitador para los modelos del juego (bola y raqueta).
// Además establece áreas en dónde obtener o perder puntos.

package dodgeball;

public class Circunferencia {
    
    private int coordenada_x;
    private int coordenada_y;
    private int radio;
    
    public Circunferencia(int coordenada_x, int coordenada_y, int radio) {
        this.coordenada_x = coordenada_x;
        this.coordenada_y = coordenada_y;
        this.radio = radio;
    }
    public Circunferencia(){
        this(0,0,100);
    }
    public Circunferencia(int coordenada_x) {
        //Mismo punto
        this(coordenada_x, coordenada_x, 100);
    }
    
    public int getCoordenada_x(){
        return this.coordenada_x;
    }
    public int getCoordenada_y(){
        return this.coordenada_y;
    }
    public int getRadio(){
        return this.radio;
    }
    
}
