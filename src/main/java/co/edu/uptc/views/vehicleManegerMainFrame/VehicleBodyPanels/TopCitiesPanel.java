package co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import co.edu.uptc.pojos.VehiclesByCity;
import co.edu.uptc.utilities.DoubleLinkedList;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;

public class TopCitiesPanel extends JPanel {
    private VehicleManagerView managerView;
    private DefaultTableModel citiesTableModel;
    private JTable citiesTable;

    public TopCitiesPanel(VehicleManagerView managerView) {
        this.managerView = managerView;
        this.setLayout(null);
        this.setBackground(GlobalView.BODY_BACKGROUND);
        this.initComponents();
    }

    private void initComponents() {
        JLabel headerLabel = new JLabel("Ciudades con Mayor Cantidad de Vehículos");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        Dimension headerDimension = headerLabel.getPreferredSize();
        headerLabel.setBounds(150, 30, (int) headerDimension.getWidth(), (int) headerDimension.getHeight());
        this.add(headerLabel);
        this.addCitiesTable();
    }

    private void addCitiesTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, GlobalView.BORDER_COLOR));
        tablePanel.setBounds(15, 80, 630, 300);
        tablePanel.setLayout(null);

        String[] columnNames = { "Ciudad", "Cantidad de Vehículos" };
        citiesTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        citiesTable = new JTable(citiesTableModel);
        citiesTable.setFillsViewportHeight(true);
        citiesTable.getTableHeader().setReorderingAllowed(false);
        citiesTable.getTableHeader().setResizingAllowed(false);
        citiesTable.setDragEnabled(false);

        JScrollPane scrollPane = new JScrollPane(citiesTable);
        scrollPane.setBounds(15, 20, 600, 250);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    public void loadCitiesData() {
        DoubleLinkedList<VehiclesByCity> vehiclesByCity = managerView.getPresenter().getSortByCities();

        String[][] data = new String[vehiclesByCity.size()][2];
        for (int i = 0; i < vehiclesByCity.size(); i++) {
            VehiclesByCity city = vehiclesByCity.get(i);
            data[i][0] = city.getCity();
            data[i][1] = String.valueOf(city.getNumberOfVehicles());
        }

        citiesTableModel.setRowCount(0);
        for (String[] row : data) {
            citiesTableModel.addRow(row);
        }
    }
}
