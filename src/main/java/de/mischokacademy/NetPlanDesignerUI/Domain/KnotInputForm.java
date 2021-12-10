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
}
