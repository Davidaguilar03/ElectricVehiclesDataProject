package co.edu.uptc.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclesByState {
    private String state;
    private int numberOfVehicles;
}
