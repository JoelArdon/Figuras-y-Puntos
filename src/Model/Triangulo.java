/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 *
 * @author Luver
 */
public class Triangulo extends Figura {

    /**
     * @return the punto2
     */
    public Punto getPunto2() {
        return punto2;
    }

    /**
     * @param punto2 the punto2 to set
     */
    public void setPunto2(Punto punto2) {
        this.punto2 = punto2;
    }

    /**
     * @return the punto3
     */
    public Punto getPunto3() {
        return punto3;
    }

    /**
     * @param punto3 the punto3 to set
     */
    public void setPunto3(Punto punto3) {
        this.punto3 = punto3;
    }
    private Punto punto2;
    private Punto punto3;

    public Triangulo(Punto punto, Punto punto2, Punto punto3, String tipo, String nombre, Stroke trazo, Color colorTrazo) {
        super(punto, tipo, nombre, trazo, colorTrazo);

        this.punto2 = punto2;
        this.punto3 = punto3;
    }

    //este hay que bretearlo hay que calcular otras varas con los puntos me parece
    public double Area() {
        return 0.5 * (this.punto.getX() * this.punto2.getY() + this.punto2.getX() * this.punto3.getY() + this.punto3.getX() * this.punto.getY()
                - this.punto3.getX() * this.punto2.getY() - this.punto2.getX() * this.punto.getY() - this.punto.getX() * this.punto3.getY());
    }

    public String toString() {
        return "figura " + this.nombre + ": " + this.tipo + " con vertice 1 en " + "(" + this.punto.getX() + "," + this.punto.getY() + ") y vertice 2 en : " + "(" + this.punto2.getX() + "," + this.punto2.getY() + ")"
                + "y vertice 3 en " + "(" + this.punto3.getX() + "," + this.punto3.getY() + ")";
    }
    
    
   public boolean ContienePunto(Punto punto4){
       double valor1 = punto.getX()-punto4.getX()*((punto2.getY()-punto4.getY())-(punto2.getX()-punto4.getX()))*(punto.getY()-punto4.getY());
       double valor2 = punto2.getX()-punto4.getX()*((punto3.getY()-punto4.getY())-(punto3.getX()-punto4.getX()))*(punto2.getY()-punto4.getY());
       double valor3 = punto3.getX()-punto4.getX()*((punto.getY()-punto4.getY())-(punto.getX()-punto4.getX()))*(punto3.getY()-punto4.getY());
       if (valor1>=0 && valor2>=0 && valor3>=0){
           return true;
       }
       if(valor1<0 && valor2<0 && valor3<0){
           return true;
       }
       return false;
     
   }   

    @Override
    public void dibujar(Graphics bg, float escala) {
        Graphics2D g = (Graphics2D) bg;

        int posX[] = {(int) (escala * punto.getX()), (int) (escala * punto2.getX()), (int) (escala * punto3.getX())};
        int posY[] = {(int) (escala * punto.getY()), (int) (escala * punto2.getY()), (int) (escala * punto3.getY())};
        int cantidadVertices = 3;

        g.setColor(colorTrazo);
        g.fillPolygon(posX, posY, cantidadVertices);

        g.setStroke(trazo);
        g.setColor(colorFigura);
        g.drawPolygon(posX, posY, 3);
    }
    


}
