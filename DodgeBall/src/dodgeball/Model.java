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
        this.raqueta = new Raqueta(246, 291, 10, 10, 100, 40);
        this.lista_bolas = new ArrayList<Bola>();
        lista_bolas.add(new Bola(251, 201, 10, 10, 15/*25*/));
        this.circunferencia = new Circunferencia(45, 80, 250);
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        this.setChanged();
        this.notifyObservers();
    }

    public void avanzar() {

        for (Bola b : this.lista_bolas) {
            b.movimiento(this);
        }
        this.raqueta.movimiento(this);
        this.setChanged();
        this.notifyObservers();
    }

    public Raqueta getRaqueta() {
        return this.raqueta;
    }

    public List<Bola> getListaBolas() {
        return this.lista_bolas;
    }

    public Circunferencia getCircunferencia() {
        return this.circunferencia;
    }

    public void iniciar() {
        final int mili_segundos = 42;
        Runnable code = new Runnable() {
            public void run() {
                while (true) {
                    avanzar();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(mili_segundos);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        Thread thread = new Thread(code);
        thread.start();
    }

    public void detenerVer() {
        raqueta.setDireccion_y(0);
    }

    public void detenerHor() {
        raqueta.setDireccion_x(0);
    }

    public void mover(int flecha) {
        switch (flecha) {
            case ARR: {
                raqueta.setDireccion_y(-10);
                break;
            }
            case ABA: {
                raqueta.setDireccion_y(10);
                break;
            }
            case IZQ: {
                raqueta.setDireccion_x(-10);
                break;
            }
            case DER: {
                raqueta.setDireccion_x(10);
                break;
            }
        }
    }

    static final int ARR = 0;
    static final int ABA = 1;
    static final int IZQ = 2;
    static final int DER = 3;

}
