package co.edu.uptc.presenters;

import co.edu.uptc.interfaces.VehicleManagerInterface;
import co.edu.uptc.pojos.VehiclesByCity;
import co.edu.uptc.pojos.VehiclesByCounty;
import co.edu.uptc.pojos.VehiclesByElectricRange;
import co.edu.uptc.pojos.VehiclesByManufacturer;
import co.edu.uptc.pojos.VehiclesByModel;
import co.edu.uptc.pojos.VehiclesByState;
import co.edu.uptc.utilities.DoubleLinkedList;
import lombok.Getter;

@Getter
public class Presenter implements VehicleManagerInterface.Presenter {
    private VehicleManagerInterface.View view;
    private VehicleManagerInterface.Model model;

    @Override
    public void setView(VehicleManagerInterface.View view) {
        this.view = view;
    }

    @Override
    public void setModel(VehicleManagerInterface.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        model.loadData();
    }

    @Override
    public DoubleLinkedList<VehiclesByState> getSortByStateList() {
        return model.getSortByStateList();
    }

    @Override
    public DoubleLinkedList<VehiclesByCounty> getSortByCounties() {
        return model.getSortByCounties();
    }

    @Override
    public DoubleLinkedList<VehiclesByCity> getSortByCities() {
        return model.getSortByCities();
    }

    @Override
    public DoubleLinkedList<VehiclesByModel> getSortByModel() {
        return model.getSortByModel();
    }

    @Override
    public DoubleLinkedList<VehiclesByManufacturer> getSortByManufacturer() {
        return model.getSortByManufacturer();
    }

    @Override
    public DoubleLinkedList<VehiclesByElectricRange> getSortByElectricRanges() {
        return model.getSortByElectricRanges();
    }

}
