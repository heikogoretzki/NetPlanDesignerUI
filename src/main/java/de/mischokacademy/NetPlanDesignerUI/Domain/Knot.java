package de.mischokacademy.NetPlanDesignerUI.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Knot {
    private int operationNumber;
    private String operationDuration;
    private int durationInMinutes;
    private int earliestStart;
    private int earliestEnd;
    private int latestStart;
    private int latestEnd;
    private int totalBuffer;
    private int freeBuffer;
    private List<Knot> predecessor = new ArrayList<>();
    private List<Knot> successor = new ArrayList<>();

    public Knot() {
    }

    public Knot(int operationNumber, String operationDuration, int durationInMinutes) {
        this.operationNumber = operationNumber;
        this.operationDuration = operationDuration;
        this.durationInMinutes = durationInMinutes;
    }

    public Knot(int operationNumber, String operationDuration, int durationInMinutes, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDuration = operationDuration;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public Knot(int operationNumber, String operationDuration, int durationInMinutes, int earliestStart, int earliestEnd, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDuration = operationDuration;
        this.durationInMinutes = durationInMinutes;
        this.earliestStart = earliestStart;
        this.earliestEnd = earliestEnd;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public Knot(int operationNumber, String operationDuration, int durationInMinutes, int earliestStart, int earliestEnd, int latestStart, int latestEnd, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDuration = operationDuration;
        this.durationInMinutes = durationInMinutes;
        this.earliestStart = earliestStart;
        this.earliestEnd = earliestEnd;
        this.latestStart = latestStart;
        this.latestEnd = latestEnd;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public List<Knot> calculateCriticalPath() {

        return null;
    }

    public void calculateEarliestTime() {

    }

    public void calculateLatestTime() {

    }

    public void calculateBuffer() {

    }
}
