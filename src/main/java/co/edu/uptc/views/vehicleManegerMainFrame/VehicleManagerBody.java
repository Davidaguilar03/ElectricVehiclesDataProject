package co.edu.uptc.views.vehicleManegerMainFrame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.edu.uptc.utilities.PropertiesService;
import co.edu.uptc.views.GlobalView;
import lombok.Getter;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;

import co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels.RecordsByCountyPanel;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels.RecordsByStatePanel;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels.TopCitiesPanel;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels.VehiclesManufacturersPanel;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels.VehiclesModelsPanel;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels.VehiclesRangePanel;

@Getter
public class VehicleManagerBody extends JPanel {

    private VehicleManagerView vehicleManagerView;
    private JPanel geographicalAnalysisPanel;
    private JPanel vehicleAnalysisPanel;

    private RecordsByStatePanel recordsByStatePanel;
    private RecordsByCountyPanel recordsByCountyPanel;
    private TopCitiesPanel topCitiesPanel;
    private VehiclesModelsPanel vehiclesModelsPanel;
    private VehiclesManufacturersPanel vehiclesManufacturersPanel;
    private VehiclesRangePanel vehiclesRangePanel;

    private DefaultTableModel vehiclesTableModel;
    private JTable vehiclesTable;
    private DefaultTableModel geograficalTableModel;
    private JTable geograficalTable;

    private PropertiesService p = new PropertiesService();

    public VehicleManagerBody(VehicleManagerView vehicleManagerView, CardLayout cardLayout) {
        this.vehicleManagerView = vehicleManagerView;
        this.setLayout(cardLayout);
        this.initGeographicalAnalysisPanel();
        this.initVehicleAnalysisPanel();
        this.initRecordsByStatePanel();
        this.initRecordsByCountyPanel();
        this.initTopCitiesPanel();
        this.initVehiclesModelsPanel();
        this.initVehiclesManufacturerPanel();
        this.initElectricRangePanel();
    }

    private void initGeographicalAnalysisPanel() {
        geographicalAnalysisPanel = new JPanel();
        geographicalAnalysisPanel.setBackground(GlobalView.BODY_BACKGROUND);
        geographicalAnalysisPanel.setLayout(null);
        this.add(geographicalAnalysisPanel, "GeographicalAnalysis");

        JLabel titleLabel = new JLabel("Análisis Geográfico");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(270, 20, 200, 30);
        geographicalAnalysisPanel.add(titleLabel);
        ImageIcon icon = new ImageIcon(
                new ImageIcon(p.getKeyValue("geoBg")).getImage().getScaledInstance(700, 300, Image.SCALE_SMOOTH));

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(0, 60, icon.getIconWidth(), icon.getIconHeight());
        geographicalAnalysisPanel.add(imageLabel);
    }

    private void initVehicleAnalysisPanel() {
        vehicleAnalysisPanel = new JPanel();
        vehicleAnalysisPanel.setBackground(GlobalView.BODY_BACKGROUND);
        vehicleAnalysisPanel.setLayout(null);
        this.add(vehicleAnalysisPanel, "VehicleAnalysis");

        JLabel titleLabel = new JLabel("Análisis por Vehículo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(270, 20, 200, 30);
        vehicleAnalysisPanel.add(titleLabel);

        ImageIcon icon = new ImageIcon(
                new ImageIcon(p.getKeyValue("carBg")).getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH));

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(165, 0, icon.getIconWidth(), icon.getIconHeight());
        vehicleAnalysisPanel.add(imageLabel);
    }

    private void initRecordsByStatePanel() {
        recordsByStatePanel = new RecordsByStatePanel(this.vehicleManagerView);
        this.add(recordsByStatePanel, "RecordsByState");
    }

    private void initRecordsByCountyPanel() {
        recordsByCountyPanel = new RecordsByCountyPanel(this.vehicleManagerView);
        this.add(recordsByCountyPanel, "RecordsByCounty");
    }

    private void initTopCitiesPanel() {
        topCitiesPanel = new TopCitiesPanel(this.vehicleManagerView);
        this.add(topCitiesPanel, "TopCities");
    }

    private void initVehiclesModelsPanel() {
        vehiclesModelsPanel = new VehiclesModelsPanel(this.vehicleManagerView);
        this.add(vehiclesModelsPanel, "VehiclesModels");
    }

    private void initVehiclesManufacturerPanel() {
        vehiclesManufacturersPanel = new VehiclesManufacturersPanel(this.vehicleManagerView);
        this.add(vehiclesManufacturersPanel, "VehiclesManufacturers");
    }

    private void initElectricRangePanel() {
        vehiclesRangePanel = new VehiclesRangePanel(this.vehicleManagerView);
        this.add(vehiclesRangePanel, "ElectricRange");
    }

    public void loadTableData() {
        recordsByStatePanel.loadRecordsByStateData();
        recordsByCountyPanel.loadRecordsByCountyData();
        topCitiesPanel.loadCitiesData();
        vehiclesManufacturersPanel.loadVehiclesManufacturersData();
        vehiclesModelsPanel.loadVehiclesByModelData();
        vehiclesRangePanel.loadRangeData();
    }
}
