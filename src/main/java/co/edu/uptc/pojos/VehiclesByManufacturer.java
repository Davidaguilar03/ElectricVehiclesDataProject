package co.edu.uptc.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclesByManufacturer {
    private String manufacturer;
    private int numberOfVehicles;
}
