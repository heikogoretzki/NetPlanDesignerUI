package de.mischokacademy.NetPlanDesignerUI.Domain;

public class KnotInputForm {
    private int operationNumber;
    private String operationDescription;
    private int durationInMinutes;
    private Integer predecessorOneListIndex;
    private Integer predecessorTwoListIndex;
    private Integer predecessorThreeListIndex;

    public KnotInputForm(int operationNumber, String operationDescription, int durationInMinutes, Integer predecessorOneListIndex, Integer predecessorTwoListIndex, Integer predecessorThreeListIndex) {
        this.operationNumber = operationNumber;
        this.operationDescription = operationDescription;
        this.durationInMinutes = durationInMinutes;
        this.predecessorOneListIndex = predecessorOneListIndex;
        this.predecessorTwoListIndex = predecessorTwoListIndex;
        this.predecessorThreeListIndex = predecessorThreeListIndex;
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

    public Integer getPredecessorOneListIndex() {
        return predecessorOneListIndex;
    }

    @SuppressWarnings("unused")
    public void setPredecessorOneListIndex(Integer predecessorOneListIndex) {
        this.predecessorOneListIndex = predecessorOneListIndex;
    }

    public Integer getPredecessorTwoListIndex() {
        return predecessorTwoListIndex;
    }

    @SuppressWarnings("unused")
    public void setPredecessorTwoListIndex(Integer predecessorTwoListIndex) {
        this.predecessorTwoListIndex = predecessorTwoListIndex;
    }

    public Integer getPredecessorThreeListIndex() {
        return predecessorThreeListIndex;
    }

    @SuppressWarnings("unused")
    public void setPredecessorThreeListIndex(Integer predecessorThreeListIndex) {
        this.predecessorThreeListIndex = predecessorThreeListIndex;
    }

    @Override
    public String toString() {
        return "KnotInputForm{" +
                "operationNumber=" + operationNumber +
                ", operationDescription='" + operationDescription + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", predecessorOneListIndex=" + predecessorOneListIndex +
                ", predecessorTwoListIndex=" + predecessorTwoListIndex +
                ", predecessorThreeListIndex=" + predecessorThreeListIndex +
                '}';
    }
}
