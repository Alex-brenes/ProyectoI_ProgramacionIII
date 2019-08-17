// Raqueta.java 
// Autor: José Alexander Brenes Brenes
// Reúne los modelos del juego y administra sus operaciones

package dodgeball;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
    
    private final Raqueta raqueta;
    private final Bola bola;
    private final Circunferencia circunferencia;
    
    public Model() {
        this.raqueta = new Raqueta(300, 300, 10, 10, 150, 100);
        this.bola = new Bola(150, 200, 10, 10, 25);
        this.circunferencia = new Circunferencia(8, 31, 250);
    }
    
    @Override
    public void addObserver(Observer observer){
        super.addObserver(observer);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void avanzar(){
        bola.movimiento(this);
        this.setChanged();
        this.notifyObservers();
    }
    
    public Raqueta getRaqueta(){
        return this.raqueta;
    }
    
    public Bola getBola(){
        return this.bola;
    }
    
    public Circunferencia getCircunferencia(){
        return this.circunferencia;
    }
    
}
