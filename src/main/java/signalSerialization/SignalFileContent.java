package signalSerialization;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import signalUtils.SignalParameters;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignalFileContent {
    private String name;
    private SignalParameters signalParameters;
    private Double timeStep;
    private Integer sampleSize;
    private List<Double> yValues;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SignalFileContent{");
        sb.append("name='").append(name).append('\'');
        sb.append(", signalParameters=").append(signalParameters);
        sb.append(", timeStep=").append(timeStep);
        sb.append(", sampleSize=").append(sampleSize);
        sb.append(", yValues= {\n");
        for (Double y : yValues) {
            sb.append(y);
            sb.append(", \n");
        }
        sb.append("} \n");
        sb.append('}');
        return sb.toString();
    }
}
