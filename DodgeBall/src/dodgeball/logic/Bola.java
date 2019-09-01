// Bola.java 
// Autor: José Alexander Brenes Brenes
//        Juan Daniel Quirós
// Define la clase bola, modelo que permite la obtención de puntos e interactúa con
// la raqueta y la circunferencia
package dodgeball.logic;

import dodgeball.presentacion.Model;
import java.util.Random;

public class Bola extends Actor {

    public static int radio;
    public static int speed;

    public Bola(int coordenada_x, int coordenada_y, int direccion_x, int direccion_y) {
        super(coordenada_x, coordenada_y, direccion_x, direccion_y);
        this.radio = 15;
        this.speed = 7;
    }

    public int getRadio() {
        return this.radio;
    }

    @Override
    public void movimiento(Model model) {
        int cbx = this.getCoordenada_x() + radio;
        int cby = this.getCoordenada_y() + radio;

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
                //Rebote en la circunferencia
                switch (segmento(model.getCircunferencia())) {
                    case S_I: { 
                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + 2 * this.getRadio(), this.getCoordenada_y(), model)) {
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            reboto = false;
                        } else {
                            if (!reboto) {
                                nuevaDireccion(model, S_I);
                            }
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                        }
                        break;
                    }
                    case S_II: {

                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y(), model)) {
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            reboto = false;
                        } else {
                            if (!reboto) {
                                nuevaDireccion(model, S_II);
                            }
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                        }
                        break;
                    }
                    case S_III: { 

                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y() + 2 * this.getRadio(), model)) {
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            reboto = false;

                        } else {
                            if (!reboto) {
                                nuevaDireccion(model, S_III);
                            }
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                        }
                        break;
                    }
                    case S_IV: {

                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + 2 * this.getRadio(), this.getCoordenada_y() + 2 * this.getRadio(), model)) {
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            reboto = false;

                        } else {
                            if (!reboto) {
                                nuevaDireccion(model, S_IV);
                            }
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                        }
                        break;
                    }
                }
            }
        }

    }

    private void nuevaDireccion(Model model, int segmento) {
        reboto = true;
        int circ_x = model.getCircunferencia().centro_x();
        int circ_y = model.getCircunferencia().centro_y();
        double theta = Math.toDegrees(Math.atan2(this.getCoordenada_y() + radio - circ_y, this.getCoordenada_x() + radio - circ_x)) * (-1);
        int angulo_sup = 0;
        int angulo_inf = 0;
        double angle = 0.0;
        Random randNum = new Random(System.currentTimeMillis());

        switch (segmento) {

            case S_I: {
                if (this.getDireccion_y() < 0) {
                    if (0 <= theta && theta < 30) {
                        angulo_inf = 180;
                        angulo_sup = 210;
                        if (theta <= 15) {
                            punto = 1;
                        }

                    } else if (30 <= theta && theta < 45) {
                        angulo_inf = 200;
                        angulo_sup = 210;
                        punto = 2;

                    } else if (45 <= theta && theta < 60) {
                        angulo_inf = 170;
                        angulo_sup = 200;
                        punto = 2;

                    } else if (60 <= theta && theta < 91) {
                        angulo_inf = 135;
                        angulo_sup = 150;
                        if (theta >= 75) {
                            punto = 1;
                        }
                    }
                } else {
                    if (0 <= theta && theta < 30) {
                        angulo_inf = 150;
                        angulo_sup = 180;
                        if (theta <= 15) {
                            punto = 1;
                        }
                    } else if (30 <= theta && theta < 45) {
                        angulo_inf = 135;
                        angulo_sup = 150;
                        punto = 2;
                    } else if (45 <= theta && theta < 60) {
                        angulo_inf = 120;
                        angulo_sup = 135;
                        punto = 2;
                    } else if (60 <= theta && theta < 91) {
                        angulo_inf = 90;
                        angulo_sup = 120;
                        if (theta >= 75) {
                            punto = 1;
                        }
                    }

                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                break;
            }

            case S_II: {
                if (this.getDireccion_y() < 0) { 
                    if (89 <= theta && theta < 120) { 
                        angulo_inf = 60;
                        angulo_sup = 90;
                        if (theta <= 105) {
                            punto = 1;
                        }

                    } else if (120 <= theta && theta < 135) { 
                        angulo_inf = 345;
                        angulo_sup = 355;
                        punto = 2;
                    } else if (135 <= theta && theta < 150) { 
                        angulo_inf = 340;
                        angulo_sup = 350;
                        punto = 2;
                    } else if (150 <= theta && theta < 180) { 
                        angulo_inf = 330;
                        angulo_sup = 360;
                        if (theta >= 165) {
                            punto = 1;
                        }
                    }
                } else {
                    if (89 <= theta && theta < 120) { 
                        angulo_inf = 60;
                        angulo_sup = 90;
                        if (theta <= 105) {
                            punto = 1;
                        }
                    } else if (120 <= theta && theta < 135) {
                        angulo_inf = 45;
                        angulo_sup = 60;
                        punto = 2;
                    } else if (135 <= theta && theta < 150) { 
                        angulo_inf = 30;
                        angulo_sup = 45;
                        punto = 2;
                    } else if (150 <= theta && theta < 180) { 
                        angulo_inf = 0;
                        angulo_sup = 30;
                        if (theta >= 165) {
                            punto = 1;
                        }
                    }
                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                break;
            }
            case S_III: {
                theta *= (-1);
                theta = 360 - theta;
                if (this.getDireccion_y() < 0) { 
                    if (180 <= theta && theta < 210) { 
                        angulo_inf = 330;
                        angulo_sup = 350;
                        if (theta <= 195) {
                            punto = 1;
                        }

                    } else if (210 <= theta && theta < 225) { 
                        angulo_inf = 315;
                        angulo_sup = 330;
                        punto = 2;
                    } else if (225 <= theta && theta < 240) { 
                        angulo_inf = 300;
                        angulo_sup = 315;
                        punto = 2;
                    } else if (240 <= theta && theta < 270) { 
                        angulo_inf = 270;
                        angulo_sup = 300;
                        if (theta >= 255) {
                            punto = 1;
                        }
                    }
                } else {

                    if (180 <= theta && theta < 210) { 
                        angulo_inf = 0;
                        angulo_sup = 30;
                        if (theta <= 195) {
                            punto = 1;
                        }

                    } else if (210 <= theta && theta < 225) { 
                        angulo_inf = 10;
                        angulo_sup = 30;
                        punto = 2;
                    } else if (225 <= theta && theta < 240) { 
                        angulo_inf = 15;
                        angulo_sup = 20;
                        punto = 2;
                    } else if (240 <= theta && theta <= 270) {
                        angulo_inf = 270;
                        angulo_sup = 300;
                        if (theta >= 255) {
                            punto = 1;
                        }
                    }
                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                break;
            }
            case S_IV: {
                
                theta *= (-1);
                theta = 360 - theta;
                if (this.getDireccion_y() < 0) {
                    if (269 <= theta && theta < 300) {
                        angulo_inf = 315;
                        angulo_sup = 330;
                        if (theta <= 285) {
                            punto = 1;
                        }

                    } else if (300 <= theta && theta < 315) { 
                        angulo_inf = 225;
                        angulo_sup = 240;
                        punto = 2;

                    } else if (315 <= theta && theta < 330) { 
                        angulo_inf = 135;
                        angulo_sup = 150;
                        punto = 2;
                    } else if (330 <= theta && theta < 360) {
                        angulo_inf = 180;
                        angulo_sup = 210;
                        punto = 2;
                        if (theta >= 345) {
                            punto = 1;
                        }
                    }

                } else {
                    if (269 <= theta && theta < 300) { 
                        angulo_inf = 240;
                        angulo_sup = 265;
                        if (theta <= 285) {
                            punto = 1;
                        }

                    } else if (300 <= theta && theta < 315) { 
                        angulo_inf = 165;
                        angulo_sup = 170;
                        punto = 2;
                    } else if (315 <= theta && theta < 330) {
                        angulo_inf = 150;
                        angulo_sup = 180;
                        punto = 2;
                    } else if (330 <= theta && theta < 360) { 
                        angulo_inf = 140;
                        angulo_sup = 150;
                        if (theta >= 345) {
                            punto = 1;
                        }

                    }
                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                break;
            }
        }
        this.setDireccion_x((int) (speed * Math.cos(Math.toRadians(angle))));
        this.setDireccion_y((int) (speed * Math.sin(Math.toRadians(angle))));

    }

    private boolean chocaRaquetaVertical(Model model) {
        int cenx = this.getCoordenada_x() + radio;
        int ceny = this.getCoordenada_y() + radio;
        int esquina1_x = this.getCoordenada_x();
        int esquina1_y = this.getCoordenada_y();
        int esquina2_x = this.getCoordenada_x() + radio * 2;
        int esquina2_y = this.getCoordenada_y();
        int esquina3_x = this.getCoordenada_x();
        int esquina3_y = this.getCoordenada_y() + radio * 2;
        int esquina4_x = this.getCoordenada_x() + radio * 2;
        int esquina4_y = this.getCoordenada_y() + radio * 2;
        if ((this.getCoordenada_y() + radio * 2 >= model.getRaqueta().getCoordenada_y() && this.getCoordenada_y() <= model.getRaqueta().getCoordenada_y() + model.getRaqueta().getAltura()) && (this.getCoordenada_x() >= model.getRaqueta().getCoordenada_x() && this.getCoordenada_x() + radio < model.getRaqueta().getCoordenada_x() + model.getRaqueta().getBase())) {

            return true;
        } else if ((model.getRaqueta().interiorRaqueta(esquina1_x, esquina1_y)
                || model.getRaqueta().interiorRaqueta(esquina2_x, esquina2_y)
                || model.getRaqueta().interiorRaqueta(esquina3_x, esquina3_y)
                || model.getRaqueta().interiorRaqueta(esquina4_x, esquina4_y)) 
                && (
                ceny < model.getRaqueta().getCoordenada_y()
                || 
                ceny > model.getRaqueta().getCoordenada_y() + model.getRaqueta().getAltura())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean chocaRaquetaHorizontal(Model model) {
        int cenx = this.getCoordenada_x() + radio;
        int esquina1_x = this.getCoordenada_x();
        int esquina1_y = this.getCoordenada_y();
        int esquina2_x = this.getCoordenada_x() + radio * 2;
        int esquina2_y = this.getCoordenada_y();
        int esquina3_x = this.getCoordenada_x();
        int esquina3_y = this.getCoordenada_y() + radio * 2;
        int esquina4_x = this.getCoordenada_x() + radio * 2;
        int esquina4_y = this.getCoordenada_y() + radio * 2;
        if ((this.getCoordenada_x() + this.radio * 2 >= model.getRaqueta().getCoordenada_x() && this.getCoordenada_x() <= model.getRaqueta().getCoordenada_x() + model.getRaqueta().getBase()) && (this.getCoordenada_y() >= model.getRaqueta().getCoordenada_y() && this.getCoordenada_y() + radio < model.getRaqueta().getCoordenada_y() + model.getRaqueta().getAltura())) {

            return true;
        } else if ((model.getRaqueta().interiorRaqueta(esquina1_x, esquina1_y)
                || model.getRaqueta().interiorRaqueta(esquina2_x, esquina2_y)
                || model.getRaqueta().interiorRaqueta(esquina3_x, esquina3_y)
                || model.getRaqueta().interiorRaqueta(esquina4_x, esquina4_y))
                && (
                cenx < model.getRaqueta().getCoordenada_x()
                || 
                cenx > model.getRaqueta().getCoordenada_x() + model.getRaqueta().getBase())) {
            return true;
        } else {
            return false;
        }

    }

    private boolean interior(int x1, int y1, int x2, int y2, Model model) {
        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) < model.getCircunferencia().getRadio());
    }

    public boolean interiorAbajo(Model model) {
        int x1 = this.getCoordenada_x() + radio * 2;
        int y1 = this.getCoordenada_y() + radio * 2;
        int x2 = model.getCircunferencia().centro_x();
        int y2 = model.getCircunferencia().centro_y();
        return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) < model.getCircunferencia().getRadio());
    }

    public int segmento(Circunferencia c) {
        int xb = this.getCoordenada_x();
        int yb = this.getCoordenada_y();
        int x = c.getCoordenada_x();
        int y = c.getCoordenada_y();
        int r = c.getRadio();

        int cen_bola_x = this.getCoordenada_x() + radio;
        int cen_bola_y = this.getCoordenada_y() + radio;

        //Se determina el segmento en el que se encuentra la bola;
        if ((cen_bola_x >= x + r && cen_bola_x < x + 2 * r) && (cen_bola_y > y && cen_bola_y <= y + r)) {
            return S_I;
        } else if ((cen_bola_x <= x + r && cen_bola_x > x) && (cen_bola_y <= y + r && cen_bola_y > y)) {
            return S_II;
        } else if ((cen_bola_x <= x + r && cen_bola_x > x) && (cen_bola_y >= y + r && cen_bola_y < y + 2 * r)) {
            return S_III;
        } else if ((cen_bola_x >= x + r && cen_bola_x < x + 2 * r) && (cen_bola_y >= y + r && cen_bola_y < y + 2 * r)) {
            return S_IV;
        }

        return -1;

    }

    public static final int S_I = 0;
    public static final int S_II = 1;
    public static final int S_III = 2;
    public static final int S_IV = 3;
    public int punto = 0;
    /*
     0 : No hay cambio
     1 : Gana punto (Poner en 0 después)
     2 : Pierde punto (Poner en 0 después)
     */

    private boolean reboto = false;

}
