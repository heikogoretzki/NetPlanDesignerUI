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
//    private int earliestStart;
//    private int earliestEnd;
    private int latestStart;
    private int latestEnd;
    private int totalBuffer;
    private int freeBuffer;
    private List<Knot> predecessor = new ArrayList<>();
    private List<Knot> successor = new ArrayList<>();

    public Knot() {
    }

    public Knot(int operationNumber, String operationDescription, int durationInMinutes) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
    }

    public Knot(int operationNumber, String operationDescription, int durationInMinutes, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public Knot(int operationNumber, String operationDescription, int durationInMinutes, List<Knot> predecessor, List<Knot> successor, int earliestStart, int earliestEnd) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public Knot(int operationNumber, String operationDescription, int durationInMinutes, int latestStart, int latestEnd, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.latestStart = latestStart;
        this.latestEnd = latestEnd;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public List<Knot> calculateCriticalPath() {

        return null;
    }

//    public void calculateEarliestTime() {
//        this.setEarliestStart(getMaximumOfEarliestEndOfPredecessors());
//        this.setEarliestEnd(this.getEarliestStart() + this.getDurationInMinutes());
//    }

    private int getMaximumOfEarliestEndOfPredecessors() {
        int result = 0;

        for (Knot tempKnot : this.getPredecessor()) {
            int tempEarliestEnd = tempKnot.getEarliestEnd();

//            result = Math.max(result, tempEarliestEnd);

            if (tempEarliestEnd > result) {
                result = tempEarliestEnd;
            }
        }

        return result;

//        return this.getPredecessor().stream()
//                .mapToInt(knot -> knot.getEarliestEnd())
//                .max()
//                .orElse(0);
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

    public int getEarliestStart() {
        return getMaximumOfEarliestEndOfPredecessors();
    }

    public int getEarliestEnd() {
        return this.getEarliestStart() + this.getDurationInMinutes();
    }
}
