// Bola.java 
// Autor: José Alexander Brenes Brenes
// Clase para el uso de vectores de orden 2
package dodgeball;

/**
 *
 * @author alex
 */
public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector multiplicacionEscalar(double a) {
        return new Vector(a * x, a * y);
    }

    public Vector normalizar() {
        return this.multiplicacionEscalar(1 / magnitud());
    }

    public double magnitud() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "\n(" + x + "," + y + ")";
    }
    public double productoP(Vector a){
        return this.getX() * a.getX() + this.getY() * a.getY();
    }
    public Vector suma(Vector a){
        return new Vector(this.getX() + a.getX(),this.getY() + a.getY());
    }
    public Vector resta(Vector a){
        return suma(multiplicacionEscalar(-1));
    }

}
