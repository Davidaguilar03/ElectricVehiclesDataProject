package co.edu.uptc;

import java.net.URISyntaxException;

import co.edu.uptc.interfaces.VehicleManagerInterface;
import co.edu.uptc.models.VehicleManagerModel;
import co.edu.uptc.presenters.Presenter;
import co.edu.uptc.views.vehicleManegerMainFrame.VehicleManagerView;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        VehicleManagerInterface.Model model = new VehicleManagerModel();
        VehicleManagerInterface.Presenter presenter = new Presenter();
        VehicleManagerInterface.View view = new VehicleManagerView();
        model.setPresenter(presenter);
        view.setPresenter(presenter);
        presenter.setModel(model);
        presenter.setView(view);
        view.getVehicleManagerLoadingView().begin();
    }
}