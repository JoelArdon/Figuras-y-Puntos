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
public class Cuadrado extends Figura {

    private double longitud_lado;

    /**
     * @return the longitud_lado
     */
    public double getLongitud_lado() {
        return longitud_lado;
    }

    /**
     * @param longitud_lado the longitud_lado to set
     */
    public void setLongitud_lado(double longitud_lado) {
        this.longitud_lado = longitud_lado;

    }

    public Cuadrado(Punto punto, double longitud_lado, String tipo, String nombre, Stroke trazo, Color colorTrazo) {
        super(punto, tipo, nombre, trazo, colorTrazo);
        this.longitud_lado = longitud_lado;

    }

    public double Area() {
        return longitud_lado * longitud_lado;
    }

    public String toString() {
        return "figura " + this.nombre + ": " + this.tipo + " con esquina superior izquierda en " + "(" + this.punto.getX() + "," + this.punto.getY() + ") y longitud de lado de " + this.longitud_lado;
    }
      public boolean ContienePunto(Punto puntoAbuscar){
        if (puntoAbuscar.getX() >= punto.getX() && puntoAbuscar.getX()<=punto.getX()+longitud_lado && puntoAbuscar.getY()>=punto.getY()
                &&puntoAbuscar.getY()<=punto.getY()+longitud_lado){
            return true;
        }

            return false;
        
    }

    @Override
    public void dibujar(Graphics bg, float escala) {
        Graphics2D g = (Graphics2D) bg;

        g.setColor(colorTrazo);
        g.fillRect(
                (int) (escala * punto.getX()),
                (int) (escala * punto.getY()),
                (int) (escala * longitud_lado),
                (int) (escala * longitud_lado)
        );

        g.setStroke(trazo);
        g.setColor(colorFigura);
        g.drawRect(
                (int) (escala * punto.getX()),
                (int) (escala * punto.getY()),
                (int) (escala * longitud_lado),
                (int) (escala * longitud_lado)
        );
    }

}
