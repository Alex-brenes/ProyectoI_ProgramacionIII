// Raqueta.java 
// Autor: José Alexander Brenes Brenes
//        Juan Daniel Quirós
// Define la clase raqueta para el objeto de colisión con la bola
package dodgeball.logic;

import dodgeball.presentacion.Model;

public class Raqueta extends Actor {

    private int base;
    private int altura;

    public Raqueta(int coordenada_x, int coordenada_y, int direccion_x, int direccion_y, int base, int altura) {
        super(coordenada_x, coordenada_y, direccion_x, direccion_y);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public void movimiento(Model model) {

        switch (segmento(model.getCircunferencia())) {
            case S_I: {
                if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + this.getBase(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                } else {
                    this.setDireccion_y(this.getDireccion_y() * (-1));
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                    this.setDireccion_x(this.getDireccion_x() * (-1));
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                }
                break;
            }
            case S_II: {
                if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                } else {
                    this.setDireccion_y(this.getDireccion_y() * (-1));
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                    this.setDireccion_x(this.getDireccion_x() * (-1));
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                }
                break;
            }
            case S_III: {
                if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y() + this.getAltura(), model)) { //Si el punto es interior a la circunferencia
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                } else {
                    this.setDireccion_y(this.getDireccion_y() * (-1));
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                    this.setDireccion_x(this.getDireccion_x() * (-1));
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                }
                break;
            }
            case S_IV: {
                if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + this.getBase(), this.getCoordenada_y() + this.getAltura(), model)) { //Si el punto es interior a la circunferencia
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                } else {
                    this.setDireccion_y(this.getDireccion_y() * (-1));
                    this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                    this.setDireccion_x(this.getDireccion_x() * (-1));
                    this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                }
                break;
            }
        }

    }

    private int segmento(Circunferencia c) {
        int xr = this.getCoordenada_x() + base / 2;
        int yr = this.getCoordenada_y() + altura / 2;
        int x = c.getCoordenada_x();
        int y = c.getCoordenada_y();
        int r = c.getRadio();
        //Se determina el segmento en el que se encuentra la bola;
        if ((xr > x + r && xr < x + 2 * r) && (yr > y && yr < y + r)) { //Segmento I
//            System.out.println("RAQUETA: SEGMENTO I");
            return S_I;
        } else if ((xr > x && xr < x + r) && (yr > y && yr < y + r)) { //Segemento II 
//            System.out.println("RAQUETA: SEGMENTO II");
            return S_II;
        } else if ((xr > x && xr < x + r) && (yr > y + r && yr < y + 2 * r)) { //Segmento III
//            System.out.println("RAQUETA: SEGMENTO III");
            return S_III;
        } else if ((xr > x + r && xr < x + 2 * r) && (yr > y + r && yr < y + 2 * r)) { //Segmento IV
//            System.out.println("RAQUETA: SEGMENTO IV");
            return S_IV;
        }

        return -1;

    }

    private boolean interior(int x1, int y1, int x2, int y2, Model model) {
        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) < model.getCircunferencia().getRadio());
    }

    public int getBase() {
        return this.base;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public boolean interiorRaqueta(int x, int y) {
        return (y >= this.getCoordenada_y()  && y <= this.getCoordenada_y() + this.altura) && (x >= this.getCoordenada_x() && x <= this.getCoordenada_x() + this.getBase());
    }

    private final int S_I = 0;
    private final int S_II = 1;
    private final int S_III = 2;
    private final int S_IV = 3;

}
