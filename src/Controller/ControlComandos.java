/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Circulo;
import Model.Cuadrado;
import Model.Dona;
import Model.Figura;
import Model.Punto;
import Model.Rectangulo;
import Model.Triangulo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import View.Interfaz;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Luver
 */
public class ControlComandos {
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";
public static final String ANSI_RESET = "\u001B[0m";

    private ArrayList<Figura> figuras;
    private boolean termina = false;
    private int indice = 1;

    private static ControlComandos instancia;

    public static ControlComandos getInstance() {
        if (instancia == null) {
            instancia = new ControlComandos();
        }
        return instancia;
    }

    private static final Stroke TRAZO_BASE = new BasicStroke(1.5f);
    private static final Color[] COLORES_BASE = {
        new Color(1.0f, 0.7f, 0.7f),
        new Color(0.7f, 0.7f, 1.0f),
        new Color(1.0f, 0.85f, 0.7f),
        new Color(0.85f, 1.0f, 0.7f),
        new Color(1.0f, 0.7f, 0.85f),};

    public ControlComandos() {
        this.figuras = new ArrayList<>();
    }

    public void iniciaComandos() {

        Scanner sc = new Scanner(System.in);
        String comandoRecibido;
        while (!termina) {
            System.out.println("\n" + "Ingrese un comando:");
            comandoRecibido = sc.nextLine();
            String[] stringSeparado = SeparaComando(comandoRecibido);
            leeComandos(stringSeparado);
        }
        System.exit(0);
    }

    public void leeComandos(String[] stringSeparado) {
        Random rnd = new Random();
        boolean comandoLeido = false;

        if (stringSeparado[0].equals("circle") || stringSeparado[0].equals("Circle")) {
            if (stringSeparado.length != 4) {
                System.err.println("Cantidad de argumentos invalida para el comando circle");
            } else {
                double x = Double.parseDouble(stringSeparado[1]);
                double y = Double.parseDouble(stringSeparado[2]);
                double radio = Double.parseDouble(stringSeparado[3]);
                Punto punto = new Punto(x, y);
                Figura circulo = new Circulo(punto, radio, "Circulo", Integer.toString(indice), TRAZO_BASE, COLORES_BASE[rnd.nextInt(COLORES_BASE.length)]);
                System.out.println(circulo.toString());
                figuras.add(circulo);
                indice++;
                Interfaz i = Interfaz.getInstance();
                i.generarPrueba(figuras);

            }
            comandoLeido = true;

        }

        if (stringSeparado[0].equals("square") || stringSeparado[0].equals("Square")) {
            if (stringSeparado.length != 4) {
                System.err.println("Cantidad de argumentos invalida para el comando square");
            } else {
                double x = Double.parseDouble(stringSeparado[1]);
                double y = Double.parseDouble(stringSeparado[2]);
                double longitudLado = Double.parseDouble(stringSeparado[3]);
                Punto punto = new Punto(x, y);
                Cuadrado cuadrado = new Cuadrado(punto, longitudLado, "Cuadrado", Integer.toString(indice), TRAZO_BASE, COLORES_BASE[rnd.nextInt(COLORES_BASE.length)]);
                System.out.println(cuadrado.toString());
                indice++;
                figuras.add(cuadrado);
                Interfaz i = Interfaz.getInstance();
                i.generarPrueba(figuras);

            }
            comandoLeido = true;
        }
        if (stringSeparado[0].equals("rectangle") || stringSeparado[0].equals("Rectangle")) {
            if (stringSeparado.length != 5) {
                System.err.println("Cantidad de argumentos invalida para el comando rectangle");
            } else {
                double x = Double.parseDouble(stringSeparado[1]);
                double y = Double.parseDouble(stringSeparado[2]);
                double base = Double.parseDouble(stringSeparado[3]);
                double altura = Double.parseDouble(stringSeparado[4]);
                Punto punto = new Punto(x, y);
                Rectangulo rectangulo = new Rectangulo(punto, base, altura, "Rectangulo", Integer.toString(indice), TRAZO_BASE, COLORES_BASE[rnd.nextInt(COLORES_BASE.length)]);
                System.out.println(rectangulo.toString());
                indice++;
                figuras.add(rectangulo);
                Interfaz i = Interfaz.getInstance();
                i.generarPrueba(figuras);

            }
            comandoLeido = true;
        }
        if (stringSeparado[0].equals("triangle") || stringSeparado[0].equals("Triangle")) {
            if (stringSeparado.length != 7) {
                System.err.println("Cantidad de argumentos invalida para el comando triangle");
            } else {
                double x = Double.parseDouble(stringSeparado[1]);
                double y = Double.parseDouble(stringSeparado[2]);
                double x1 = Double.parseDouble(stringSeparado[3]);
                double y1 = Double.parseDouble(stringSeparado[4]);
                double x2 = Double.parseDouble(stringSeparado[5]);
                double y2 = Double.parseDouble(stringSeparado[6]);
                Punto punto = new Punto(x, y);
                Punto punto2 = new Punto(x1, y1);
                Punto punto3 = new Punto(x2, y2);
                Triangulo triangulo = new Triangulo(punto, punto2, punto3, "Triangulo", Integer.toString(indice), TRAZO_BASE, COLORES_BASE[rnd.nextInt(COLORES_BASE.length)]);
                indice++;
                System.out.println(triangulo.toString());
                figuras.add(triangulo);
                Interfaz i = Interfaz.getInstance();
                i.generarPrueba(figuras);
            }
            comandoLeido = true;
        }
        if (stringSeparado[0].equals("donut") || stringSeparado[0].equals("Donut")) {
            if (stringSeparado.length != 5) {
                System.err.println("Cantidad de argumentos invalida para el comando donut");
            } else {
                double x = Double.parseDouble(stringSeparado[1]);
                double y = Double.parseDouble(stringSeparado[2]);
                double radioMenor = Double.parseDouble(stringSeparado[3]);
                double radioMayor = Double.parseDouble(stringSeparado[4]);
                Punto punto = new Punto(x, y);
                Dona dona = new Dona(punto, radioMenor, radioMayor, "Dona", Integer.toString(indice), TRAZO_BASE, COLORES_BASE[rnd.nextInt(COLORES_BASE.length)]);
                indice++;
                System.out.println(dona.toString());
                figuras.add(dona);
                Interfaz i = Interfaz.getInstance();
                i.generarPrueba(figuras);

            }
            comandoLeido = true;
        }

        if (stringSeparado[0].equals("list") || stringSeparado[0].equals("list")) {
            System.out.println("El total del area de todas las figuras es: " + ImprimeLista());
            Interfaz i = Interfaz.getInstance();
            i.generarPrueba(figuras);
            comandoLeido = true;
        }

        if (stringSeparado[0].equals("delete") || stringSeparado[0].equals("Delete")) {
             int numeroaEliminar = Integer.parseInt(stringSeparado[1]);
            if (stringSeparado.length != 2) {
                System.err.println("Cantida de argumentos invalida para el comando delete");

            }
            
            
            if (figuras.isEmpty()) {
                System.err.println("No hay figuras para ser borradas");

            }
            if(EliminaFigura(numeroaEliminar)){
                System.out.println("El total del area de todas las figuras es: " + ImprimeLista());
                Interfaz i = Interfaz.getInstance();
                i.generarPrueba(figuras);
            }
            else{
                System.err.print("No existe esa figura");
            }
 
            comandoLeido = true;
        }

        if (stringSeparado[0].equals("Exit") || stringSeparado[0].equals("exit")) {
            if (stringSeparado.length == 1) {
                termina = true;
                comandoLeido = true;
            } else {
                System.err.println("Comando no reconocido.");
            }

        }
        if (isNumeric(stringSeparado[0])) {
            double x = Double.parseDouble(stringSeparado[0]);
            double y = Double.parseDouble(stringSeparado[1]);
            Punto punto = new Punto(x, y);
            boolean hayfigura = false;
              ArrayList<Figura> figurasEnPunto = new ArrayList<>();
            for (Figura f : figuras) {
                if (f.ContienePunto(punto)) {
                  
                    System.out.println(f.toString());
                    figurasEnPunto.add(f);
                    
                    hayfigura = true;
                    comandoLeido = true;

                }

            }
                    Interfaz i = Interfaz.getInstance();
                    i.generarPrueba(figurasEnPunto);
            if (!hayfigura) {
                System.err.print("Ninguna figura con tiene al punto: (" + punto.getX() + "," + punto.getY() + ")");
                comandoLeido = true;
            }
        }

        if (stringSeparado[0].equals("Help") || stringSeparado[0].equals("help")) {

            System.out.println("Instrucciones de uso\n");
            System.out.println("COMANDOS SECUNDARIOS \n");
            System.out.println(ANSI_GREEN+"Exit "+ANSI_RESET+": para salir del programa");
            System.out.println(ANSI_GREEN+"Delete"+ANSI_RESET+": y el numero de la figura para borrarla");
            System.out.println(ANSI_GREEN+"List"+ANSI_RESET+": para mostrar la lista de figuras con su area");
            System.out.println("Digite las coordenadas X e Y para mostrar la lista de figuras que contienen a ese punto \n");

           System.out.println("COMANDOS PARA INGRESAR FIGURAS \n");
            System.out.println(ANSI_GREEN+"circle (3)"+ANSI_RESET+": los parámetros son las coordenadas X e Y del centro seguido por el radio");
            System.out.println(ANSI_GREEN+"square (3)"+ANSI_RESET+": Para el cuadrado los parámetros son las coordendas X e Y de la esquina superior izquierda seguidas por la longitud del lado");
            System.out.println(ANSI_GREEN+"Rectangle (4)"+ANSI_RESET+": Para el rectángulo los parámetros son las coordenadas X e Y de la esquina superior izquierda seguidas por los dos lados (base y altura)");
            System.out.println(ANSI_GREEN+"Triangle (6) "+ANSI_RESET+":  Para el triángulo los parámetros son las coordenadas X e Y de cada uno de los tres vértices (seis números en total).");
            System.out.println(ANSI_GREEN+"Donut(4) "+ANSI_RESET+": Para la dona los parámetros son las coordenadas X e Y del centro seguidas por los dos radios (menor y mayor).");
            System.out.println("NOTA: \nAl buscar un punto contenido en una figura ,el programa dibujara todas las figuras que contienen a dicho punto \n"
                    + "para volver a ver el total de figuras digite el comando list");
            
            comandoLeido = true;
        }

        if (comandoLeido == false) {
            System.err.println("Digite un comando valido");
        }

    }

    public String[] SeparaComando(String comando) {
        String[] comandosSeparados;
        comandosSeparados = comando.split(" ");
        return comandosSeparados;
    }

    private static boolean isNumeric(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public double ImprimeLista() {
        for (Figura f : figuras) {
            System.out.println(f.toString() + " Con un area de: " + f.Area());
        }
        return AreaTotal();
    }

    public double AreaTotal() {
        double areatotal = 0;
        for (Figura f : figuras) {
            areatotal += f.Area();
        }
        return areatotal;
    }

    public ArrayList<Figura> getFiguras() {
        return figuras;
    }

    public void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        this.figuras.clear();
        this.indice = 1;
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            String[] comandos = SeparaComando(cadena);
            leeComandos(comandos);
        }
        System.out.println("\n" + "Ingrese un comando:");

        b.close();
    }
    
    
    public boolean EliminaFigura(int idaeliminar){
        int contador = 0;
        for(Figura F:figuras){
         
           int nombre = Integer.parseInt(F.getNombre());
           if(nombre==idaeliminar){
               figuras.remove(contador);
               return true;
           }
             contador++;
        }
        return false;
    }
    

}
