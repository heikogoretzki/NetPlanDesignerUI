package de.mischokacademy.NetPlanDesignerUI.Domain;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Knot {
    private int operationNumber;
    private String operationDescription;
    private int durationInMinutes;
    private List<Knot> predecessor = new ArrayList<>();
    private List<Knot> successor = new ArrayList<>();

    public Knot() {
    }

    public Knot(int operationNumber, String operationDescription, int durationInMinutes) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
    }

    public Knot(int operationNumber, String operationDescription, int durationInMinutes, List<Knot> predecessor) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
    }

    public Knot(int operationNumber, String operationDescription, int durationInMinutes, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    @SuppressWarnings("unused")
    public Knot(int operationNumber, String operationDescription, int durationInMinutes, List<Knot> predecessor, List<Knot> successor, int earliestStart, int earliestEnd) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    @SuppressWarnings("unused")
    public Knot(int operationNumber, String operationDescription, int durationInMinutes, List<Knot> predecessor, List<Knot> successor, int earliestStart, int earliestEnd, int latestStart, int latestEnd) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    @SuppressWarnings("unused")
    public Knot(int operationNumber, String operationDescription, int durationInMinutes, int latestStart, int latestEnd, List<Knot> predecessor, List<Knot> successor) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessor = predecessor;
        this.successor = successor;
    }

    public int getEarliestStart() {
        return getMaximumOfEarliestEndOfPredecessors();
    }

    public int getEarliestEnd() {
        return this.getEarliestStart() + this.getDurationInMinutes();
    }

    public int getOperationNumber() {
        return operationNumber;
    }

    @SuppressWarnings("unused")
    public void setOperationNumber(int operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    @SuppressWarnings("unused")
    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    @SuppressWarnings("unused")
    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getLatestStart() {
        return this.getLatestEnd() - this.getDurationInMinutes();
    }

    public int getLatestEnd() {
        return getMinimumOfLatestStartOfSuccessor();
    }

    public int getTotalBuffer() {
        return Math.subtractExact(this.getLatestEnd(), this.getEarliestEnd());
    }

    public int getFreeBuffer() {
        return calcFreeBuffer();
    }

    public List<Knot> getPredecessor() {
        if (predecessor == null) {
            predecessor = new ArrayList<>();
        }

        return predecessor;
    }

    public List<Knot> getSuccessor() {
        if (successor == null) {
            successor = new ArrayList<>();
        }

        return successor;
    }

    public void setSuccessor(List<Knot> successor) {
        this.successor = successor;
    }

    public Knot calculateCriticalPath() {
        if (this.getFreeBuffer() == 0 && this.getTotalBuffer() == 0) {
            return this;

        } else {
            return null;
        }
    }

    public static List<Knot> getCriticalPath(List<Knot> knotList) {
        List<Knot> criticalPath = new ArrayList<>();

        for (Knot knot : knotList) {
            if (knot.calculateCriticalPath() != null) {
                criticalPath.add(knot.calculateCriticalPath());
            }
        }

        return criticalPath;
    }

    private int getMaximumOfEarliestEndOfPredecessors() {
        int result = 0;

        for (Knot actualPredecessor : this.getPredecessor()) {
            int tempEarliestEnd = actualPredecessor.getEarliestEnd();

            result = Math.max(result, tempEarliestEnd);
        }
        return result;
    }

    private int getMinimumOfLatestStartOfSuccessor() {
        if (this.getSuccessor().isEmpty()) {
            return this.getEarliestEnd();

        } else {

            int result = Integer.MAX_VALUE;

            for (Knot actualSuccessor : this.getSuccessor()) {
                int tempLatestStart = actualSuccessor.getLatestStart();

                result = Math.min(result, tempLatestStart);
            }
            return result;
        }
    }

    private int calcFreeBuffer() {
        int result;

        if (!this.getSuccessor().isEmpty()) {
            result = Math.subtractExact(this.getSuccessor().get(this.getSuccessor().size() - 1).getEarliestStart(), this.getEarliestEnd());
        } else {
            result = 0;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Knot{" +
                "operationNumber=" + operationNumber +
                ", operationDescription='" + operationDescription + '\'' +
                ", durationInMinutes=" + durationInMinutes +
//                ", latestStart=" + latestStart +
//                ", latestEnd=" + latestEnd +
                ", predecessor=" + predecessor +
                ", successor=" + successor +
                '}';
    }
}
