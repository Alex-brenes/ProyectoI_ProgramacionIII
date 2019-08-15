// Controller.java 
// Autor: José Alexander Brenes Brenes
// Permite establecer un control de los modelos así como
// punto de intercambio de información entre Model y View

package dodgeball;

public class Controller {
    
    public Model model;
    public View view;
    
    public Controller(Model model,View view){
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(this.model);
    }
    
}
