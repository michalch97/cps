package radarSimulator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import signalGenerators.Point;
import signalGenerators.SignalGenerator;
import signalGenerators.SignalOperations;
import signals.RectangularSignal;
import signals.SinHalfWaveRectifiedSignal;
import signals.SinSignal;

public class RadarSimulator {
    private SimulatorSettingsDto settingsDto;

    public RadarSimulator(SimulatorSettingsDto settingsDto) {
        this.settingsDto = settingsDto;
    }

    public List<Point> simulate() {
        ObjectSimulator objectSimulator = new ObjectSimulator(settingsDto);
        ProximitySensor proximitySensor = new ProximitySensor(settingsDto);
        double timeStep = 1.d / settingsDto.getSensorSamplesPerSecond();
        List<Point> sourceSignal = createSignal();

        DoubleStream simulationTimeStamps = getSimulationTimeStream(timeStep);
        List<Double> distances = simulationTimeStamps.mapToObj(value -> objectSimulator.simulateObjectMovement(timeStep, sourceSignal))
                                                     .map(reflectedSignal -> proximitySensor.measureDistance(sourceSignal, reflectedSignal))
                                                     .collect(Collectors.toList());
        return IntStream.range(0, (int) (settingsDto.getSimulationTime() / timeStep))
                        .mapToObj(index -> new Point(index * timeStep, distances.get(index)))
                        .collect(Collectors.toList());
    }

    private DoubleStream getSimulationTimeStream(double timeStep) {
        return DoubleStream.iterate(0.d,
                                    value -> value < settingsDto.getSimulationTime(),
                                    value -> value + timeStep);
    }

    public List<Point> getRealDistance() {
        ObjectSimulator objectSimulator = new ObjectSimulator(settingsDto);
        double timeStep = 1.d / settingsDto.getSensorSamplesPerSecond();

        DoubleStream simulationTimeStream = getSimulationTimeStream(timeStep);
        List<Double> distances = simulationTimeStream.map(value -> objectSimulator.simulateStep(timeStep)).boxed().collect(Collectors.toList());

        return IntStream.range(0, (int) (settingsDto.getSimulationTime() / timeStep))
                        .mapToObj(index -> new Point(index * timeStep, distances.get(index)))
                        .collect(Collectors.toList());
    }

    private List<Point> createSignal() {
        double signalPeriod = settingsDto.getSignalPeriod();
        double timeStep = 1.d / settingsDto.getSignalSamplesPerSecond();
        double duration = timeStep * settingsDto.getBufferLength();
        double startTime = 0.d;

        SinSignal sinSignal = new SinSignal(1.d, signalPeriod, startTime);
        RectangularSignal rectangularSignal = new RectangularSignal(1.d, signalPeriod, startTime, signalPeriod);
        SinHalfWaveRectifiedSignal sinHalfSignal = new SinHalfWaveRectifiedSignal(1.d, signalPeriod, startTime);

        List<Point> sinPoints = new SignalGenerator(sinSignal, startTime, duration, timeStep).generateSignal();
        List<Point> rectPoints = new SignalGenerator(rectangularSignal, startTime, duration, timeStep).generateSignal();
        List<Point> sinHalfPoints = new SignalGenerator(sinHalfSignal, startTime, duration, timeStep).generateSignal();

        List<Point> addedPoints = new SignalOperations().add(sinPoints, rectPoints, timeStep);
        return new SignalOperations().add(addedPoints, sinHalfPoints, timeStep);
    }
}
