package co.edu.uptc.views.vehicleManegerMainFrame;

import javax.swing.*;

import co.edu.uptc.views.GlobalView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleManagerAside extends JPanel {
    private VehicleManagerView vehicleManagerView;
    private JPanel geographicalAnalysisBtnsPanel;
    private JPanel vehicleAnalysisBtnsPanel;

    public VehicleManagerAside(VehicleManagerView vehicleManagerView, CardLayout cardLayout) {
        this.vehicleManagerView = vehicleManagerView;
        this.setLayout(cardLayout);
        this.initGeographicalAnalysisBtnsPanel();
        this.initVehicleAnalysisPanel();
    }

    private void initGeographicalAnalysisBtnsPanel() {
        geographicalAnalysisBtnsPanel = new JPanel();
        geographicalAnalysisBtnsPanel.setBackground(GlobalView.ASIDE_BACKGROUND);
        geographicalAnalysisBtnsPanel.setPreferredSize(new Dimension(225, 0));
        geographicalAnalysisBtnsPanel.setLayout(new GridLayout(0, 1, 0, 39));
        this.add(geographicalAnalysisBtnsPanel, "GeographicalAnalysisBtns");

        this.createRecordsByStateBtn();
        this.createRecordsByCountyBtn();
        this.createRecordsByCityBtn();
    }

    private void initVehicleAnalysisPanel() {
        vehicleAnalysisBtnsPanel = new JPanel();
        vehicleAnalysisBtnsPanel.setBackground(GlobalView.ASIDE_BACKGROUND);
        vehicleAnalysisBtnsPanel.setPreferredSize(new Dimension(225, 0));
        vehicleAnalysisBtnsPanel.setLayout(new GridLayout(0, 1, 0, 39));
        this.add(vehicleAnalysisBtnsPanel, "VehicleAnalysisBtns");

        this.createVehiclesByModelBtn();
        this.createVehiclesByManufacturerBtn();
        this.createVehiclesByElecticRangeBtn();
    }

    private void createRecordsByStateBtn() {
        addButtonToPanel("Registros Por Estado", "RecordsByState", geographicalAnalysisBtnsPanel);
    }

    private void createRecordsByCountyBtn() {
        addButtonToPanel("Registros Por Condado", "RecordsByCounty", geographicalAnalysisBtnsPanel);
    }

    private void createRecordsByCityBtn() {
        addButtonToPanel("Ciudad con Mas Registros", "TopCities", geographicalAnalysisBtnsPanel);
    }

    private void createVehiclesByModelBtn() {
        addButtonToPanel("Vehiculos Por Modelo", "VehiclesByModel", vehicleAnalysisBtnsPanel);
    }

    private void createVehiclesByManufacturerBtn() {
        addButtonToPanel("Vehiculos Por Fabricante", "VehiclesByManufacturer", vehicleAnalysisBtnsPanel);
    }

    private void createVehiclesByElecticRangeBtn() {
        addButtonToPanel("<html><div style='text-align: center;'>Vehiculos Por<br>Rango Electrico</div></html>", "VehiclesByElectricRange", vehicleAnalysisBtnsPanel);
    }

    private void addButtonToPanel(String text, String actionCommand, JPanel panel) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(GlobalView.ASIDE_BACKGROUND);
        button.setForeground(GlobalView.ASIDE_BTN_TEXT_BACKGROUD);
        button.setOpaque(true);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(225, 5));
        button.setMinimumSize(new Dimension(225, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button.setBackground(GlobalView.ASIDE_BTN_BACKGROUD);

                switch (actionCommand) {
                    case "RecordsByState":
                        vehicleManagerView.showRecordsByStatePanel();
                        break;
                    case "RecordsByCounty":
                        vehicleManagerView.showRecordsByCountyPanel();
                        break;
                    case "TopCities":
                        vehicleManagerView.showTopCitiesPanel();
                        break;
                    case "VehiclesByModel":
                        vehicleManagerView.showVehiclesModelsPanel();
                        break;
                    case "VehiclesByManufacturer":
                        vehicleManagerView.showVehiclesManufactureresPanel();
                        break;
                    case "VehiclesByElectricRange":
                        vehicleManagerView.showElectricRangePanel();
                        break;
                }
            }
        });

        panel.add(button);
        panel.revalidate();
        panel.repaint();
    }

    private void resetButtonColors() {
        for (Component component : geographicalAnalysisBtnsPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton btn = (JButton) component;
                btn.setBackground(GlobalView.ASIDE_BACKGROUND);
            }
        }
        for (Component component : vehicleAnalysisBtnsPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton btn = (JButton) component;
                btn.setBackground(GlobalView.ASIDE_BACKGROUND);
            }
        }
    }
}
