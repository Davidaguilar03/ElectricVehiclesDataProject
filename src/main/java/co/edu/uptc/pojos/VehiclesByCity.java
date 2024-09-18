package co.edu.uptc.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclesByCity {
    private String city;
    private int numberOfVehicles;
}
