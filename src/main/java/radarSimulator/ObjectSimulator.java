package radarSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import signalGenerators.Point;

@NoArgsConstructor
@Getter
public class ObjectSimulator {
    private Double currentObjectDistance;
    private Double objectSpeed;

    private SimulatorSettingsDto settingsDto;

    public ObjectSimulator(SimulatorSettingsDto settingsDto) {
        this.settingsDto = settingsDto;
        objectSpeed = settingsDto.getObjectSpeed();
        currentObjectDistance = settingsDto.getObjectStartDistance();
    }

    public List<Point> simulateObjectMovement(Double timeStep, List<Point> sourceSignal) {
        simulateStep(timeStep);
        return shiftSignal(sourceSignal);
    }

    private void simulateStep(Double timeStep) {
        currentObjectDistance = currentObjectDistance - (objectSpeed * timeStep);
    }

    private List<Point> shiftSignal(List<Point> sourceSignalValues) {
        Integer shiftDistance = getShiftDistance(settingsDto);
        return rotate(sourceSignalValues, shiftDistance);
    }

    private List<Point> rotate(List<Point> sourceSignalValues, Integer shiftDistance) {
        List<Point> shiftedSignal = new ArrayList<>(sourceSignalValues);
        Collections.rotate(shiftedSignal, shiftDistance);

        for (int i = 0; i < sourceSignalValues.size(); i++) {
            Point point = new Point(sourceSignalValues.get(i).getX(), shiftedSignal.get(i).getY());
            shiftedSignal.set(i, point);
        }

        return shiftedSignal;
    }

    private Integer getShiftDistance(SimulatorSettingsDto settingsDto) {
        double shift = (currentObjectDistance * 2) / settingsDto.getWaveEnvironmentalSpeed();
        return (int) (shift * settingsDto.getSignalSamplesPerSecond());
    }

}
