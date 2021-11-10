package Entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.nfunk.jep.JEP;

public class Euler {
    private double x_inicial;
    private double x_final;
    public double y_inicial;
    public String funcion;
    private double h;
    public ArrayList<Double> x;
    public ArrayList<Double> y;
    public ArrayList<Double> predictor;
    public ArrayList<Double> corrector;
    
    public Euler() {
        this.x = new ArrayList<>();
        this.y = new ArrayList<>();
        this.predictor = new ArrayList<>();
        this.corrector = new ArrayList<>();
    }
 
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
       int i = 0;
       this.x.add(this.x_inicial);

        while (this.x.get(i) <= this.x_final){
            double valor = this.x.get(i) + this.h;
            this.x.add(valor);
            i++;
        };
       
    }
    
    // Cargar los valores de Y
    private void cargarValoresY(){
        this.y.add(this.y_inicial);
        for (int i = 0 ; i < this.x.size()-1; i++) {
            double predictor = calcularPredictor(this.x.get(i),this.y.get(i)); // Calcular el predictot
            double corrector = calcularCorrector(i, predictor); // Calcular el corrector
            this.predictor.add(predictor);
            this.corrector.add(corrector);
            this.y.add(corrector); 
        }
        
    }
    
    // Funcion general donde llamamos los metodos para calcular todos los datos
    public void calcularValores(){
       this.cargarValoresX();
       this.cargarValoresY();
    }
    
    // Funcion que nos ayuda a evaluar la funcion
    private double EvaluarFx(Double x_value, Double y_value){
        double value = 0.0;
        try {
            
            JEP j = new JEP();
            j.addStandardConstants();
            j.addStandardFunctions();
            j.addVariable("x", x_value); //("variable", numero a evaluar)
            j.addVariable("y", y_value); //("variable", numero a evaluar)
            j.parseExpression(this.funcion);

            BigDecimal bd = new BigDecimal(j.getValue()).setScale(2, RoundingMode.HALF_UP);
            value = bd.doubleValue();

        } catch (Exception e) {
            System.out.println("Error ecuacion: " + e.getMessage());
        }
        return value;
    }
    
    // Funcion que nos permite calcular el predictor
    public double calcularPredictor(Double x_actual , Double y_actual){
        double resultado = 0.0;
        try {
            resultado = y_actual + EvaluarFx(x_actual, y_actual) * this.h;
        } catch (Exception e) {
            System.out.println("Error predictor: " + e.getMessage());
        }
        
        return resultado;
    }
    
    // Funcion que nos permite calcular el corrector y obtener el resultado
    public double calcularCorrector(int iteracionActual , double predictor){
        double corrector = 0.0;
        try {
            corrector = this.y.get(iteracionActual) 
                + ((this.h/2)*(EvaluarFx(this.x.get(iteracionActual),this.y.get(iteracionActual)) + 
                EvaluarFx(this.x.get(iteracionActual+1), predictor)));
        } catch (Exception e) {
            System.out.println("Error corrector: " + e.getMessage());
        }
        
        return corrector;
    }
    
}
