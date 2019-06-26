package signalGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplexPoint {

    private Double x;
    private Double yReal;
    private Double yImaginary;
}
