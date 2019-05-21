package radarSimulator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalGenerators.SignalOperations;
import signals.RectangularSignal;
import signals.SinSignal;

public class RadarSimulator {
    private SimulatorSettingsDto settingsDto;

    public RadarSimulator(SimulatorSettingsDto settingsDto) {
        this.settingsDto = settingsDto;
    }

    public List<Double> simulate() {
        ObjectSimulator objectSimulator = new ObjectSimulator(settingsDto);
        ProximitySensor proximitySensor = new ProximitySensor(settingsDto);
        double timeStep = 1.d / settingsDto.getSensorSamplesPerSecond();
        List<Point> sourceSignal = createSignal();

        DoubleStream simulationTimeStamps = DoubleStream.iterate(0.d,
                                                                 value -> value < settingsDto.getSimulationTime(),
                                                                 value -> value + timeStep);
        return simulationTimeStamps.mapToObj(value -> objectSimulator.simulateObjectMovement(timeStep, sourceSignal))
                                   .map(reflectedSignal -> proximitySensor.measureDistance(sourceSignal, reflectedSignal))
                                   .collect(Collectors.toList());
    }

    private List<Point> createSignal() {
        double signalPeriod = settingsDto.getSignalPeriod();
        double timeStep = 1.d / settingsDto.getSignalSamplesPerSecond();
        double duration = timeStep * settingsDto.getBufferLength();
        double startTime = 0.d;

        SinSignal sinSignal = new SinSignal(1.d, signalPeriod, startTime);
        RectangularSignal rectangularSignal = new RectangularSignal(1.d, signalPeriod, startTime, 1.d);

        List<Point> sinPoints = new SignalGenerator(sinSignal, startTime, duration, timeStep).generateSignal();
        List<Point> rectPoints = new SignalGenerator(rectangularSignal, startTime, duration, timeStep).generateSignal();

        return new SignalOperations().add(sinPoints, rectPoints, timeStep);
    }
}
