package radarSimulator;

import java.util.List;

import org.junit.jupiter.api.Test;
import signalGenerators.Point;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RadarSimulatorTest {

    @Test
    void simulate() {
        SimulatorSettingsDto settings = getSettings();
        RadarSimulator radarSimulator = new RadarSimulator(settings);

        List<Point> simulationResults = radarSimulator.simulate();
        assertNotNull(simulationResults);
    }

    private SimulatorSettingsDto getSettings() {
        return SimulatorSettingsDto.builder()
                                   .bufferLength(900)
                                   .objectSpeed(25.d)
                                   .objectStartDistance(500.d)
                                   .sensorSamplesPerSecond(10)
                                   .signalPeriod(3.d)
                                   .signalSamplesPerSecond(300)
                                   .simulationTime(6.d)
                                   .waveEnvironmentalSpeed(1_000.d)
                                   .build();
    }
}