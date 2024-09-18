package co.edu.uptc.views.vehicleManegerMainFrame.VehicleBodyPanels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.edu.uptc.pojos.VehiclesByElectricRange;
import co.edu.uptc.utilities.DoubleLinkedList;
import co.edu.uptc.views.GlobalView;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;

public class VehiclesRangePanel extends JPanel {

    private VehicleManagerView managerView;
    private DefaultTableModel rangeTableModel;
    private JTable rangeTable;

    public VehiclesRangePanel(VehicleManagerView managerView) {
        this.managerView = managerView;
        this.setLayout(null);
        this.setBackground(GlobalView.BODY_BACKGROUND);
        this.initComponents();
    }

    private void initComponents() {
        JLabel headerLabel = new JLabel("Cantidad de Vehículos por Rango Eléctrico");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        Dimension headerDimension = headerLabel.getPreferredSize();
        headerLabel.setBounds(180, 30, (int) headerDimension.getWidth(), (int) headerDimension.getHeight());
        this.add(headerLabel);
        this.addRangeTable();
    }

    private void addRangeTable() {
        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, GlobalView.BORDER_COLOR));
        tablePanel.setBounds(15, 80, 630, 300);
        tablePanel.setLayout(null);

        String[] columnNames = { "Rango Eléctrico", "Cantidad" };
        rangeTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        rangeTable = new JTable(rangeTableModel);
        rangeTable.setFillsViewportHeight(true);
        rangeTable.getTableHeader().setReorderingAllowed(false);
        rangeTable.getTableHeader().setResizingAllowed(false);
        rangeTable.setDragEnabled(false);

        JScrollPane scrollPane = new JScrollPane(rangeTable);
        scrollPane.setBounds(15, 20, 600, 250);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    public void loadRangeData() {
        DoubleLinkedList<VehiclesByElectricRange> rangeData = managerView.getPresenter().getSortByElectricRanges();
        updateRangeTable(rangeData);
    }

    private void updateRangeTable(DoubleLinkedList<VehiclesByElectricRange> rangeData) {
        rangeTableModel.setRowCount(0);
        for (VehiclesByElectricRange range : rangeData) {
            String rangeLabel = range.getElectricRange();
            int numberOfVehicles = range.getNumberOfVehicles();
            rangeTableModel.addRow(new Object[] { rangeLabel, numberOfVehicles });
        }
    }
}
