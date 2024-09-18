package co.edu.uptc.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclesByCounty {
    private String state;
    private String county;
    private int numberOfVehicles;
}
