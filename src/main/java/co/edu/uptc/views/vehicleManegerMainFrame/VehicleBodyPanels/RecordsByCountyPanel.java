package co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import co.edu.uptc.pojos.VehiclesByCounty;
import co.edu.uptc.utilities.DoubleLinkedList;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;

public class RecordsByCountyPanel extends JPanel {
    private VehicleManagerView managerView;
    private DefaultTableModel countyTableModel;
    private JTable countyTable;

    public RecordsByCountyPanel(VehicleManagerView managerView) {
        this.managerView = managerView;
        this.setLayout(null);
        this.setBackground(GlobalView.BODY_BACKGROUND);
        this.initComponents();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Listado de Registros por Condado");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(180, 20, 300, 30);
        this.add(titleLabel);
        this.addCountyTable();
    }

    private void addCountyTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, GlobalView.BORDER_COLOR));
        tablePanel.setBounds(15, 60, 630, 350);
        tablePanel.setLayout(null);
        String[] columnNames = { "Estado", "Condado", "Cantidad de Registros" };
        countyTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        countyTable = new JTable(countyTableModel);
        countyTable.setFillsViewportHeight(true);
        countyTable.getTableHeader().setReorderingAllowed(false);
        countyTable.getTableHeader().setResizingAllowed(false);
        countyTable.setDragEnabled(false);

        JScrollPane scrollPane = new JScrollPane(countyTable);
        scrollPane.setBounds(15, 20, 600, 300);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    public void loadRecordsByCountyData() {
        DoubleLinkedList<VehiclesByCounty> vehiclesByCounty = managerView.getPresenter().getSortByCounties();

        countyTableModel.setRowCount(0);
        for (VehiclesByCounty vehicle : vehiclesByCounty) {
            countyTableModel.addRow(new Object[] {
                    vehicle.getState(),
                    vehicle.getCounty(),
                    vehicle.getNumberOfVehicles()
            });
        }
    }

}
