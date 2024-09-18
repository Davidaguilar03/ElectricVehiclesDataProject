package co.edu.uptc.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclesByElectricRange {
    private String electricRange;
    private int numberOfVehicles;
}
