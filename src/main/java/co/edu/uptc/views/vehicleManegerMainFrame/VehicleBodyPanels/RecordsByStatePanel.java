package co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import co.edu.uptc.pojos.VehiclesByState;
import co.edu.uptc.utilities.DoubleLinkedList;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;

public class RecordsByStatePanel extends JPanel {
    private VehicleManagerView managerView;
    private DefaultTableModel stateTableModel;
    private JTable stateTable;

    public RecordsByStatePanel(VehicleManagerView managerView) {
        this.managerView = managerView;
        this.setLayout(null);
        this.setBackground(GlobalView.BODY_BACKGROUND);
        this.initComponents();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Cantidad de Registros por Estado");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(180, 20, 300, 30);
        this.add(titleLabel);
        this.addStateTable();
    }

    private void addStateTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, GlobalView.BORDER_COLOR));
        tablePanel.setBounds(15, 60, 630, 350);
        tablePanel.setLayout(null);

        String[] columnNames = { "Estado", "Cantidad de Registros" };

        stateTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        stateTable = new JTable(stateTableModel);
        stateTable.setFillsViewportHeight(true);
        stateTable.getTableHeader().setReorderingAllowed(false);
        stateTable.getTableHeader().setResizingAllowed(false);
        stateTable.setDragEnabled(false);

        JScrollPane scrollPane = new JScrollPane(stateTable);
        scrollPane.setBounds(15, 20, 600, 300);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    public void loadRecordsByStateData() {
        DoubleLinkedList<VehiclesByState> vehiclesByState = managerView.getPresenter().getSortByStateList();

        stateTableModel.setRowCount(0);
        for (VehiclesByState vehicle : vehiclesByState) {
            stateTableModel.addRow(new Object[] {
                    vehicle.getState(),
                    vehicle.getNumberOfVehicles()
            });
        }
    }
}
