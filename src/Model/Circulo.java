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
public class Circulo extends Figura {

    private double radio;

    public Circulo(Punto punto, double radio, String tipo, String nombre, Stroke trazo, Color colorTrazo) {
        super(punto, tipo, nombre, trazo, colorTrazo);
        this.radio = radio;
    }
    
    

    public double Area() {
        return Math.PI * (radio * radio);
    }
    
    public boolean ContienePunto(Punto punto1){
        if ((Math.sqrt(Math.pow(punto.getX()-punto1.getX(),2)+Math.pow(punto.getY()-punto1.getY(),2)))<radio){
            return true;
        }
        return false;
    }

    /**
     * @return the radio
     */
    public double getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }

    public String toString() {
        return "figura " + this.nombre + ": " + this.tipo + " con centro en " + "(" + this.punto.getX() + "," + this.punto.getY() + ") y radio " + this.radio;
    }

    @Override
    public void dibujar(Graphics bg, float escala) {
        Graphics2D g = (Graphics2D) bg;

        g.setColor(colorTrazo);
        g.fillOval(
                (int) (escala *( punto.getX()-(radio))),
                (int) (escala * (punto.getY()-(radio))),
                (int) (escala * radio *2),
                (int) (escala * radio *2)
        );

        g.setStroke(trazo);
        g.setColor(colorFigura);
        g.drawOval(
                (int) (escala *( punto.getX()-(radio))),
                (int) (escala * (punto.getY()-(radio))),
                (int) (escala * radio*2 ),
                (int) (escala * radio*2));

    }
}
