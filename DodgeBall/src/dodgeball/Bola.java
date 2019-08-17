// Bola.java 
// Autor: José Alexander Brenes Brenes
// Define la clase bola, modelo que permite la obtención de puntos e interactúa con
// la raqueta y la circunferencia
package dodgeball;

/**
 *
 * @author pc
 */
public class Bola extends Actor {

    private int radio;

    public Bola(int coordenada_x, int coordenada_y, int direccion_x, int direccion_y, int radio) {
        super(coordenada_x, coordenada_y, direccion_x, direccion_y);
        this.radio = radio;
    }

    public int getRadio() {
        return this.radio;
    }

    @Override
    public void movimiento(Model model) {
        if (this.getDireccion_x() > 0 && this.getDireccion_y() > 0) {
            if (interiorAbajo(model)) {
                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
            } else {
                this.setDireccion_x(this.getDireccion_x() * (-1));
                this.setDireccion_y(this.getDireccion_y() * (-1));
                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
            }
        }else{
            if(interiorArriba(model)){
                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
            }else{
                this.setDireccion_x(this.getDireccion_x() * (-1));
                this.setDireccion_y(this.getDireccion_y() * (-1));
                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
            }
                            
            
        }

    }

    public boolean interiorArriba(Model model) {
        int x1 = this.getCoordenada_x();
        int y1 = this.getCoordenada_y();
        int x2 = model.getCircunferencia().centro_x();
        int y2 = model.getCircunferencia().centro_y();
        System.out.println(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) < model.getCircunferencia().getRadio());
    }

    public boolean interiorAbajo(Model model) {
        int x1 = this.getCoordenada_x() + radio * 2;
        int y1 = this.getCoordenada_y() + radio * 2;
        int x2 = model.getCircunferencia().centro_x();
        int y2 = model.getCircunferencia().centro_y();
        System.out.println(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) < model.getCircunferencia().getRadio());
    }

    private void nuevaDireccion(Model model){

        int radio_circunferencia = model.getCircunferencia().getRadio();
        if (this.getCoordenada_x() > radio_circunferencia / 2 && this.getCoordenada_y() < radio_circunferencia / 2) { //Segemento I

        } else if (this.getCoordenada_x() < radio_circunferencia / 2 && this.getCoordenada_y() < radio_circunferencia / 2) { //Segmento II

        } else if (this.getCoordenada_x() < radio_circunferencia / 2 && this.getCoordenada_y() > radio_circunferencia / 2) { //Segmento III

        } else { //Segemento IV

        }
        
    }
    
}
