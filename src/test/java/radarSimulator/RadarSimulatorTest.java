package radarSimulator;

import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    void calculateCorrelation() {
        SimulatorSettingsDto settings = getSettings();
        ProximitySensor proximitySensor = new ProximitySensor(settings);

        List<Double> numbers = List.of(8d, 9d, 2d, 3d);
        List<Double> numbers2 = List.of(4d, 3d, 6d, 0d);
        List<Point> points = numbers.stream().map(number -> new Point(1d, number)).collect(Collectors.toList());
        List<Point> points2 = numbers2.stream().map(number -> new Point(1d, number)).collect(Collectors.toList());

        List<Point> results = proximitySensor.calculateCorrelation(points, points2);
        assertNotNull(results);
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