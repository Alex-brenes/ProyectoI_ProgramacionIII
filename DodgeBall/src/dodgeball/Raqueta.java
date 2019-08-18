// Raqueta.java 
// Autor: José Alexander Brenes Brenes
// Define la clase raqueta para el objeto de colisión con la bola
package dodgeball;

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

        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + this.getBase(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
            //this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
        }

            //this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());

    }

    private int segmento(Circunferencia c) {
        int xb = this.getCoordenada_x();
        int yb = this.getCoordenada_y();
        int x = c.getCoordenada_x();
        int y = c.getCoordenada_y();
        int r = c.getRadio();
        //Se determina el segmento en el que se encuentra la bola;
        if ((xb > x + r && xb < x + 2 * r) && (yb > y && yb < y + r)) { //Segmento I
            System.out.println("SEGMENTO I");
            return S_I;
        } else if ((xb > x && xb < x + r) && (yb > y && yb < y + r)) { //Segemento II 
            System.out.println("SEGMENTO II");
            return S_II;
        } else if ((xb > x && xb < x + r) && (yb > y + r && yb < y + 2 * r)) { //Segmento III
            System.out.println("SEGMENTO III");
            return S_III;
        } else if ((xb > x + r && xb < x + 2 * r) && (yb > y + r && yb < y + 2 * r)) { //Segmento IV
            System.out.println("SEGMENTO IV");
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

    private final int S_I = 0;
    private final int S_II = 1;
    private final int S_III = 2;
    private final int S_IV = 3;

}
