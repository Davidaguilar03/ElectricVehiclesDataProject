package co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;
import co.edu.uptc.pojos.VehiclesByManufacturer;
import co.edu.uptc.utilities.DoubleLinkedList;

public class VehiclesManufacturersPanel extends JPanel {
    private VehicleManagerView managerView;
    private DefaultTableModel manufacturersTableModel;
    private JTable manufacturersTable;

    public VehiclesManufacturersPanel(VehicleManagerView managerView) {
        this.managerView = managerView;
        this.setLayout(null);
        this.setBackground(GlobalView.BODY_BACKGROUND);
        this.initComponents();
    }

    private void initComponents() {
        JLabel headerLabel = new JLabel("Cantidad de Vehículos por Fabricante");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        Dimension headerDimension = headerLabel.getPreferredSize();
        headerLabel.setBounds(180, 30, (int) headerDimension.getWidth(), (int) headerDimension.getHeight());
        this.add(headerLabel);
        this.addManufacturersTable();
    }

    private void addManufacturersTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, GlobalView.BORDER_COLOR));
        tablePanel.setBounds(15, 80, 630, 300);
        tablePanel.setLayout(null);

        String[] columnNames = { "Fabricante", "Cantidad" };
        manufacturersTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        manufacturersTable = new JTable(manufacturersTableModel);
        manufacturersTable.setFillsViewportHeight(true);
        manufacturersTable.getTableHeader().setReorderingAllowed(false);
        manufacturersTable.getTableHeader().setResizingAllowed(false);
        manufacturersTable.setDragEnabled(false);

        JScrollPane scrollPane = new JScrollPane(manufacturersTable);
        scrollPane.setBounds(15, 20, 600, 250);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    public void loadVehiclesManufacturersData() {
        DoubleLinkedList<VehiclesByManufacturer> manufacturersList = managerView.getPresenter().getSortByManufacturer();
        updateManufacturersTable(manufacturersList);
    }

    private void updateManufacturersTable(DoubleLinkedList<VehiclesByManufacturer> manufacturersList) {
        manufacturersTableModel.setRowCount(0);
        for (VehiclesByManufacturer manufacturer : manufacturersList) {
            String manufacturerName = manufacturer.getManufacturer();
            int numberOfVehicles = manufacturer.getNumberOfVehicles();
            manufacturersTableModel.addRow(new Object[] { manufacturerName, numberOfVehicles });
        }
    }
}