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
public class Rectangulo extends Figura {

    /**
     * @return the base
     */
    public double getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(double base) {
        this.base = base;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }
    private double base;
    private double altura;

    public Rectangulo(Punto punto, double base, double altura, String tipo, String nombre, Stroke trazo, Color colorTrazo) {
        super(punto, tipo, nombre, trazo, colorTrazo);

        this.base = base;
        this.altura = altura;
    }

    @Override
    public double Area() {
        return base * altura;
    }

    @Override
    public String toString() {
        return "figura " + this.nombre + ": " + this.tipo + " con esquina superior izquierda en " + "(" + this.punto.getX() + "," + this.punto.getY() + ") y altura de " + this.altura + " base de " + this.base;
    }
    
     public boolean ContienePunto(Punto puntoAbuscar){
        if (puntoAbuscar.getX() >= punto.getX() && puntoAbuscar.getX()<=punto.getX()+base && puntoAbuscar.getY()>=punto.getY()
                &&puntoAbuscar.getY()<=punto.getY()+altura){
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
                (int) (escala * base),
                (int) (escala * altura)
        );

        g.setStroke(trazo);
        g.setColor(colorFigura);
        g.drawRect(
                (int) (escala * punto.getX()),
                (int) (escala * punto.getY()),
                (int) (escala * base),
                (int) (escala * altura)
        );
    }

}
