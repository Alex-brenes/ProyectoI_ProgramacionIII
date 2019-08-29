// Controller.java 
// Autor: José Alexander Brenes Brenes
//        Juan Daniel Quirós
// Permite establecer un control de los modelos así como
// punto de intercambio de información entre Model y View
package dodgeball;

public class Controller {

    public Model model;
    public View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }

    public void continuar() {
        model.cambiaEstado();
    }
    public void pause(){
        model.cambiaEstado();
    }
    public void cambiarVelocidad(int nueva_vel){
        model.cambiaVelocidad(nueva_vel);
    }
    public void mover(int flecha) {
        model.mover(flecha);
    }

    public void detenerVer() {
        model.detenerVer();
    }

    public void detenerHor() {
        model.detenerHor();
    }

}
