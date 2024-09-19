package co.edu.uptc.views.vehicleManagerLoadingFrame;

import javax.swing.*;
import co.edu.uptc.utilities.RoundedButton;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;
import lombok.Getter;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class VehicleManagerLoadingView extends JFrame {
    private int borderRadius;
    private RoundedPanel waitingPanel;
    private VehicleManagerView vehicleManagerView;
    private VehicleManagerLoadingWaitingFrame vehicleManagerLoadingWaitingFrame;

    public VehicleManagerLoadingView(VehicleManagerView vehicleManagerView,int borderRadius) {
        this.vehicleManagerView= vehicleManagerView;
        this.borderRadius = borderRadius;
        this.initFrame();
        this.createWaitingFrame();
    }

    public void begin() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }

    private void initFrame() {
        this.setUndecorated(true);
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setBackground(GlobalView.TRANSPARENT);
    }

    private void createWaitingFrame() {
        waitingPanel = new RoundedPanel(borderRadius);
        this.add(waitingPanel);
        this.waitingPanel.setLayout(null);
        this.addWaitingLabel();
        this.createYesButton();
        this.createNoButton();
    }

    private void addWaitingLabel() {
        JLabel waitingLabel = new JLabel(
                "<html><div style='text-align: center;'>Cargar los Datos Podría Tomar unos Minutos<br>¿Desea Cargar los Datos?</div></html>",
                SwingConstants.CENTER);
        waitingLabel.setFont(new Font("Comfortaa", Font.BOLD, 24));
        waitingLabel.setForeground(GlobalView.WAITING_FOREGROUND);
        waitingLabel.setBounds(5, 10, 600, 200);
        waitingPanel.add(waitingLabel);
    }

    private void createYesButton() {
        RoundedButton yesButton = new RoundedButton("Cargar Datos", 20);
        yesButton.setBackground(GlobalView.BTN_BACKGROUND);
        yesButton.setForeground(GlobalView.BTN_TEXT_BACKGROUND);
        yesButton.setBounds(100, 180, 150, 80);
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createVehicleManagerLoadingWaitingFrame();
                SwingUtilities.getWindowAncestor(waitingPanel).dispose();
                vehicleManagerView.begin();
            }
        });
        waitingPanel.add(yesButton);
    }

    private void createNoButton(){
        RoundedButton noButton = new RoundedButton("Salir", 20);
        noButton.setBackground(GlobalView.BTN_BACKGROUND);
        noButton.setForeground(GlobalView.BTN_TEXT_BACKGROUND);
        noButton.setBounds(350,180,150,80);
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        waitingPanel.add(noButton);
    }

    public void createVehicleManagerLoadingWaitingFrame(){
        vehicleManagerLoadingWaitingFrame = new VehicleManagerLoadingWaitingFrame(this);
        vehicleManagerLoadingWaitingFrame.begin();
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
            drawPanelBackground(g2);
            drawPanelBorder(g2);
        }

        private void drawPanelBackground(Graphics2D g2) {
            g2.setColor(GlobalView.WAITING_BACKGROUND);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borderRadius, borderRadius));
        }

        private void drawPanelBorder(Graphics2D g2) {
            g2.setColor(GlobalView.BORDER_COLOR);
            g2.draw(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borderRadius, borderRadius));
        }
    }
}
