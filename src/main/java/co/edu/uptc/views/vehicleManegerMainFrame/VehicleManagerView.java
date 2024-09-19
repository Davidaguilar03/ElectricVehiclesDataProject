package co.edu.uptc.views.vehicleManegerMainFrame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import co.edu.uptc.interfaces.VehicleManagerInterface;
import co.edu.uptc.interfaces.VehicleManagerInterface.Presenter;
import co.edu.uptc.utilities.PropertiesService;
import co.edu.uptc.views.vehicleManagerLoadingFrame.VehicleManagerLoadingView;
import lombok.Getter;
import java.awt.BorderLayout;
import java.awt.CardLayout;

@Getter
public class VehicleManagerView extends JFrame implements VehicleManagerInterface.View {
    private VehicleManagerInterface.Presenter presenter;
    private VehicleManagerHeader vehicleManagerHeader;
    private VehicleManagerBody vehicleManagerBody;
    private VehicleManagerAside vehicleManagerAside;
    private VehicleManagerLoadingView vehicleManagerLoadingView;
    private CardLayout bodyCardLayout;
    private CardLayout asideCardLayout;

    private PropertiesService p = new PropertiesService();

    public VehicleManagerView() {
        this.initFrame();
        this.createVehicleManagerHeader();
        this.createVehicleManagerBody();
        this.createVehicleManagerAside();
        this.createVehicleManagerLoadingView();
    }

    private void createVehicleManagerAside() {
        asideCardLayout = new CardLayout();
        vehicleManagerAside = new VehicleManagerAside(this, asideCardLayout);
        this.add(vehicleManagerAside, BorderLayout.WEST);
    }

    @Override
    public void begin() {
        this.presenter.loadData();
        this.vehicleManagerLoadingView.close();
        this.setVisible(true);
        vehicleManagerBody.loadTableData();
    }

    private void initFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("EV DATA ANALYZER");
        this.setLayout(new BorderLayout());
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon(p.getKeyValue("icon"));
        this.setIconImage(icon.getImage());
    }

    private void createVehicleManagerHeader() {
        vehicleManagerHeader = new VehicleManagerHeader(this);
        this.add(vehicleManagerHeader, BorderLayout.NORTH);
    }

    private void createVehicleManagerBody() {
        bodyCardLayout = new CardLayout();
        vehicleManagerBody = new VehicleManagerBody(this, bodyCardLayout);
        this.add(vehicleManagerBody);
    }

    private void createVehicleManagerLoadingView(){
        vehicleManagerLoadingView = new VehicleManagerLoadingView(this,40);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public VehicleManagerInterface.Presenter getPresenter() {
        return this.presenter;
    }

    @Override
    public VehicleManagerLoadingView getVehicleManagerLoadingView(){
        return this.vehicleManagerLoadingView;
    }

    public void showGeographicalAnalysisPanel() {
        bodyCardLayout.show(vehicleManagerBody, "GeographicalAnalysis");
    }

    public void showVehicleAnalysisPanel() {
        bodyCardLayout.show(vehicleManagerBody, "VehicleAnalysis");
    }

    public void showRecordsByStatePanel() {
        bodyCardLayout.show(vehicleManagerBody, "RecordsByState");
    }

    public void showRecordsByCountyPanel() {
        bodyCardLayout.show(vehicleManagerBody, "RecordsByCounty");
    }

    public void showTopCitiesPanel() {
        bodyCardLayout.show(vehicleManagerBody, "TopCities");
    }

    public void showVehiclesModelsPanel() {
        bodyCardLayout.show(vehicleManagerBody, "VehiclesModels");
    }

    public void showVehiclesManufactureresPanel() {
        bodyCardLayout.show(vehicleManagerBody, "VehiclesManufacturers");
    }

    public void showElectricRangePanel() {
        bodyCardLayout.show(vehicleManagerBody, "ElectricRange");
    }

}
