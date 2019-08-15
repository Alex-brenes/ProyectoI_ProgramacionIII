// Actor.java 
// Autor: José Alexander Brenes Brenes
// Super-clase para los modelos del juego

package dodgeball;

public abstract class Actor {
    
    private int coordenada_x;
    private int coordenada_y;
    private int direccion_x;
    private int direccion_y;
    
    public Actor(int coordenada_x,int coordenada_y,int direccion_x, int direccion_y){
        this.coordenada_x = coordenada_x;
        this.coordenada_y = coordenada_y;
        this.direccion_x = direccion_x;
        this.direccion_y = direccion_y;
    }
    
    public abstract void movimiento(Model model);
    
    public int getCoordenada_x(){
        return this.coordenada_x;
    }
    
    public void setCoordenada_x(int coordenada_x){
        this.coordenada_x = coordenada_x;
    }

    public int getCoordenada_y() {
        return coordenada_y;
    }

    public void setCoordenada_y(int coordenada_y) {
        this.coordenada_y = coordenada_y;
    }

    public int getDireccion_x() {
        return direccion_x;
    }

    public void setDireccion_x(int direccion_x) {
        this.direccion_x = direccion_x;
    }

    public int getDireccion_y() {
        return direccion_y;
    }

    public void setDireccion_y(int direccion_y) {
        this.direccion_y = direccion_y;
    }
    
    
    
}
