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
        //Colisión con la raqueta
        if (chocaRaquetaHorizontal(model)) {
            this.setDireccion_x(this.getDireccion_x() * (-1));
            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
        } else {
            if (chocaRaquetaVertical(model)) {
                this.setDireccion_y(this.getDireccion_y() * (-1));
                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
            } else {
                //Rebote en el ring
                //if (this.getDireccion_x() > 0 && this.getDireccion_y() > 0) {
                    switch(segmento(model.getCircunferencia())){
                        case S_I: { //Si está en el segmento I
                            if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + 2 * this.getRadio(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            } else { //Si no se cambia la dirección
                                nuevaDireccion(model,S_I);
                                this.setDireccion_x(this.getDireccion_x() * (-1));
                                this.setDireccion_y(this.getDireccion_y() * (-1));
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            }
                            break;
                        }
                        case S_II: { //Si está en el segmento II
                            if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            } else { //Si no se cambia la dirección
                                nuevaDireccion(model, S_II);
                                this.setDireccion_x(this.getDireccion_x() * (-1));
                                this.setDireccion_y(this.getDireccion_y() * (-1));
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            }
                            break;
                        }
                        case S_III: { //Si está en el segmento III
                            if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y()  + 2 * this.getRadio(), model)) { //Si el punto es interior a la circunferencia
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            } else { //Si no se cambia la dirección
                                nuevaDireccion(model, S_III);
                                this.setDireccion_x(this.getDireccion_x() * (-1));
                                this.setDireccion_y(this.getDireccion_y() * (-1));
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            }
                            break;
                        }
                        case S_IV: { //Si está en el segmento IV
                            if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + 2 * this.getRadio(), this.getCoordenada_y() + 2 * this.getRadio(), model)) { //Si el punto es interior a la circunferencia
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            } else { //Si no se cambia la dirección
                                nuevaDireccion(model, S_IV);
                                this.setDireccion_x(this.getDireccion_x() * (-1));
                                this.setDireccion_y(this.getDireccion_y() * (-1));
                                this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                                this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            }
                            break;
                        }
                    }
////                    if (interiorAbajo(model)) {
////                        this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
////                        this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
////                    } else {
////                        this.setDireccion_x(this.getDireccion_x() * (-1));
////                        this.setDireccion_y(this.getDireccion_y() * (-1));
////                        this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
////                        this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
////                    }
//                } else {
//                    if (interiorArriba(model)) {
//                        this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                        this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                    } else {
//                        this.setDireccion_x(this.getDireccion_x() * (-1));
//                        this.setDireccion_y(this.getDireccion_y() * (-1));
//                        this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                        this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                    }
//
//                }

            }
        }


  

    }
    private void nuevaDireccion(Model model,int segmento){
        
        switch(segmento){
            case S_I:{
                if(this.getDireccion_x() > 0){
                    
                }
                break;
            }
            case S_II:{
                break;
            }
            case S_III:{
                break;
            }
            case S_IV:{
                break;
            }
        }
    }
    private boolean chocaRaquetaVertical(Model model){
        if ((this.getCoordenada_y() + radio * 2 >= model.getRaqueta().getCoordenada_y() && this.getCoordenada_y() <= model.getRaqueta().getCoordenada_y() + model.getRaqueta().getAltura()) && (this.getCoordenada_x() >= model.getRaqueta().getCoordenada_x() && this.getCoordenada_x() + radio < model.getRaqueta().getCoordenada_x() + model.getRaqueta().getBase())) {
           System.out.println("Vertical");
            return true;
        }
        return false;
    }
    private boolean chocaRaquetaHorizontal(Model model){
        if((this.getCoordenada_x() + this.radio * 2 >= model.getRaqueta().getCoordenada_x() && this.getCoordenada_x() <= model.getRaqueta().getCoordenada_x() + model.getRaqueta().getBase()) && (this.getCoordenada_y() >= model.getRaqueta().getCoordenada_y() && this.getCoordenada_y() + radio < model.getRaqueta().getCoordenada_y() + model.getRaqueta().getAltura())){
          System.out.println("Horizontal");
            return true;
        }
        return false;
    }

    private boolean interior(int x1,int y1, int x2, int y2, Model model){
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

    private int segmento(Circunferencia c){
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
        }else if((xb > x + r && xb < x + 2 * r) && (yb > y + r && yb < y + 2 * r)){ //Segmento IV
            System.out.println("SEGMENTO IV");
            return S_IV;
        }
        
        return -1;
        
    }
    
    private final int S_I = 0;
    private final int S_II = 1;
    private final int S_III = 2;
    private final int S_IV = 3;
    
    
}
