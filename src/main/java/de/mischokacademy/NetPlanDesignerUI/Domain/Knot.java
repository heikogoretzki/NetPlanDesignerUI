package de.mischokacademy.NetPlanDesignerUI.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private List<Knot> predecessor;
    private List<Knot> successor;
}
