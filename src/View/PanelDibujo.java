package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class PanelDibujo extends JPanel {

    public PanelDibujo() {
        super(true);
        configurar();
    }

    private void configurar() {
        setBackground(new Color(220, 220, 220));
        setCursor(new Cursor(Cursor.MOVE_CURSOR));

        MouseAdapter m = new MouseAdapter() {
            @Override

            public void mouseDragged(MouseEvent e) {
                if (origen != null) {
                    reposicionar(e.getPoint());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fijarOrigen(e.getPoint());
            }

        };

        addMouseListener(m);
        addMouseMotionListener(m);
    }

    public void reposicionar(Point e) {
        JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(
                JViewport.class, PanelDibujo.this);
        if (viewPort != null) {
            int deltaX = origen.x - e.x;
            int deltaY = origen.y - e.y;

            Rectangle view = viewPort.getViewRect();
            view.x += deltaX;
            view.y += deltaY;

            scrollRectToVisible(view);
        }
    }

    public void fijarOrigen(Point origen) {
        this.origen = origen;
    }

    private Point origen = new Point();
}
