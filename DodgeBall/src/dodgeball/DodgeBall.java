// DodgeBall.java 
// Autor: José Alexander Brenes Brenes
// El programa consiste en un juego en el que se controla el rebote de una
// bola con una raqueta, para así obtener o perder puntos si rebota en las zonas
// definidas.

package dodgeball;

public class DodgeBall {

    public static void main(String[] args) throws InterruptedException {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setVisible(true);
        for (;;) {
            model.avanzar();
            try {
                Thread.sleep(42);
            } catch (InterruptedException e) {

            }
        }
        
    }

}
