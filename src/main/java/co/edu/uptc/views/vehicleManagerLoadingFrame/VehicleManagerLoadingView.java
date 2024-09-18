package co.edu.uptc.views.vehicleManagerLoadingFrame;

import javax.swing.*;

import co.edu.uptc.views.GlobalView;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class VehicleManagerLoadingView extends JFrame {
    private int borderRadius;

    public VehicleManagerLoadingView(int borderRadius) {
        this.borderRadius = borderRadius;
        this.initFrame();
        this.createWaitingLabel();
    }

    public void begin() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }

    private void initFrame() {
        setUndecorated(true);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(GlobalView.TRANSPARENT); 
    }

    private void createWaitingLabel() {
        JLabel createWaitingLabel = new JLabel("<html><div style='text-align: center;'>Espere, cargar los datos podria<br>Tomar unos minutos...<br><br>¿Ya se tomó un Tinto?</div></html>", SwingConstants.CENTER);
        createWaitingLabel.setFont(new Font("Comfortaa", 1, 24));
        createWaitingLabel.setForeground(GlobalView.WAITING_FOREGROUND);
        createWaitingLabel.setBounds(100, 10, 400, 300);
        RoundedPanel roundedPanel = new RoundedPanel(borderRadius);
        roundedPanel.setLayout(null);
        roundedPanel.add(createWaitingLabel);
        this.add(roundedPanel);
    }

    private class RoundedPanel extends JPanel {
        private int borderRadius;

        public RoundedPanel(int borderRadius) {
            this.borderRadius = borderRadius;
            setOpaque(false); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(GlobalView.WAITING_BACKGROUND); 
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borderRadius, borderRadius));

            g2.setColor(GlobalView.BORDER_COLOR); 
            g2.draw(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borderRadius, borderRadius));
        }
    }
}


