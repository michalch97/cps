package radarSimulator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimulatorSettingsDto {
    private Double waveEnvironmentalSpeed;
    private Integer sensorSamplesPerSecond;
    private Double simulationTime;

    private Double signalPeriod;
    private Integer signalSamplesPerSecond;
    private Integer bufferLength;

    private Double objectStartDistance;
    private Double objectSpeed;
}
