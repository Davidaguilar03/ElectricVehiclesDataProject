package co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.pojos.VehiclesByModel;
import co.edu.uptc.utilities.DoubleLinkedList;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;

public class VehiclesModelsPanel extends JPanel {

    private VehicleManagerView managerView;
    private DefaultTableModel modelsTableModel;
    private JTable modelsTable;

    public VehiclesModelsPanel(VehicleManagerView managerView) {
        this.managerView = managerView;
        this.setLayout(null);
        this.setBackground(GlobalView.BODY_BACKGROUND);
        this.initComponents();
    }

    private void initComponents() {
        JLabel headerLabel = new JLabel("Cantidad de Vehículos por Modelo");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        Dimension headerDimension = headerLabel.getPreferredSize();
        headerLabel.setBounds(180, 30, (int) headerDimension.getWidth(), (int) headerDimension.getHeight());
        this.add(headerLabel);
        this.addModelsTable();
    }

    private void addModelsTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, GlobalView.BORDER_COLOR));
        tablePanel.setBounds(15, 80, 630, 300);
        tablePanel.setLayout(null);

        String[] columnNames = { "Modelo de Vehículo", "Cantidad" };
        modelsTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelsTable = new JTable(modelsTableModel);
        modelsTable.setFillsViewportHeight(true);
        modelsTable.getTableHeader().setReorderingAllowed(false);
        modelsTable.getTableHeader().setResizingAllowed(false);
        modelsTable.setDragEnabled(false);

        JScrollPane scrollPane = new JScrollPane(modelsTable);
        scrollPane.setBounds(15, 20, 600, 250);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    private void updateModelsTable(DoubleLinkedList<VehiclesByModel> vehiclesByModels) {
        String[][] data = new String[vehiclesByModels.size()][2];
        for (int i = 0; i < vehiclesByModels.size(); i++) {
            VehiclesByModel model = vehiclesByModels.get(i);
            data[i][0] = model.getVehicleModel();
            data[i][1] = String.valueOf(model.getNumberOfVehicles());
        }
        modelsTableModel.setRowCount(0);
        for (String[] row : data) {
            modelsTableModel.addRow(row);
        }
    }

    public void loadVehiclesByModelData() {
        DoubleLinkedList<VehiclesByModel> vehiclesByModels = managerView.getPresenter().getSortByModel();
        updateModelsTable(vehiclesByModels);
    }
}
