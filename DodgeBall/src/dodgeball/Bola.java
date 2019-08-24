// Bola.java 
// Autor: José Alexander Brenes Brenes
//        Juan Daniel Quirós
// Define la clase bola, modelo que permite la obtención de puntos e interactúa con
// la raqueta y la circunferencia
package dodgeball;

import java.util.Random;

/**
 *
 * @author pc
 */
public class Bola extends Actor {

    private int radio;
    private int speed;

    public Bola(int coordenada_x, int coordenada_y, int direccion_x, int direccion_y, int radio) {
        super(coordenada_x, coordenada_y, direccion_x, direccion_y);
        this.radio = radio;
        this.speed = 10;
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
                switch (segmento(model.getCircunferencia())) {
                    case S_I: { //Si está en el segmento I
//                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + 2 * this.getRadio(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        } else { //Si no, se cambia la dirección
//                            if (this.getDireccion_y() > 0) { //Si va bajando
//                                System.out.println("Va bajando");
//                                nuevaDireccion(model, S_I);
//                            } else {
//                                nuevaDireccion(model, S_I);
//                                this.setDireccion_y(this.getDireccion_y() * (-1));
//
//                            }
//                            this.setDireccion_x(this.getDireccion_x() * (-1));
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        }
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
                    case S_II: { //Si está en el segmento II
//                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        } else { //Si no, se cambia la dirección
////                            this.setDireccion_x(this.getDireccion_x() * (-1));
////                            this.setDireccion_y(this.getDireccion_y() * (-1));
//                            if (this.getDireccion_y() < 0) { //Si va subiendo
//                                nuevaDireccion(model, S_II);
//                                this.setDireccion_y(this.getDireccion_y() * (-1));
//                            } else {
//                                nuevaDireccion(model, S_II);
//                            }
//
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        }
                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y(), model)) { //Si el punto es interior a la circunferencia
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            reboto = false;
                        } else { //Si no, se cambia la dirección
                            if (!reboto) {
                                nuevaDireccion(model, S_II);
                            }
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                        }
                        break;
                    }
                    case S_III: { //Si está en el segmento III
//                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y() + 2 * this.getRadio(), model)) { //Si el punto es interior a la circunferencia
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        } else { //Si no, se cambia la dirección
//                            nuevaDireccion(model, S_III);
//                            this.setDireccion_x(this.getDireccion_x() * (-1));
//                            this.setDireccion_y(this.getDireccion_y() * (-1));
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        }
                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x(), this.getCoordenada_y() + 2 * this.getRadio(), model)) { //Si el punto es interior a la circunferencia
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            reboto = false;
                            System.out.println("Fuera");
                        } else { //Si no, se cambia la dirección
                            if (!reboto) {
                                nuevaDireccion(model, S_III);
                            }
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                        }
                        break;
                    }
                    case S_IV: { //Si está en el segmento IV

//                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + 2 * this.getRadio(), this.getCoordenada_y() + 2 * this.getRadio(), model)) { //Si el punto es interior a la circunferencia
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        } else { //Si no, se cambia la dirección
//                            System.out.println("IV\n\ty: " + this.getDireccion_y());
//
//                            if (this.getDireccion_y() < 0) { //Si va subiendo
//                                System.out.println("\tViene subiendo");
//                                nuevaDireccion(model, S_IV);
//                                this.setDireccion_y(this.getDireccion_y() * (-1));
//                            } else {
//                                System.out.println("\tViene Bajando");
//                                nuevaDireccion(model, S_IV);
//                            }
//
//                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
//                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
//                        }
                        if (interior(model.getCircunferencia().getCoordenada_x() + model.getCircunferencia().getRadio(), model.getCircunferencia().getCoordenada_y() + model.getCircunferencia().getRadio(), this.getCoordenada_x() + 2 * this.getRadio(), this.getCoordenada_y() + 2 * this.getRadio(), model)) { //Si el punto es interior a la circunferencia
                            this.setCoordenada_x(this.getCoordenada_x() + this.getDireccion_x());
                            this.setCoordenada_y(this.getCoordenada_y() + this.getDireccion_y());
                            reboto = false;
                            System.out.println("Fuera");
                        } else { //Si no, se cambia la dirección
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

        //	Right side of boundary
//        if (this.getCoordenada_x() > circ_x) {
//
//            //	Quadrant 4
//            if (this.getCoordenada_y() > circ_y) {
//
//                //	Sub Q 13
//                if (this.getCoordenada_y() - circ_y > Math.sin(3 * Math.PI / 8)) {
//                    System.out.println("q13");
//                    angle = (double) (randNum.nextInt(90) + 90);
//
//                } //	Sub Q 14
//                else if (this.getCoordenada_y() - circ_y > Math.sin(Math.PI / 4)) {
//                    System.out.println("q14");
//                    angle = (double) (randNum.nextInt(300 - 200 + 1) + 200);
//
//                } //	Sub Q 15
//                else if (this.getCoordenada_y() - circ_y > Math.sin(Math.PI / 8)) {
//                    System.out.println("q15");
//                    angle = (double) (randNum.nextInt(265 - 180 + 1) + 180);
//
//                } //	Sub Q 16
//                else {
//                    System.out.println("q16");
//                    angle = (double) (randNum.nextInt(220 - 140 + 1) + 140);
//
//                }
//
//            }//end Quadrant 4
//            //	Quadrant 1
//            else {
//
//                //	Sub Q 4
//                if (this.getCoordenada_y() - circ_y < -Math.sin(3 * Math.PI / 8)) {
//                    System.out.println("q4");
//                    angle = (double) (randNum.nextInt(30 - 0 + 1) + 0);
//                    //angle = (double) (randNum.nextInt(130 - 50 + 1) + 50);
//                    System.out.println("angle: " + angle);
//
//                } //	Sub Q 3
//                else if (this.getCoordenada_y() - circ_y < -Math.sin(Math.PI / 4)) {
//                    System.out.println("q3");
//                    angle = (double) (randNum.nextInt(45 - 30 + 1) + 30);
//                    //angle = (double) (randNum.nextInt(175 - 85 + 1) + 85);
//
//                } //	Sub Q 2
//                else if (this.getCoordenada_y() - circ_y < -Math.sin(Math.PI / 8)) {
//                    System.out.println("q2");
//                    angle = (double) (randNum.nextInt(185 - 105 + 1) + 105);
//
//                } //	Sub Q 1
//                else {
//                    System.out.println("q1");
//                    angle = (double) (randNum.nextInt(220 - 140 + 1) + 140);
//
//                }
//            }//end Quadrant 1
//
//        }//end right side of boundary
//        //	Left side of boundary
//        else {
//
//            //	Quadrant 3
//            if (this.getCoordenada_y() > circ_y) {
//
//                //	Sub Q 12
//                if (this.getCoordenada_y() - circ_y > Math.sin(3 * Math.PI / 8)) {
//                    System.out.println("q12");
//                    angle = (double) (randNum.nextInt(225 - 210 + 1) + 210);
//
//                } //	Sub Q 11
//                else if (this.getCoordenada_y() - circ_y > Math.sin(Math.PI / 4)) {
//                    System.out.println("q11");
//                    angle = (double) (randNum.nextInt(350 - 270 + 1) + 270);
//
//                } //	Sub Q 10
//                else if (this.getCoordenada_y() - circ_y > Math.sin(Math.PI / 8)) {
//                    System.out.println("q10");
//                    angle = (double) (randNum.nextInt(90) - 85);
//
//                } //	Sub Q 9
//                else {
//                    System.out.println("q9");
//                    angle = (double) (randNum.nextInt(80) - 40);
//
//                }
//            }//end Quadrant 3
//            //	Quadrant 2
//            else {
//
//                //	Sub Q 5
//                if (this.getCoordenada_y() - circ_y < -Math.sin(3 * Math.PI / 8)) {
//                    System.out.println("q5");
//                    angle = (double) (randNum.nextInt(130 - 50 + 1) + 50);
//
//                } //	Sub Q 6
//                else if (this.getCoordenada_y() - circ_y < -Math.sin(Math.PI / 4)) {
//                    System.out.println("q6");
//                    angle = (double) (randNum.nextInt(95 - 5 + 1) + 5);
//
//                } //	Sub Q 7
//                else if (this.getCoordenada_y() - circ_y < -Math.sin(Math.PI / 8)) {
//                    System.out.println("q7");
//                    angle = (double) (randNum.nextInt(80));
//
//                } //	Sub Q 8
//                else {
//                    System.out.println("q8");
//                    angle = (double) (randNum.nextInt(80) - 40);
//
//                }
//            }//end Quadrant 2
//
//        }//end left side of boundary
//        this.setDireccion_y((int) (speed * Math.sin(Math.toRadians(angle))));
//        this.setDireccion_x((int) (speed * Math.cos(Math.toRadians(angle))));
        //end if (changeAngle)
        /*
         TODO ESTO es para el rebote si la bola viene BAJANDO.
         Hay que hacer otro para cuando la bola va SUBIENDO.
         */
        switch (segmento) {

            case S_I: {
                System.out.println("DIRECCION Y: " + this.getDireccion_y());
                if (this.getDireccion_y() < 0) { //Si la bola está subiendo
                    if (0 <= theta && theta < 30) { // Si se encuentra entre los ángulos 0 y 30
                        angulo_inf = 180;
                        angulo_sup = 210;
                        angle = (double) (randNum.nextInt(180 - 150 + 1) + 150);
                        System.out.println("0 a 30\n\tangulo = " + angle);
                        if (theta <= 15) {//Si theta es mayor a 15 no hay punto
                            punto = 1;
                        }

                    } else if (30 <= theta && theta < 45) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 210;
                        angulo_sup = 225; //CON 225 SE SALE
                        angle = (double) (randNum.nextInt(150 - 135 + 1) + 135);
                        System.out.println("30 a 45\n\tangulo = " + angle);
                        punto = 2;

                    } else if (45 <= theta && theta < 60) { // Si se encuentra entre los ángulos 45 y 60
                        //ESTOS YA NO SIGUEN LOS ÁNGULOS PARA EVITAR QUE SE SALGA LA BOLA
                        angulo_inf = 210;
                        angulo_sup = 215;
                        angle = (double) (randNum.nextInt(135 - 120 + 1) + 120);
                        System.out.println("45 a 60\n\tangulo = " + angle);
                        punto = 2;

                    } else if (60 <= theta && theta < 90) { // Si se encuentra entre los ángulos 60 y 90
                        //ESTOS YA NO SIGUEN LOS ÁNGULOS PARA EVITAR QUE SE SALGA LA BOLA
                        angulo_inf = 180;
                        angulo_sup = 210;
                        angle = (double) (randNum.nextInt(120 - 90 + 1) + 90);
                        System.out.println("60 a 90\n\tangulo = " + angle);
                        if (theta >= 75) {//Si theta es mayor a 15 no hay punto
                            punto = 1;
                        }
                    }
                } else {//Si la bola está bajando
                    System.out.println("Theta: " + theta);
                    if (0 <= theta && theta < 30) { // Si se encuentra entre los ángulos 0 y 30
                        angulo_inf = 150;
                        angulo_sup = 180;
                        angle = (double) (randNum.nextInt(180 - 150 + 1) + 150);
                        System.out.println("0 a 30\n\tangulo = " + angle);
                        if (theta <= 15) {//Si theta es mayor a 15 no hay punto
                            punto = 1;
                        }
                    } else if (30 <= theta && theta < 45) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 135;
                        angulo_sup = 150;
                        angle = (double) (randNum.nextInt(150 - 135 + 1) + 135);
                        System.out.println("30 a 45\n\tangulo = " + angle);
                        punto = 2;

                    } else if (45 <= theta && theta < 60) { // Si se encuentra entre los ángulos 45 y 60
                        angulo_inf = 120;
                        angulo_sup = 135;
                        angle = (double) (randNum.nextInt(135 - 120 + 1) + 120);
                        System.out.println("45 a 60\n\tangulo = " + angle);
                        punto = 2;

                    } else if (60 <= theta && theta < 90) { // Si se encuentra entre los ángulos 60 y 90
                        angulo_inf = 90;
                        angulo_sup = 120;
                        angle = (double) (randNum.nextInt(120 - 90 + 1) + 90);
                        System.out.println("60 a 90\n\tangulo = " + angle);
                        if (theta >= 75) {//Si theta es mayor a 15 no hay punto
                            punto = 1;
                        }
                    }

                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                break;

            }

            case S_II: {
                if (this.getDireccion_y() < 0) { //Si la bola está subiendo
                    if (90 <= theta && theta < 120) { // Si se encuentra entre los ángulos 90 y 120
                        angulo_inf = 60;
                        angulo_sup = 90;
                        System.out.println("90 a 120\n\tangulo = ");
                        if (theta <= 105) {
                            punto = 1;
                        }

                    } else if (120 <= theta && theta < 135) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 330;
                        angulo_sup = 340;
                        System.out.println("120 a 135\n\tangulo = ");
                        punto = 2;

                    } else if (135 <= theta && theta < 150) { // Si se encuentra entre los ángulos 45 y 60
                        angulo_inf = 315;
                        angulo_sup = 330;
                        System.out.println("135 a 150\n\tangulo = ");
                        punto = 2;
                    } else if (150 <= theta && theta < 180) { // Si se encuentra entre los ángulos 60 y 90
                        angulo_inf = 330;
                        angulo_sup = 360;
                        System.out.println("150 a 180\n\tangulo = ");
                        if (theta >= 165) {
                            punto = 1;
                        }
                    }
                } else {
                    System.out.println("Theta: " + theta);

                    if (90 <= theta && theta < 120) { // Si se encuentra entre los ángulos 90 y 120
                        angulo_inf = 60;
                        angulo_sup = 90;
                        System.out.println("90 a 120\n\tangulo = ");
                        if (theta <= 105) {
                            punto = 1;
                        }
                    } else if (120 <= theta && theta < 135) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 45;
                        angulo_sup = 60;
                        System.out.println("120 a 135\n\tangulo = ");
                        punto = 2;
                    } else if (135 <= theta && theta < 150) { // Si se encuentra entre los ángulos 45 y 60
                        angulo_inf = 30;
                        angulo_sup = 45;
                        System.out.println("135 a 150\n\tangulo = ");
                        punto = 2;
                    } else if (150 <= theta && theta < 180) { // Si se encuentra entre los ángulos 60 y 90
                        angulo_inf = 0;
                        angulo_sup = 30;
                        System.out.println("150 a 180\n\tangulo = ");
                        if (theta >= 165) {
                            punto = 1;
                        }
                    }
                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                System.out.println(angle);
                break;
            }
            case S_III: {
                theta *= (-1);
                theta = 360 - theta;
                System.out.println("theta: " + theta);
                if (this.getDireccion_y() < 0) { //Si sube
                    if (180 <= theta && theta < 210) { // Si se encuentra entre los ángulos 0 y 30
                        angulo_inf = 330;
                        angulo_sup = 350;
                        System.out.println("180 a 210\n\tangulo = ");
                        if (theta <= 195) {
                            punto = 1;
                        }

                    } else if (210 <= theta && theta < 225) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 315;
                        angulo_sup = 330;
                        System.out.println("210 a 225\n\tangulo = ");
                        punto = 2;
                    } else if (225 <= theta && theta < 240) { // Si se encuentra entre los ángulos 45 y 60
                        angulo_inf = 300;
                        angulo_sup = 315;
                        System.out.println("225 a 240\n\tangulo = ");
                        punto = 2;
                    } else if (240 <= theta && theta < 270) { // Si se encuentra entre los ángulos 60 y 90
                        angulo_inf = 270;
                        angulo_sup = 300;
                        System.out.println("240 a 270\n\tangulo = ");
                        if (theta >= 255) {
                            punto = 1;
                        }
                    }
                } else {

                    if (180 <= theta && theta < 210) { // Si se encuentra entre los ángulos 0 y 30
                        angulo_inf = 0;
                        angulo_sup = 30;
                        System.out.println("180 a 210\n\tangulo = ");
                        if (theta <= 195) {
                            punto = 1;
                        }

                    } else if (210 <= theta && theta < 225) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 10;
                        angulo_sup = 30;
                        System.out.println("210 a 225\n\tangulo = ");
                        punto = 2;
                    } else if (225 <= theta && theta < 240) { // Si se encuentra entre los ángulos 45 y 60
                        angulo_inf = 25;
                        angulo_sup = 30;
                        System.out.println("225 a 240\n\tangulo = ");
                        punto = 2;
                    } else if (240 <= theta && theta < 270) { // Si se encuentra entre los ángulos 60 y 90
                        angulo_inf = 270;
                        angulo_sup = 300;
                        System.out.println("240 a 270\n\tangulo = ");
                        if (theta >= 255) {
                            punto = 1;
                        }
                    }
                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                System.out.println(angle);
                break;
            }
            case S_IV: {
                theta *= (-1);
                theta = 360 - theta;
                System.out.println(theta);
                if (this.getDireccion_y() < 0) {//Si sube

                    if (270 <= theta && theta < 300) { // Si se encuentra entre los ángulos 0 y 30
                        angulo_inf = 315;
                        angulo_sup = 330;
                        System.out.println("270 a 300\n\tangulo = ");

                    } else if (300 <= theta && theta < 315) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 225;
                        angulo_sup = 240;
                        System.out.println("300 a 315\n\tangulo = ");
                    } else if (315 <= theta && theta < 330) { // Si se encuentra entre los ángulos 45 y 60
                        angulo_inf = 135;
                        angulo_sup = 150;
                        System.out.println("315 a 330\n\tangulo = ");
                    } else if (330 <= theta && theta < 360) { // Si se encuentra entre los ángulos 60 y 90
                        angulo_inf = 180;
                        angulo_sup = 210;
                        System.out.println("330 a 360\n\tangulo = ");

                    }

                } else { //Si baja
                    if (270 <= theta && theta < 300) { // Si se encuentra entre los ángulos 0 y 30
                        angulo_inf = 240;
                        angulo_sup = 270;
                        System.out.println("270 a 300\n\tangulo = ");

                    } else if (300 <= theta && theta < 315) { // Si se encuentra entre los ángulos 30 y 45
                        angulo_inf = 150;
                        angulo_sup = 160;
                        System.out.println("300 a 315\n\tangulo = ");
                    } else if (315 <= theta && theta < 330) { // Si se encuentra entre los ángulos 45 y 60
                        angulo_inf = 150;
                        angulo_sup = 180;
                        System.out.println("315 a 330\n\tangulo = ");
                    } else if (330 <= theta && theta < 360) { // Si se encuentra entre los ángulos 60 y 90
                        angulo_inf = 135;
                        angulo_sup = 150;
                        System.out.println("330 a 360\n\tangulo = ");

                    }
                }

                angle = (double) (randNum.nextInt(angulo_sup - angulo_inf + 1) + angulo_inf);
                System.out.println(angle);
                break;
            }
        }
        this.setDireccion_x((int) (speed * Math.cos(Math.toRadians(angle))));
        this.setDireccion_y((int) (speed * Math.sin(Math.toRadians(angle))));
        System.out.println("x: " + this.getDireccion_x());
        System.out.println("y: " + this.getDireccion_y());
    }

    private boolean chocaRaquetaVertical(Model model) {
        if ((this.getCoordenada_y() + radio * 2 >= model.getRaqueta().getCoordenada_y() && this.getCoordenada_y() <= model.getRaqueta().getCoordenada_y() + model.getRaqueta().getAltura()) && (this.getCoordenada_x() >= model.getRaqueta().getCoordenada_x() && this.getCoordenada_x() + radio < model.getRaqueta().getCoordenada_x() + model.getRaqueta().getBase())) {
//            System.out.println("Vertical");
            return true;
        }
        return false;
    }

    private boolean chocaRaquetaHorizontal(Model model) {
        if ((this.getCoordenada_x() + this.radio * 2 >= model.getRaqueta().getCoordenada_x() && this.getCoordenada_x() <= model.getRaqueta().getCoordenada_x() + model.getRaqueta().getBase()) && (this.getCoordenada_y() >= model.getRaqueta().getCoordenada_y() && this.getCoordenada_y() + radio < model.getRaqueta().getCoordenada_y() + model.getRaqueta().getAltura())) {
//            System.out.println("Horizontal");
            return true;
        }
        return false;
    }

    private boolean interior(int x1, int y1, int x2, int y2, Model model) {
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

    private int segmento(Circunferencia c) {
        int xb = this.getCoordenada_x();
        int yb = this.getCoordenada_y();
        int x = c.getCoordenada_x();
        int y = c.getCoordenada_y();
        int r = c.getRadio();

        int cen_bola_x = this.getCoordenada_x() + radio;
        int cen_bola_y = this.getCoordenada_y() + radio;

        //Se determina el segmento en el que se encuentra la bola;
        if ((cen_bola_x >= x + r && cen_bola_x < x + 2 * r) && (cen_bola_y > y && cen_bola_y <= y + r)) { //Segmento I
            return S_I;
        } else if ((cen_bola_x <= x + r && cen_bola_x > x) && (cen_bola_y <= y + r && cen_bola_y > y)) { //Segemento II 
            return S_II;
        } else if ((cen_bola_x <= x + r && cen_bola_x > x) && (cen_bola_y >= y + r && cen_bola_y < y + 2 * r)) { //Segmento III
            return S_III;
        } else if ((cen_bola_x >= x + r && cen_bola_x < x + 2 * r) && (cen_bola_y >= y + r && cen_bola_y < y + 2 * r)) { //Segmento IV
            return S_IV;
        }

        return -1;

    }

    private final int S_I = 0;
    private final int S_II = 1;
    private final int S_III = 2;
    private final int S_IV = 3;
    public int punto = 0;
    /*
     0 : No hay cambio
     1 : Gana punto (Poner en 0 después)
     2 : Pierde punto (Poner en 0 después)
     */

    private boolean reboto = false;

}
