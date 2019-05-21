package radarSimulator;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RadarSimulatorTest {

    @Test
    void simulate() {
        SimulatorSettingsDto settings = getSettings();
        RadarSimulator radarSimulator = new RadarSimulator(settings);

        List<Double> simulationResults = radarSimulator.simulate();
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
                                   .waveEnvironmentalSpeed(10_000.d)
                                   .build();
    }
}