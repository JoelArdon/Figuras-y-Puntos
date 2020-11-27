package View;

import Controller.ControlComandos;
import Model.Figura;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;

public class Interfaz extends JFrame {

    private static Interfaz instancia;

    public static Interfaz getInstance() {
        if (instancia == null) {
            instancia = new Interfaz();
        }
        return instancia;
    }

    public Interfaz() throws HeadlessException {
        super("Tarea 1");
        this.figuras = new ArrayList<>();
        configurar();
    }

    private void configurar() {
        ajustarComponentes(getContentPane());
        pack();
        setLocationByPlatform(true);
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout());

        JPanel d = new JPanel();
        d.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        d.setLayout(new FlowLayout(FlowLayout.LEFT));
        d.add(btnEscala = new JButton("100%"));
        d.add(etqEscala = new JLabel());
        c.add(BorderLayout.PAGE_END, d);

        c.add(BorderLayout.CENTER, new JScrollPane(
                areaDibujo = new PanelDibujo() {
            @Override
            public void paintComponent(Graphics bg) {
                super.paintComponent(bg);
                Graphics2D g = (Graphics2D) bg;

                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                figuras.forEach((f) -> {
                    f.dibujar(g, escala);
                });
            }

        },
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

        areaDibujo.setBackground(COLOR_FONDO);

        c.add(BorderLayout.LINE_END, controlEscala = new JSlider(JSlider.VERTICAL, 0, 100, 0));
        controlEscala.setMajorTickSpacing(5);
        controlEscala.setPaintTicks(true);
        controlEscala.addChangeListener((ChangeEvent e) -> {
            ajustarEscala();
            ajustarPanel();
            repaint();
        });

        btnEscala.addActionListener((ActionEvent e) -> {
            fijarEscala(1f);
            ajustarControl();
        });

        setResizable(true);
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        ajustarMenus();
    }
    
    private void ajustarMenus() {
        menuPrincipal = new JMenuBar();
        menuPrincipal.add(menuArchivo = new JMenu("Archivo"));
        //menuPrincipal.add(menuDatos = new JMenu("Datos"));

        menuArchivo.add(itemLeerArchivo = new JMenuItem("Leer archivo prueba"));
        //menuDatos.add(itemPrueba = new JMenuItem("Generar Prueba"));

        itemLeerArchivo.addActionListener((ActionEvent e) -> {
            try {
                ControlComandos c = ControlComandos.getInstance();
                c.muestraContenido("prueba.txt");
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        setJMenuBar(menuPrincipal);
    }

    private void ajustarPanel() {
        areaDibujo.setPreferredSize(new Dimension(
                (int) (escala * limites.width),
                (int) (escala * limites.height)
        ));
        areaDibujo.invalidate();
    }

    public void init(ArrayList<Figura> figuras) {
        generarPrueba(figuras);

        fijarEscala(1f);
        ajustarControl();
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | UnsupportedLookAndFeelException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            mostrarInterfaz(new ArrayList<>());

        });

        ControlComandos cc = ControlComandos.getInstance();
        cc.iniciaComandos();
    }

    private static void mostrarInterfaz(ArrayList<Figura> figuras) {
        Interfaz.getInstance().init(figuras);
    }

    private void fijarEscala(float nuevaEscala) {
        escala = Math.max(MIN_ESCALA, Math.min(MAX_ESCALA, nuevaEscala));
        etqEscala.setText(String.format("%4.2f%%", escala * 100f));
    }

    private void ajustarEscala() {
        fijarEscala(trasladar(
                controlEscala.getMinimum(), controlEscala.getMaximum(),
                MIN_ESCALA, MAX_ESCALA,
                controlEscala.getValue()));
    }

    private void ajustarControl() {
        controlEscala.setValue((int) trasladar(
                MIN_ESCALA, MAX_ESCALA,
                controlEscala.getMinimum(), controlEscala.getMaximum(),
                escala));
    }

    private float trasladar(float a0, float b0, float a1, float b1, float x) {
        float y = (x - a0) / (b0 - a0);
        return a1 + y * (b1 - a1);
    }

    public void setFiguras(ArrayList<Figura> figuras) {
        this.figuras = figuras;
    }

    public void generarPrueba(ArrayList<Figura> figuras) {
        Random rnd = new Random();

        int mp = 800;
        int ms = 40;
        int Ms = 200;

        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;

        this.figuras = figuras;

        int x = 0 + rnd.nextInt(mp);
        int y = 0 + rnd.nextInt(mp);
        int w = ms + rnd.nextInt(Ms);
        int h = ms + rnd.nextInt(Ms);

        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x + w);
        maxY = Math.max(maxY, y + h);

        limites = new Rectangle(minX, minY, maxX - minX, maxY - minY);
        limites.grow(8, 8);
        ajustarPanel();

        validate();
        repaint();
    }

    private static final Color COLOR_FONDO = new Color(220, 220, 220);
    private static final float MIN_ESCALA = 0.10f;
    private static final float MAX_ESCALA = 4.00f;

    private JPanel areaDibujo;
    private JSlider controlEscala;
    private JButton btnEscala;
    private JLabel etqEscala;
    
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemLeerArchivo;

    private float escala = 1f;
    private Rectangle limites = null;
    private List<Figura> figuras;

}
