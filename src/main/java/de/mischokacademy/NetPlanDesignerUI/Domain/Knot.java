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
    private String operationDescription;
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
        this.operationDescription = operationDuration;
        this.durationInMinutes = durationInMinutes;
    }

    public Knot(int operationNumber, String operationDuration, int durationInMinutes, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDuration;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public Knot(int operationNumber, String operationDuration, int durationInMinutes, List<Knot> predecessor, List<Knot> successor, int earliestStart, int earliestEnd) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDuration;
        this.durationInMinutes = durationInMinutes;
        this.earliestStart = earliestStart;
        this.earliestEnd = earliestEnd;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public Knot(int operationNumber, String operationDuration, int durationInMinutes, int earliestStart, int earliestEnd, int latestStart, int latestEnd, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDuration;
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
        if (this.getPredecessor().size() == 0) {
            this.setEarliestStart(0);
            this.setEarliestEnd(this.getEarliestStart() + this.getDurationInMinutes());
        } else {
            for (int i = 0; i < this.getPredecessor().size(); i++) {
                Knot tempKnot = this.getPredecessor().get(i); // fail
                this.setEarliestStart(tempKnot.getEarliestStart() + tempKnot.getDurationInMinutes());
            }
        }

    }

    public List<Knot> getPredecessor() {
        if (predecessor == null) {
            predecessor = new ArrayList<>();
        }

        return predecessor;
    }

    public void calculateLatestTime() {

    }

    public void calculateBuffer() {

    }
}
