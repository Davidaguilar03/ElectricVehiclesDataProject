package co.edu.uptc.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclesByModel {
    private String vehicleModel;
    private int numberOfVehicles;
}
