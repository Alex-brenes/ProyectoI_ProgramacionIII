// Raqueta.java 
// Autor: José Alexander Brenes Brenes
//        Juan Daniel Quirós
// Reúne los modelos del juego y administra sus operaciones
package dodgeball.presentacion;

import dodgeball.logic.Raqueta;
import dodgeball.logic.Circunferencia;
import dodgeball.logic.Bola;
import dodgeball.logic.HUD;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    private final Raqueta raqueta;
    private final List<Bola> lista_bolas;
    private final Circunferencia circunferencia;
    private final HUD hud;

    public Model() {
        this.raqueta = new Raqueta(246/*246*/, 451, 0, 0, 100, 40);
        this.lista_bolas = new ArrayList<Bola>();
        lista_bolas.add(new Bola(120, 290, 7, 7));
        this.circunferencia = new Circunferencia(45, 80, 250);
        hud = new HUD(Bola.speed);
    }

    public HUD getHud() {
        return hud;
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        this.setChanged();
        this.notifyObservers();
    }

    public void avanzar() {
        if (!isPaused) {
            for (Bola b : this.lista_bolas) {
                b.movimiento(this);
            }
            this.raqueta.movimiento(this);
            this.setChanged();
            this.notifyObservers();
        }

    }

    public void cambiaVelocidad(int nueva_vel) {
        Bola.speed = nueva_vel;
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

    public void eliminarBolas(int c) {
        while (c < lista_bolas.size()) {
            lista_bolas.remove(lista_bolas.size() - 1);
        }
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

    public void agregarBola(int x, int y) {
        try {
            Bola nueva = new Bola(x, y, 0, 0);
            int xc = this.circunferencia.centro_x();
            int yc = this.circunferencia.centro_y();
            if (Math.sqrt(((x - xc) * (x - xc)) + ((y - yc) * (y - yc))) < this.circunferencia.getRadio() - Bola.radio * 3) {
                //Se toma la velocidad de la bola anterior
                int bolAnt_x = lista_bolas.get(lista_bolas.size() - 1).getDireccion_x();
                int bolAnt_y = lista_bolas.get(lista_bolas.size() - 1).getDireccion_y();
                if (bolAnt_y < 0) {
                    bolAnt_y *= (-1);
                }
                if (bolAnt_x < 0) {
                    bolAnt_x *= (-1);
                }
                switch (nueva.segmento(circunferencia)) {
                    case Bola.S_I: nueva.setDireccion_x((-1) * bolAnt_x); nueva.setDireccion_y(bolAnt_y); break;
                    case Bola.S_II: nueva.setDireccion_x(bolAnt_x); nueva.setDireccion_y(bolAnt_y); break;
                    case Bola.S_III: nueva.setDireccion_x(bolAnt_x); nueva.setDireccion_y((-1) * bolAnt_y); break;
                    case Bola.S_IV: nueva.setDireccion_x((-1) * bolAnt_x); nueva.setDireccion_y((-1) * bolAnt_y); break;
                }
                this.lista_bolas.add(nueva);
                
            } else {
                throw new java.io.IOException();
            }

        } catch (java.io.IOException ex) {

        }
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

    public void cambiaEstado() {
        isPaused = !isPaused;
    }
    static final int ARR = 0;
    static final int ABA = 1;
    static final int IZQ = 2;
    static final int DER = 3;

    private static boolean isPaused = false;
}
