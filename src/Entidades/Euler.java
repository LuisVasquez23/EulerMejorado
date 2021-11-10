package Entidades;

public class Euler {
    private double x_inicial;
    private double x_final;
    private double y_inicial;
    private double h;
    private double[] x;
    private double[] y;

    // Metodos getter
    public double getX_inicial() {
        return x_inicial;
    }

    public double getX_final() {
        return x_final;
    }

    public double getY_inicial() {
        return y_inicial;
    }

    public double getH() {
        return h;
    }
    
    // Metodos setter

    public void setX_inicial(double x_inicial) {
        this.x_inicial = x_inicial;
    }

    public void setX_final(double x_final) {
        this.x_final = x_final;
    }

    public void setH(double h) {
        this.h = h;
    }

    // Cargar los valores de X
    public void cargarValoresX(){
       int i = 1;
       this.x[0] = this.x_inicial;
       
       while( i < Integer.parseInt(Double.toString(this.x_final))){
           this.x[i] = this.x[i-1] + this.h;
       }
       
    }
    
    // Cargar los valores de Y
    public void cargarValoresY(){
        
    }
    
}
