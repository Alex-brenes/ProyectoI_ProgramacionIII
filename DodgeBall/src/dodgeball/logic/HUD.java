// HUD.java 
// Autor: José Alexander Brenes Brenes
// Información sobre los datos relevantes para el jugador de la partida
package dodgeball.logic;

public class HUD {
    
    private int puntaje;
    private int velocidad;
    private int bolasRestantes;
    private final int topeBolas;

    public HUD(int velocidad) {
        this.puntaje = 0;
        this.velocidad = velocidad;
        this.bolasRestantes = 0;
        this.topeBolas = 15;
    }

    public int getTopeBolas() {
        return topeBolas;
    }
    
    public void reducirBolas(){
        this.bolasRestantes--;
    }
    public void aumentarPuntaje(){
        this.puntaje++;
    }
    public void reducirPuntaje(){
        this.puntaje--;
    }
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getBolasRestantes() {
        return bolasRestantes;
    }

    public void setBolasRestantes(int bolasRestantes) {
        this.bolasRestantes = bolasRestantes;
    }
}
