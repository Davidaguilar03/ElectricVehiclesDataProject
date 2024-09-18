package co.edu.uptc.models;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uptc.interfaces.VehicleManagerInterface;
import co.edu.uptc.pojos.VehicleMainData;
import co.edu.uptc.pojos.VehiclesByCity;
import co.edu.uptc.pojos.VehiclesByCounty;
import co.edu.uptc.pojos.VehiclesByElectricRange;
import co.edu.uptc.pojos.VehiclesByManufacturer;
import co.edu.uptc.pojos.VehiclesByModel;
import co.edu.uptc.pojos.VehiclesByState;
import co.edu.uptc.pojos.VehicleAllData;
import co.edu.uptc.utilities.ApiConsumer;
import co.edu.uptc.utilities.DoubleLinkedList;
import co.edu.uptc.utilities.PropertiesService;
import lombok.Data;

@Data
public class VehicleManagerModel implements VehicleManagerInterface.Model {
    private VehicleManagerInterface.Presenter presenter;
    private DoubleLinkedList<VehicleMainData> vehiclesData;
    private DoubleLinkedList<VehiclesByState> vehiclesByStates;
    private DoubleLinkedList<VehiclesByCounty> vehiclesByCounties;
    private DoubleLinkedList<VehiclesByCity> vehiclesByCities;
    private DoubleLinkedList<VehiclesByModel> vehiclesByModel;
    private DoubleLinkedList<VehiclesByManufacturer> vehiclesByManufacturer;
    private DoubleLinkedList<VehiclesByElectricRange> vehiclesByElectricRange;

    public VehicleManagerModel() {
        vehiclesData = new DoubleLinkedList<VehicleMainData>();
        vehiclesByStates = new DoubleLinkedList<>();
        vehiclesByCounties = new DoubleLinkedList<>();
        vehiclesByCities = new DoubleLinkedList<>();
        vehiclesByModel = new DoubleLinkedList<>();
        vehiclesByManufacturer = new DoubleLinkedList<>();
        vehiclesByElectricRange = new DoubleLinkedList<>();
    }

    @Override
    public void setPresenter(VehicleManagerInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadData() {
        try {
            String jsonData = consumeApiToString();
            List<VehicleAllData> vehiclesAllData = parseJsonDataToVehicleRegistration(jsonData);
            List<VehicleMainData> vehiclesMainData = vehicleRegistrationToVehicleInfo(vehiclesAllData);
            for (VehicleMainData vehicleInfo : vehiclesMainData) {
                this.vehiclesData.add(vehicleInfo);
            }
            this.searchVehiclesByStates();
            this.searchVehiclesByCounties();
            this.searchVehiclesByCities();
            this.searchVehiclesByModel();
            this.searchVehiclesByManufacturer();
            this.searchVehiclesByElectricRange();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private String consumeApiToString() throws IOException, URISyntaxException {
        ApiConsumer apiConsumer = new ApiConsumer();
        PropertiesService propertiesService = new PropertiesService();
        apiConsumer.consumeApi();
        Path dataPath = Paths.get(propertiesService.getKeyValue("DataPath"));
        return new String(Files.readAllBytes(dataPath));
    }

    private List<VehicleAllData> parseJsonDataToVehicleRegistration(String jsonData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {
        });
        @SuppressWarnings("unchecked")
        List<List<Object>> dataList = (List<List<Object>>) map.get("data");

        List<VehicleAllData> vehiclesAllData = new ArrayList<>();
        for (List<Object> dataObject : dataList) {
            VehicleAllData vehicle = mapRowToVehicleRegistration(dataObject);
            vehiclesAllData.add(vehicle);
        }
        return vehiclesAllData;
    }

    private List<VehicleMainData> vehicleRegistrationToVehicleInfo(List<VehicleAllData> vehiclesAllData) {
        List<VehicleMainData> vehiclesMainData = new ArrayList<>();
        for (VehicleAllData vehicle : vehiclesAllData) {
            VehicleMainData vehicleInfo = new VehicleMainData();
            vehicleInfo.setVinFirstTenCharacters(vehicle.getVinNumber());
            vehicleInfo.setRegistrationCounty(vehicle.getCounty());
            vehicleInfo.setRegistrationCity(vehicle.getCity());
            vehicleInfo.setRegistrationState(vehicle.getState());
            vehicleInfo.setRegistrationPostalCode(vehicle.getPostalCode());
            vehicleInfo.setVehicleModelYear(vehicle.getModelYear());
            vehicleInfo.setVehicleManufacturer(vehicle.getManufacturer());
            vehicleInfo.setVehicleModel(vehicle.getModel());
            vehicleInfo.setElectricVehicleType(vehicle.getElectricVehicleType());
            vehicleInfo.setCleanAlternativeFuelEligibility(vehicle.getCleanFuelVehicleEligibility());
            vehicleInfo.setElectricDrivingRange(vehicle.getElectricRange());
            vehicleInfo.setBaseManufacturerSuggestedRetailPrice(vehicle.getBaseMsrp());
            vehicleInfo.setLegislativeDistrictCode(vehicle.getLegislativeDistrict());
            vehicleInfo.setDepartmentOfLicensingVehicleId(vehicle.getDolVehicleId());
            vehicleInfo.setVehicleGeocodedLocation(vehicle.getGeocodedLocation());
            vehicleInfo.setElectricUtilityProvider(vehicle.getElectricUtility());
            vehicleInfo.setCensusTract2020(vehicle.getCensus2020Tract());
            vehiclesMainData.add(vehicleInfo);
        }
        return vehiclesMainData;
    }

    private VehicleAllData mapRowToVehicleRegistration(List<Object> row) {
        VehicleAllData auxVehicleAllData = new VehicleAllData();
        auxVehicleAllData.setIdentifierSID((String) row.get(0));
        auxVehicleAllData.setIdentifierID((String) row.get(1));
        auxVehicleAllData.setPosition((Integer) row.get(2));
        auxVehicleAllData.setCreatedAtEpoch(convertEpochToLong(row.get(3)));
        auxVehicleAllData.setCreatedMeta((String) row.get(4));
        auxVehicleAllData.setUpdatedAtEpoch(convertEpochToLong(row.get(5)));
        auxVehicleAllData.setUpdatedMeta((String) row.get(6));
        auxVehicleAllData.setMeta((String) row.get(7));
        auxVehicleAllData.setVinNumber((String) row.get(8));
        auxVehicleAllData.setCounty((String) row.get(9));
        auxVehicleAllData.setCity((String) row.get(10));
        auxVehicleAllData.setState((String) row.get(11));
        auxVehicleAllData.setPostalCode((String) row.get(12));
        auxVehicleAllData.setModelYear((String) row.get(13));
        auxVehicleAllData.setManufacturer((String) row.get(14));
        auxVehicleAllData.setModel((String) row.get(15));
        auxVehicleAllData.setElectricVehicleType((String) row.get(16));
        auxVehicleAllData.setCleanFuelVehicleEligibility((String) row.get(17));
        auxVehicleAllData.setElectricRange(parseInteger(row.get(18)));
        auxVehicleAllData.setBaseMsrp(parseInteger(row.get(19)));
        auxVehicleAllData.setLegislativeDistrict((String) row.get(20));
        auxVehicleAllData.setDolVehicleId((String) row.get(21));
        auxVehicleAllData.setGeocodedLocation((String) row.get(22));
        auxVehicleAllData.setElectricUtility((String) row.get(23));
        auxVehicleAllData.setCensus2020Tract((String) row.get(24));
        auxVehicleAllData.setRegionsCounted(parseInteger(row.get(25)));
        auxVehicleAllData.setCongressionalDistrict(parseInteger(row.get(26)));
        auxVehicleAllData.setLegislativeDistrictBoundaryGIS(parseInteger(row.get(27)));
        return auxVehicleAllData;
    }

    private Long convertEpochToLong(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).longValue();
        } else if (value instanceof Long) {
            return (Long) value;
        }
        return null;
    }

    private Integer parseInteger(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
                return Integer.valueOf((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private DoubleLinkedList<VehiclesByState> getVehiclesByStates() {
        DoubleLinkedList<VehiclesByState> vehiclesByStates = new DoubleLinkedList<>();
        for (int i = 0; i < vehiclesData.size(); i++) {
            VehiclesByState vehiclesByState = new VehiclesByState(vehiclesData.get(i).getRegistrationState(), 0);
            if (!vehiclesByStates.contains(vehiclesByState)) {
                vehiclesByStates.add(vehiclesByState);
            }
        }
        return vehiclesByStates;
    }

    public void searchVehiclesByStates() {
        DoubleLinkedList<VehiclesByState> vehiclesByStates = this.getVehiclesByStates();
        for (int i = 0; i < vehiclesData.size(); i++) {
            String currentState = vehiclesData.get(i).getRegistrationState();
            for (int j = 0; j < vehiclesByStates.size(); j++) {
                if (currentState.equals(vehiclesByStates.get(j).getState())) {
                    int currentNumberOfVehicles = vehiclesByStates.get(j).getNumberOfVehicles();
                    vehiclesByStates.get(j).setNumberOfVehicles(currentNumberOfVehicles + 1);
                    break;
                }
            }
        }
        this.vehiclesByStates = this.sortVehiclesByStateDesc(vehiclesByStates);
    }

    private DoubleLinkedList<VehiclesByState> sortVehiclesByStateDesc(
            DoubleLinkedList<VehiclesByState> vehiclesByStates) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < vehiclesByStates.size() - 1; i++) {
                VehiclesByState current = vehiclesByStates.get(i);
                VehiclesByState next = vehiclesByStates.get(i + 1);
                if (current.getNumberOfVehicles() < next.getNumberOfVehicles()) {
                    vehiclesByStates.set(i, next);
                    vehiclesByStates.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return vehiclesByStates;
    }

    private DoubleLinkedList<VehiclesByCounty> getVehiclesByCounties() {
        DoubleLinkedList<VehiclesByCounty> vehiclesByCounties = new DoubleLinkedList<>();
        for (int i = 0; i < vehiclesData.size(); i++) {
            VehiclesByCounty auxVehiclesByCounty = new VehiclesByCounty(vehiclesData.get(i).getRegistrationState(),
                    vehiclesData.get(i).getRegistrationCounty(), 0);
            if (auxVehiclesByCounty.getCounty() != null && !vehiclesByCounties.contains(auxVehiclesByCounty)) {
                vehiclesByCounties.add(auxVehiclesByCounty);
            }
        }
        return this.sortVehiclesByCounties(vehiclesByCounties);
    }

    public void searchVehiclesByCounties() {
        DoubleLinkedList<VehiclesByCounty> vehiclesByCounties = this.getVehiclesByCounties();
        for (int i = 0; i < vehiclesData.size(); i++) {
            String currentCounty = vehiclesData.get(i).getRegistrationCounty();
            if (currentCounty != null) {
                for (int j = 0; j < vehiclesByCounties.size(); j++) {
                    if (currentCounty.equals(vehiclesByCounties.get(j).getCounty())) {
                        int currentNumberOfVehicles = vehiclesByCounties.get(j).getNumberOfVehicles();
                        vehiclesByCounties.get(j).setNumberOfVehicles(currentNumberOfVehicles + 1);
                        break;
                    }
                }
            }
        }
        this.vehiclesByCounties = this.sortVehiclesByCounties(vehiclesByCounties);
    }

    private DoubleLinkedList<VehiclesByCounty> sortVehiclesByCounties(
            DoubleLinkedList<VehiclesByCounty> vehiclesByCounties) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < vehiclesByCounties.size() - 1; i++) {
                VehiclesByCounty current = vehiclesByCounties.get(i);
                VehiclesByCounty next = vehiclesByCounties.get(i + 1);

                if (compare(current, next) > 0) {
                    vehiclesByCounties.set(i, next);
                    vehiclesByCounties.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return vehiclesByCounties;
    }

    private int compare(VehiclesByCounty a, VehiclesByCounty b) {
        int stateCompare = a.getState().compareTo(b.getState());
        if (stateCompare != 0) {
            return stateCompare;
        }
        return Integer.compare(a.getNumberOfVehicles(), b.getNumberOfVehicles());
    }

    private DoubleLinkedList<VehiclesByCity> getVehiclesByCities() {
        DoubleLinkedList<VehiclesByCity> vehiclesByCities = new DoubleLinkedList<>();
        for (int i = 0; i < vehiclesData.size(); i++) {
            VehiclesByCity auxVehiclesByCity = new VehiclesByCity(vehiclesData.get(i).getRegistrationCity(), 0);
            if (auxVehiclesByCity.getCity() != null && !vehiclesByCities.contains(auxVehiclesByCity)) {
                vehiclesByCities.add(auxVehiclesByCity);
            }
        }
        return vehiclesByCities;
    }

    public void searchVehiclesByCities() {
        DoubleLinkedList<VehiclesByCity> vehiclesByCities = this.getVehiclesByCities();
        for (int i = 0; i < vehiclesData.size(); i++) {
            String currentCity = vehiclesData.get(i).getRegistrationCity();
            if (currentCity != null) {
                for (int j = 0; j < vehiclesByCities.size(); j++) {
                    if (currentCity.equals(vehiclesByCities.get(j).getCity())) {
                        int currentNumberOfVehicles = vehiclesByCities.get(j).getNumberOfVehicles();
                        vehiclesByCities.get(j).setNumberOfVehicles(currentNumberOfVehicles + 1);
                        break;
                    }
                }
            }
        }
        this.vehiclesByCities = this.sortVehiclesByCities(vehiclesByCities);
    }

    private DoubleLinkedList<VehiclesByCity> sortVehiclesByCities(DoubleLinkedList<VehiclesByCity> vehiclesByCities) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < vehiclesByCities.size() - 1; i++) {
                VehiclesByCity current = vehiclesByCities.get(i);
                VehiclesByCity next = vehiclesByCities.get(i + 1);
                if (current.getNumberOfVehicles() < next.getNumberOfVehicles()) {
                    vehiclesByCities.set(i, next);
                    vehiclesByCities.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return vehiclesByCities;
    }

    private void searchVehiclesByModel() {
        DoubleLinkedList<VehiclesByModel> vehiclesByModels = this.getVehiclesByModel();
        for (int i = 0; i < vehiclesData.size(); i++) {
            String currentModel = vehiclesData.get(i).getVehicleModel();
            if (currentModel != null) {
                boolean modelFound = false;
                for (int j = 0; j < vehiclesByModels.size(); j++) {
                    if (currentModel.equals(vehiclesByModels.get(j).getVehicleModel())) {
                        int currentNumberOfVehicles = vehiclesByModels.get(j).getNumberOfVehicles();
                        vehiclesByModels.get(j).setNumberOfVehicles(currentNumberOfVehicles + 1);
                        modelFound = true;
                        break;
                    }
                }
                if (!modelFound) {
                    vehiclesByModels.add(new VehiclesByModel(currentModel, 1));
                }
            }
        }
        this.vehiclesByModel = this.sortVehiclesByModels(vehiclesByModels);
    }

    private DoubleLinkedList<VehiclesByModel> sortVehiclesByModels(DoubleLinkedList<VehiclesByModel> vehiclesByModels) {
        if (vehiclesByModels.size() <= 1) {
            return vehiclesByModels;
        }
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < vehiclesByModels.size() - 1; i++) {
                VehiclesByModel current = vehiclesByModels.get(i);
                VehiclesByModel next = vehiclesByModels.get(i + 1);
                if (current.getNumberOfVehicles() < next.getNumberOfVehicles()) {
                    vehiclesByModels.set(i, next);
                    vehiclesByModels.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return vehiclesByModels;
    }

    public void searchVehiclesByManufacturer() {
        DoubleLinkedList<VehiclesByManufacturer> vehiclesByManufacturers = this.getVehiclesByManufacturer();
        for (int i = 0; i < vehiclesData.size(); i++) {
            String currentManufacturer = vehiclesData.get(i).getVehicleManufacturer();
            if (currentManufacturer != null) {
                boolean manufacturerFound = false;
                for (int j = 0; j < vehiclesByManufacturers.size(); j++) {
                    if (currentManufacturer.equals(vehiclesByManufacturers.get(j).getManufacturer())) {
                        int currentNumberOfVehicles = vehiclesByManufacturers.get(j).getNumberOfVehicles();
                        vehiclesByManufacturers.get(j).setNumberOfVehicles(currentNumberOfVehicles + 1);
                        manufacturerFound = true;
                        break;
                    }
                }
                if (!manufacturerFound) {
                    vehiclesByManufacturers.add(new VehiclesByManufacturer(currentManufacturer, 1));
                }
            }
        }
        this.vehiclesByManufacturer = this.sortVehiclesByManufacturers(vehiclesByManufacturers);
    }

    private DoubleLinkedList<VehiclesByManufacturer> sortVehiclesByManufacturers(
            DoubleLinkedList<VehiclesByManufacturer> vehiclesByManufacturers) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < vehiclesByManufacturers.size() - 1; i++) {
                VehiclesByManufacturer current = vehiclesByManufacturers.get(i);
                VehiclesByManufacturer next = vehiclesByManufacturers.get(i + 1);
                if (current.getNumberOfVehicles() < next.getNumberOfVehicles()) {
                    vehiclesByManufacturers.set(i, next);
                    vehiclesByManufacturers.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return vehiclesByManufacturers;
    }

    private DoubleLinkedList<VehiclesByElectricRange> sortVehiclesByElectricRange(
            DoubleLinkedList<VehiclesByElectricRange> vehiclesByElectricRange) {
        if (vehiclesByElectricRange.size() <= 1) {
            return vehiclesByElectricRange;
        }
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < vehiclesByElectricRange.size() - 1; i++) {
                VehiclesByElectricRange current = vehiclesByElectricRange.get(i);
                VehiclesByElectricRange next = vehiclesByElectricRange.get(i + 1);
                if (Integer.parseInt(current.getElectricRange()) > Integer.parseInt(next.getElectricRange())) {
                    vehiclesByElectricRange.set(i, next);
                    vehiclesByElectricRange.set(i + 1, current);
                    swapped = true;
                }
            }
        } while (swapped);
        return vehiclesByElectricRange;
    }

    public void searchVehiclesByElectricRange() {
        DoubleLinkedList<VehiclesByElectricRange> vehiclesByElectricRange = this.getVehiclesByElectricRange();
        for (int i = 0; i < vehiclesData.size(); i++) {
            Integer currentElectricRange = vehiclesData.get(i).getElectricDrivingRange();
            if (currentElectricRange != null) {
                String currentElectricRangeStr = currentElectricRange.toString();
                boolean electricRangeFound = false;
                for (int j = 0; j < vehiclesByElectricRange.size(); j++) {
                    if (currentElectricRangeStr.equals(vehiclesByElectricRange.get(j).getElectricRange())) {
                        int currentNumberOfVehicles = vehiclesByElectricRange.get(j).getNumberOfVehicles();
                        vehiclesByElectricRange.get(j).setNumberOfVehicles(currentNumberOfVehicles + 1);
                        electricRangeFound = true;
                        break;
                    }
                }
                if (!electricRangeFound) {
                    vehiclesByElectricRange.add(new VehiclesByElectricRange(currentElectricRangeStr, 1));
                }
            }
        }
        this.vehiclesByElectricRange = this.sortVehiclesByElectricRange(vehiclesByElectricRange);
    }

    @Override
    public DoubleLinkedList<VehiclesByState> getSortByStateList() {
        return vehiclesByStates;
    }

    @Override
    public DoubleLinkedList<VehiclesByCounty> getSortByCounties() {
        return vehiclesByCounties;
    }

    @Override
    public DoubleLinkedList<VehiclesByCity> getSortByCities() {
        return vehiclesByCities;
    }

    @Override
    public DoubleLinkedList<VehiclesByModel> getSortByModel() {
        return vehiclesByModel;
    }

    @Override
    public DoubleLinkedList<VehiclesByManufacturer> getSortByManufacturer() {
        return vehiclesByManufacturer;
    }

    @Override
    public DoubleLinkedList<VehiclesByElectricRange> getSortByElectricRanges() {
        return vehiclesByElectricRange;
    }
}
