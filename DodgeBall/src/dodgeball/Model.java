// Raqueta.java 
// Autor: José Alexander Brenes Brenes
// Reúne los modelos del juego y administra sus operaciones

package dodgeball;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
    
    private final Raqueta raqueta;
    private final List<Bola> lista_bolas;
    private final Circunferencia circunferencia;
    
    public Model() {
        this.raqueta = new Raqueta(200, 300, 10, 10, 150, 50);
        this.lista_bolas = new ArrayList<Bola>();
        lista_bolas.add(new Bola( 150, 200, 10, -10, 15/*25*/));
        this.circunferencia = new Circunferencia(8, 31, 250);
    }
    
    @Override
    public void addObserver(Observer observer){
        super.addObserver(observer);
        this.setChanged();
        this.notifyObservers();
    }
    
    public void avanzar(){
        
        for(Bola b : this.lista_bolas){
            b.movimiento(this);
        }
        this.raqueta.movimiento(this);
        this.setChanged();
        this.notifyObservers();
    }
    
    public Raqueta getRaqueta(){
        return this.raqueta;
    }
    
    public List<Bola> getListaBolas(){
        return this.lista_bolas;
    }
    
    public Circunferencia getCircunferencia(){
        return this.circunferencia;
    }
    
}
