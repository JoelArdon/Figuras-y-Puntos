/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Stroke;

/**
 *
 * @author Luver
 */
public abstract class Figura {

    protected Punto punto;
    protected String tipo;
    protected String nombre;

    protected final Stroke trazo;
    protected final Color colorTrazo;
    protected final Color colorFigura;

    Figura(Punto punto, String tipo, String nombre, Stroke trazo, Color color) {
        this.punto = punto;
        this.tipo = tipo;
        this.nombre = nombre;
        this.trazo = trazo;
        float[] c = color.getColorComponents(null);
        this.colorTrazo = new Color(c[0], c[1], c[2], 0.35f);
        this.colorFigura = color.darker();
    }

    public abstract void dibujar(Graphics bg, float escala);

    public abstract double Area();
      
    public abstract boolean ContienePunto(Punto punto);

     public String getNombre(){
         return nombre;
     }
    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }
}
