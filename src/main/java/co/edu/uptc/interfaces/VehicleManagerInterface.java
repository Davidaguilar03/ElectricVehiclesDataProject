package co.edu.uptc.interfaces;

import co.edu.uptc.pojos.VehiclesByCity;
import co.edu.uptc.pojos.VehiclesByCounty;
import co.edu.uptc.pojos.VehiclesByElectricRange;
import co.edu.uptc.pojos.VehiclesByManufacturer;
import co.edu.uptc.pojos.VehiclesByModel;
import co.edu.uptc.pojos.VehiclesByState;
import co.edu.uptc.utilities.DoubleLinkedList;

public interface VehicleManagerInterface {

    public interface Model {
        public void setPresenter(Presenter presenter);

        public void loadData();

        public DoubleLinkedList<VehiclesByState> getSortByStateList();

        public DoubleLinkedList<VehiclesByCounty> getSortByCounties();

        public DoubleLinkedList<VehiclesByCity> getSortByCities();

        public DoubleLinkedList<VehiclesByModel> getSortByModel();

        public DoubleLinkedList<VehiclesByManufacturer> getSortByManufacturer();

        public DoubleLinkedList<VehiclesByElectricRange> getSortByElectricRanges();

    }

    public interface View {
        public void setPresenter(Presenter presenter);

        public void begin();

    }

    public interface Presenter {
        public void setView(View view);

        public void setModel(Model model);

        public void loadData();

        public DoubleLinkedList<VehiclesByState> getSortByStateList();

        public DoubleLinkedList<VehiclesByCounty> getSortByCounties();

        public DoubleLinkedList<VehiclesByCity> getSortByCities();

        public DoubleLinkedList<VehiclesByModel> getSortByModel();

        public DoubleLinkedList<VehiclesByManufacturer> getSortByManufacturer();

        public DoubleLinkedList<VehiclesByElectricRange> getSortByElectricRanges();

    }
}
