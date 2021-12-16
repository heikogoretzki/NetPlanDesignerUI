package de.mischokacademy.NetPlanDesignerUI.Domain;

import java.util.ArrayList;
import java.util.List;

public class Knot {
    private int operationNumber;
    private String operationDescription;
    private int durationInMinutes;
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

    public int getEarliestStart() {
        return getMaximumOfEarliestEndOfPredecessors();
    }

    public int getEarliestEnd() {
        return this.getEarliestStart() + this.getDurationInMinutes();
    }

    public int getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(int operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getLatestStart() {
        return this.getLatestEnd() - this.getDurationInMinutes();
    }

    public void setLatestStart(int latestStart) {
        this.latestStart = latestStart;
    }

    public int getLatestEnd() {
        return getMinimumOfLatestStartOfSuccessor();
    }

    public void setLatestEnd(int latestEnd) {
        this.latestEnd = latestEnd;
    }

    public int getTotalBuffer() {
        return totalBuffer;
    }

    public void setTotalBuffer(int totalBuffer) {
        this.totalBuffer = totalBuffer;
    }

    public int getFreeBuffer() {
        return freeBuffer;
    }

    public void setFreeBuffer(int freeBuffer) {
        this.freeBuffer = freeBuffer;
    }

    public List<Knot> getPredecessor() {
        if (predecessor == null) {
            predecessor = new ArrayList<>();
        }

        return predecessor;
    }

    public void setPredecessor(List<Knot> predecessor) {
        this.predecessor = predecessor;
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

    public List<Knot> calculateCriticalPath() {

        return null;
    }

//    public void calculateEarliestTime() {
//        this.setEarliestStart(getMaximumOfEarliestEndOfPredecessors());
//        this.setEarliestEnd(this.getEarliestStart() + this.getDurationInMinutes());
//    }

    public void calculateBuffer() {

    }

    private int getMaximumOfEarliestEndOfPredecessors() {
        int result = 0;

        for (Knot actualPredecessor : this.getPredecessor()) {
            int tempEarliestEnd = actualPredecessor.getEarliestEnd();

            result = Math.max(result, tempEarliestEnd);
        }

        return result;

//        return this.getPredecessor().stream()
//                .mapToInt(knot -> knot.getEarliestEnd())
//                .max()
//                .orElse(0);
    }

    private int getMinimumOfLatestStartOfSuccessor() {
        int result = this.getEarliestEnd();

        for (Knot actualSuccessor : this.getSuccessor()) {
            int tempLatestStart = actualSuccessor.getLatestEnd() - actualSuccessor.getDurationInMinutes();

            result = Math.min(result, tempLatestStart);
        }

        return result;
    }

    @Override
    public String toString() {
        return "Knot{" +
                "operationNumber=" + operationNumber +
                ", operationDescription='" + operationDescription + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", latestStart=" + latestStart +
                ", latestEnd=" + latestEnd +
                ", totalBuffer=" + totalBuffer +
                ", freeBuffer=" + freeBuffer +
                ", predecessor=" + predecessor +
                ", successor=" + successor +
                '}';
    }
}
