package de.mischokacademy.NetPlanDesignerUI.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
}
