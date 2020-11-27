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
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Luver
 */
public class Dona extends Figura {

    /**
     * @return the radioMenor
     */
    public double getRadioMenor() {
        return radioMenor;
    }

    /**
     * @param radioMenor the radioMenor to set
     */
    public void setRadioMenor(double radioMenor) {
        this.radioMenor = radioMenor;
    }

    /**
     * @return the radioMayor
     */
    public double getRadioMayor() {
        return radioMayor;
    }

    /**
     * @param radioMayor the radioMayor to set
     */
    public void setRadioMayor(double radioMayor) {
        this.radioMayor = radioMayor;
    }
    private double radioMenor;
    private double radioMayor;

    public Dona(Punto punto, double radioMenor, double radioMayor, String tipo, String nombre, Stroke trazo, Color colorTrazo) {
        super(punto, tipo, nombre, trazo, colorTrazo);

        this.radioMayor = radioMayor;
        this.radioMenor = radioMenor;
    }

    public double Area() {
        return Math.PI * ((radioMayor * radioMayor) - (radioMenor * radioMenor));
    }

    public String toString() {
        return "figura " + this.nombre + ": " + this.tipo + " con centro en " + "(" + this.punto.getX() + "," + this.punto.getY() + ")  radio menor de " + this.radioMenor + " y radio mayor de " + this.radioMayor;

    }
    public boolean ContienePunto(Punto punto1){
          if ((Math.sqrt(Math.pow(punto.getX()-punto1.getX(),2)+Math.pow(punto.getY()-punto1.getY(),2)))<=radioMayor && (Math.sqrt(Math.pow(punto.getX()-punto1.getX(),2)+Math.pow(punto.getY()-punto1.getY(),2)))>=radioMenor){
            return true;
        }
        return false;
    }

    @Override
    public void dibujar(Graphics bg, float escala) {
        Graphics2D g = (Graphics2D) bg;

        Ellipse2D outer = new Ellipse2D.Double(
          (int) (escala *( punto.getX()-(radioMayor))),
                (int) (escala * (punto.getY()-(radioMayor))),
                (int) (escala * radioMayor *2),
                (int) (escala * radioMayor *2));
        

        Ellipse2D inner = new Ellipse2D.Double(
                (int) (escala *( punto.getX()-(radioMenor))),
                (int) (escala * (punto.getY()-(radioMenor))),
                (int) (escala * radioMenor * 2),
                (int) (escala * radioMenor * 2));
        

        Area area = new Area(outer);
        area.subtract(new Area(inner));

        /* CIRCUNFERENCIA INTERNA*/
        g.setStroke(trazo);
        g.setColor(colorFigura);
        g.drawOval(
          (int) (escala *( punto.getX()-(radioMenor))),
                (int) (escala * (punto.getY()-(radioMenor))),
                (int) (escala * radioMenor * 2),
                (int) (escala * radioMenor * 2));

        /* CIRCUNFERENCIA EXTERNA */
        g.setStroke(trazo);
        g.setColor(colorFigura);
        g.drawOval(
             (int) (escala *( punto.getX()-(radioMayor))),
                (int) (escala * (punto.getY()-(radioMayor))),
                (int) (escala * radioMayor *2),
                (int) (escala * radioMayor *2));
        

        /* PINTAR DENTRO DE LA DONA */
        g.setColor(colorTrazo);
        g.fill(area);

    }

}
