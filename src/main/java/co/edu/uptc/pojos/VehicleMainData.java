package co.edu.uptc.pojos;
import lombok.Data;

@Data
public class VehicleMainData {
    private String vinFirstTenCharacters;
    private String registrationCounty;
    private String registrationCity;
    private String registrationState;
    private String registrationPostalCode;
    private String vehicleModelYear;
    private String vehicleManufacturer;
    private String vehicleModel;
    private String electricVehicleType;
    private String cleanAlternativeFuelEligibility;
    private Integer electricDrivingRange;
    private Integer baseManufacturerSuggestedRetailPrice;
    private String legislativeDistrictCode;
    private String departmentOfLicensingVehicleId;
    private String vehicleGeocodedLocation;
    private String electricUtilityProvider;
    private String censusTract2020;
}

